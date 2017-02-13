package edu.uw.alihaugh.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by alihaugh on 2/12/17.
 */

public class Question implements Serializable {
    private Map<String, Boolean> choices;
    private String questionText, correct;

    public Question(String text, ArrayList<String> as, int a) {
        this.questionText = text;

        choices = new TreeMap<String, Boolean>();
        for (int i = 0; i < as.size(); i++) {
            if (i == a) {
                choices.put(as.get(i), true);
                correct = as.get(i);
            } else {
                choices.put(as.get(i), false);
            }
        }
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public Map<String, Boolean> getChoices() {
        return choices;
    }

    public Boolean correct(String a) {
        return choices.get(a);
    }

    public String getCorrect() {
        return correct;
    }
}
