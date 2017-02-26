package edu.uw.alihaugh.quizdroid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alihaugh on 2/12/17.
 */

public class QuizApp extends Application implements TopicRepository{
    private static QuizApp instance = null;
    private HashMap<String, Topic> topicMap;

    public QuizApp() {
        Log.i("class", "Quiz app created");
    }

    public static QuizApp getInstance() {
        if (instance == null)
            instance = new QuizApp();
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        topicMap = new HashMap<String, Topic>();
        String json = null;
        try {
            InputStream is = getAssets().open("questions.json");
            json = readFile(is);
            // SharedPreferences sp = getSharedPreferences("quizAppPref", Context.MODE_PRIVATE);
            // final AsyncTask<String, String, String> downloadedString = new DownloadFileFromUrl().execute(sp.getString("url", "http://tednewardsandbox.site44.com/questions.json"));
            // String thing = downloadedString.toString();
            // JSONObject jObject = new JSONObject(downloadedString.toString());
            JSONArray info = new JSONArray(json);
            processInfo(info);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("class", "Class was created");
    }

    public String readFile(InputStream is) throws IOException {
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }

    public void processInfo(JSONArray info) {
        topicMap.clear();
        try {
            for (int i = 0; i < info.length(); i++) {
                JSONObject obj = (JSONObject) info.get(i);
                String title = obj.getString("title");
                String desc = obj.getString("desc");
                String image = "not implemented"; //obj.getString("image");
                JSONArray questions = obj.getJSONArray("questions");

                ArrayList<Question> qs = new ArrayList<Question>();
                for(int j = 0; j < questions.length(); j++) {
                    JSONObject q = (JSONObject) questions.get(j);
                    String text = q.getString("text");
                    int cAnswer = q.getInt("answer");
                    JSONArray choices = q.getJSONArray("answers");
                    ArrayList<String> choicesList = new ArrayList<String>();
                    for (int k = 0; k < choices.length(); k++) {
                        choicesList.add(choices.get(k).toString());
                    }
                    qs.add(new Question(text, choicesList, cAnswer));
                }
                Topic t = new Topic(title, desc, image, qs);
                topicMap.put(title, t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Topic> getTopicMap() {
        return this.topicMap;
    }
}
