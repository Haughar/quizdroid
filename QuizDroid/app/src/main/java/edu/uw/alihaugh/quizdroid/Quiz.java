package edu.uw.alihaugh.quizdroid;

/**
 * Created by alihaugh on 1/28/17.
 */

public class Quiz {
    private String title;
    private String description;
    private String[] questions;
    private String[] realAnswers;
    private String[] fakeAnswers;

    public Quiz(String t, String d, String[] qs, String[] ra, String[] fa) {
        title = t;
        description = d;
        questions = qs;
        realAnswers = ra;
        fakeAnswers = fa;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String[] getQuestions() {
        return questions;
    }

    public String[] getRealAnswers() {
        return realAnswers;
    }

    public String[] getFakeAnswers() {
        return fakeAnswers;
    }
}
