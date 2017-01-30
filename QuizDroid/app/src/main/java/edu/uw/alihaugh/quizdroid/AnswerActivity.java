package edu.uw.alihaugh.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    Intent intent;
    int correctQ, totalQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        intent = getIntent();

        correctQ = intent.getIntExtra("correctQ", 0);
        totalQ = intent.getIntExtra("totalQ", correctQ + 1);
        totalQ++;
        String userAnswer = intent.getStringExtra("userAnswer");
        Boolean correct = false;

        String correctAswer = "Correct Answer";
        if(correctAswer.equals(userAnswer)) {
            correctQ++;
            correct = true;
        }

        TextView userA = (TextView)findViewById(R.id.userAnswer);
        userA.setText(userAnswer);

        TextView cAnswer = (TextView)findViewById(R.id.correctAnswer);
        cAnswer.setText(correctAswer);

        TextView stats = (TextView)findViewById(R.id.userStats);
        stats.setText(correctQ + " out of " + totalQ + " correct");

        Button nextBtn = (Button)findViewById(R.id.nextBtn);
        if(totalQ < 2) {
            nextBtn.setText("Next");
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent appIntent = new Intent(AnswerActivity.this, QuestionActivity.class);
                    Log.i("intents", Integer.toString(correctQ));
                    Log.i("intents", Integer.toString(totalQ));
                    appIntent.putExtra("correctQ", correctQ);
                    appIntent.putExtra("totalQ", totalQ);
                    startActivity(appIntent);
                }
            });
        } else {
            nextBtn.setText("Finish");
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent appIntent = new Intent(AnswerActivity.this, MainActivity.class);
                    startActivity(appIntent);
                }
            });
        }
    }
}
