package edu.uw.alihaugh.quizdroid;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {
    CharSequence userAnswer;
    Button submitBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        intent = getIntent();

        final int totalQ = intent.getIntExtra("totalQ", 1);
        Log.i("intents", Integer.toString(totalQ));

        TextView question = (TextView)findViewById(R.id.questionText);
        question.setText("This is question " + totalQ);

        RadioButton radio0 = (RadioButton)findViewById(R.id.answer0);
        radio0.setText("Wrong answer");
        radio0.setOnClickListener(new answerListener(radio0.getText()));
        RadioButton radio1 = (RadioButton)findViewById(R.id.answer1);
        radio1.setText("Wrong answer");
        radio1.setOnClickListener(new answerListener(radio1.getText()));
        RadioButton radio2 = (RadioButton)findViewById(R.id.answer2);
        radio2.setText("Correct Answer");
        radio2.setOnClickListener(new answerListener(radio2.getText()));
        RadioButton radio3 = (RadioButton)findViewById(R.id.answer3);
        radio3.setText("Wrong Answer");
        radio3.setOnClickListener(new answerListener(radio3.getText()));

        submitBtn = (Button)findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(QuestionActivity.this, AnswerActivity.class);
                appIntent.putExtra("userAnswer", userAnswer);
                appIntent.putExtra("totalQ", totalQ);
                appIntent.putExtra("correctQ", intent.getIntExtra("correctQ", 0));
                startActivity(appIntent);
            }
        });
    }

    public class answerListener implements View.OnClickListener {
        private CharSequence answer;

        public answerListener(CharSequence a) {
            answer = a;
        }

        @Override
        public void onClick(View view) {
            userAnswer = answer;
            if (submitBtn.getVisibility() == View.INVISIBLE) {
                submitBtn.setVisibility(View.VISIBLE);
            }
        }
    }
}
