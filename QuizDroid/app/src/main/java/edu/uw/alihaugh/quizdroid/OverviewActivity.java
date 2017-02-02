package edu.uw.alihaugh.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        final String[] topics = new String[] { "Math", "Physics", "Marvel Super Heroes" };
        final String[] descriptions = new String[] {
                "This quiz will test to see if you still have a kindergarten-level understanding of math.",
                "This quiz will test to see if you still have a highschool-level understanding of physics.",
                "This quiz will test to see if you are a true Marvel Super Hero fan."
        };

        Intent intent = getIntent();
        final int topicNum = intent.getIntExtra("topic", 0);
        Log.i("topic", Integer.toString(topicNum));

        TextView title = (TextView) findViewById(R.id.topicTitle);
        title.setText(topics[topicNum]);

        TextView description = (TextView) findViewById(R.id.topicDescription);
        description.setText(descriptions[topicNum]);

        Button start = (Button)findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OverviewActivity.this, QuestionActivity.class);
                intent.putExtra("topic", topics[topicNum]);
                intent.putExtra("topicNum", topicNum);
                startActivity(intent);
            }
        });
    }
}
