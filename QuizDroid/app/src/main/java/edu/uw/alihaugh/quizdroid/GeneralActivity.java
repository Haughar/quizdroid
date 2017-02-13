package edu.uw.alihaugh.quizdroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class GeneralActivity extends Activity {
    Topic chosenTopic;
    QuizApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        app = (QuizApp) getApplication();
        HashMap<String, Topic> topicMap = app.getTopicMap();


        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        chosenTopic = topicMap.get(topic);

        Bundle b = new Bundle();
        b.putString("topic", chosenTopic.getTitle());
        b.putString("desc", chosenTopic.getDescription());
        b.putInt("numQuestions", chosenTopic.getQuestions().size());

        OverviewFragment overviewF = new OverviewFragment();
        overviewF.setArguments(b);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, overviewF);
        tx.commit();
    }
}
