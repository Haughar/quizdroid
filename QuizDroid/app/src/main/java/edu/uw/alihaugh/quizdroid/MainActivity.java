package edu.uw.alihaugh.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private QuizApp app;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (QuizApp) getApplication();
        HashMap<String, Topic> topicMap = app.getTopicMap();
        Set<String> topics = topicMap.keySet();

        final String[] ts = topics.toArray(new String[topics.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ts);
        ListView listview = (ListView)findViewById(R.id.listview);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent appIntent = new Intent(MainActivity.this, GeneralActivity.class);
                Log.i("clicks", ts[position]);
                appIntent.putExtra("topic", ts[position]);
                startActivity(appIntent);
            }
        });
    }
}
