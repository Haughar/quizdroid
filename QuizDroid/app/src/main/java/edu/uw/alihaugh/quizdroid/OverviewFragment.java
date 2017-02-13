package edu.uw.alihaugh.quizdroid;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class OverviewFragment extends Fragment {
    private View view;


    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_overview, container, false);
        TextView topicTit = (TextView)view.findViewById(R.id.topicTitle);
        topicTit.setText(getArguments().getString("topic"));
        TextView topicDesc = (TextView)view.findViewById(R.id.topicDescription);
        topicDesc.setText(getArguments().getString("desc"));
        TextView numQuestions = (TextView)view.findViewById(R.id.numQuestions);
        numQuestions.setText(Integer.toString(getArguments().getInt("numQuestions")));


        Button start = (Button)view.findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("question", 1);
                b.putInt("correct", 0);
                b.putString("topic", getArguments().getString("topic"));
                QuestionFragment qf = new QuestionFragment();
                qf.setArguments(b);
                FragmentTransaction tx = getFragmentManager().beginTransaction();
                tx.replace(R.id.fragment_placeholder, qf);
                tx.commit();
            }
        });

        return view;
    }
}
