package edu.uw.alihaugh.quizdroid;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;


public class AnswerFragment extends Fragment {
    View view;
    QuizApp app;
    Topic chosenTopic;

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_answer, container, false);

        int question = getArguments().getInt("question");
        int correct = getArguments().getInt("correct");
        final String answer = getArguments().getString("answer");
        final String t = getArguments().getString("topic");

        app = (QuizApp)getActivity().getApplication();
        HashMap<String, Topic> topicMap = app.getTopicMap();
        chosenTopic = topicMap.get(t);
        Question q = chosenTopic.getQuestions().get(question - 1);

        if (q.correct(answer))
            correct++;

        TextView userAnswer = (TextView)view.findViewById(R.id.userAnswer);
        userAnswer.setText(answer);

        TextView correctAnswer = (TextView)view.findViewById(R.id.correctAnswer);
        correctAnswer.setText(q.getCorrect());

        TextView stats = (TextView)view.findViewById(R.id.userStats);
        stats.setText(correct + " out of " + question + " correct");

        Button nextBtn = (Button)view.findViewById(R.id.nextBtn);

        if(question < chosenTopic.getQuestions().size()) {
            question++;
            nextBtn.setText("Next");
            final Bundle b = new Bundle();
            b.putInt("question", question);
            b.putInt("correct", correct);
            b.putString("topic", t);

            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QuestionFragment qf = new QuestionFragment();
                    qf.setArguments(b);

                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.fragment_placeholder, qf);
                    tx.commit();
                }
            });
        } else {
            nextBtn.setText("Finish");
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent appIntent = new Intent(getActivity(), MainActivity.class);
                    startActivity(appIntent);
                }
            });
        }
        return view;
    }
}
