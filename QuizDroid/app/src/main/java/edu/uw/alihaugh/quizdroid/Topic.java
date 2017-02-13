package edu.uw.alihaugh.quizdroid;

import java.util.ArrayList;

/**
 * Created by alihaugh on 2/12/17.
 */

public class Topic {
    private String title, description;
    private ArrayList<Question> questions;

    public Topic (String t, String d, ArrayList<Question> qs) {
        this.title = t;
        this.description = d;
        this.questions = qs;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

}
