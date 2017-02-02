package edu.uw.alihaugh.quizdroid;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GeneralActivity extends Activity {

    final String[] descriptions = new String[] {
            "This quiz will test to see if you still have a kindergarten-level understanding of math.",
            "This quiz will test to see if you still have a highschool-level understanding of physics.",
            "This quiz will test to see if you are a true Marvel Super Hero fan."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        int topicNum = intent.getIntExtra("topicNum", 0);

        Bundle b = new Bundle();
        b.putString("topic", topic);
        b.putString("desc", descriptions[topicNum]);

        OverviewFragment overviewF = new OverviewFragment();
        overviewF.setArguments(b);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, overviewF);
        tx.commit();
    }
}
