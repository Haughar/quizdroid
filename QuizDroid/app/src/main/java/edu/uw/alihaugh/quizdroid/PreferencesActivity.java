package edu.uw.alihaugh.quizdroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.PreferenceChangeEvent;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        final SharedPreferences sp = getSharedPreferences("quizAppPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor pe = sp.edit();
        if (sp == null) {
            pe.putString("url", "http://tednewardsandbox.site44.com/questions.json");
            pe.putInt("updateRate", 10);
            pe.commit();
        }

        final EditText url = (EditText) findViewById(R.id.url_text);
        url.setText(sp.getString("url", "http://tednewardsandbox.site44.com/questions.json"));
        final EditText update = (EditText) findViewById(R.id.time_interval);
        update.setText(Integer.toString(sp.getInt("updateRate", 10)));

        Button save = (Button) findViewById(R.id.save_pref);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor pe = sp.edit();
                pe.putString("url", url.getText().toString());
                pe.putInt("updateRate", Integer.parseInt(update.getText().toString()));
                pe.commit();
                Intent appIntent = new Intent(PreferencesActivity.this, MainActivity.class);
                startActivity(appIntent);
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel_pref);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(PreferencesActivity.this, MainActivity.class);
                startActivity(appIntent);
            }
        });
    }
}
