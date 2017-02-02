package edu.uw.alihaugh.quizdroid;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class QuestionFragment extends Fragment {
    private View view;
    CharSequence userAnswer;
    Button submitBtn;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_question, container, false);

        final int question = getArguments().getInt("question");
        final int correct = getArguments().getInt("correct");

        TextView questionText = (TextView)view.findViewById(R.id.questionText);
        questionText.setText("Question text");

        RadioButton radio0 = (RadioButton)view.findViewById(R.id.answer0);
        radio0.setText("Wrong answer");
        radio0.setOnClickListener(new QuestionFragment.answerListener(radio0.getText()));
        RadioButton radio1 = (RadioButton)view.findViewById(R.id.answer1);
        radio1.setText("Wrong answer");
        radio1.setOnClickListener(new QuestionFragment.answerListener(radio1.getText()));
        RadioButton radio2 = (RadioButton)view.findViewById(R.id.answer2);
        radio2.setText("Correct Answer");
        radio2.setOnClickListener(new QuestionFragment.answerListener(radio2.getText()));
        RadioButton radio3 = (RadioButton)view.findViewById(R.id.answer3);
        radio3.setText("Wrong Answer");
        radio3.setOnClickListener(new QuestionFragment.answerListener(radio3.getText()));

        submitBtn = (Button)view.findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("question", question);
                b.putInt("correct", correct);
                b.putString("answer", userAnswer.toString());

                AnswerFragment af = new AnswerFragment();
                af.setArguments(b);
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                tx.replace(R.id.fragment_placeholder, af);
                tx.commit();
            }
        });

        return view;
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
