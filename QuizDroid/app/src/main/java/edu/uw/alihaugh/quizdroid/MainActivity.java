package edu.uw.alihaugh.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private QuizApp app;
    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, UpdateReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        stopService(intent);
        alarm.cancel(pi);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 100, alarmIntent);

        app = (QuizApp) getApplication();
        HashMap<String, Topic> topicMap = app.getTopicMap();
        Set<String> topics = topicMap.keySet();

        final String[] ts = topics.toArray(new String[topics.size()]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.topiclist, R.id.Itemname, ts);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent appIntent = new Intent(MainActivity.this, PreferencesActivity.class);
            startActivity(appIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
