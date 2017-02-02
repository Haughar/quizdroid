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


public class AnswerFragment extends Fragment {
    View view;
    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_answer, container, false);

        int question = getArguments().getInt("question");
        final int correct = getArguments().getInt("correct");
        final String answer = getArguments().getString("answer");

        TextView stats = (TextView)view.findViewById(R.id.userStats);
        stats.setText(correct + " out of " + question + " correct");

        Button nextBtn = (Button)view.findViewById(R.id.nextBtn);

        if(question < 2) {
            question++;
            nextBtn.setText("Next");
            Bundle b = new Bundle();
            b.putInt("question", question);
            b.putInt("correct", correct);

            QuestionFragment qf = new QuestionFragment();
            qf.setArguments(b);

            FragmentTransaction tx = getFragmentManager().beginTransaction();
            tx.replace(R.id.fragment_placeholder, qf);
            tx.commit();
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
