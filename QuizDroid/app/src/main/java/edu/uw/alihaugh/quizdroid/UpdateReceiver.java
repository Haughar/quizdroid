package edu.uw.alihaugh.quizdroid;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by alihaugh on 2/15/17.
 */

public class UpdateReceiver extends BroadcastReceiver {
    private DownloadManager downloadManager;
    private long downloadReference;

    @Override
    public void onReceive(Context context, Intent intent) {
        //pop up toast
        SharedPreferences sp = context.getSharedPreferences("quizAppPref", Context.MODE_PRIVATE);
        String notification = "Downloading from " + sp.getString("url", "http://tednewardsandbox.site44.com/questions.json");
        Toast toast = Toast.makeText(context, notification, Toast.LENGTH_SHORT);
        toast.show();

        downloadManager = (DownloadManager)context.getSystemService(DOWNLOAD_SERVICE);
        Uri Download_Uri = Uri.parse(sp.getString("url", "http://tednewardsandbox.site44.com/questions.json"));
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setTitle("Quizdroid questions");
        request.setDescription("Questions so the user can actually take quizes.");
        request.setDestinationInExternalFilesDir(context,Environment.DIRECTORY_DOWNLOADS,"Questions.json");
        downloadReference = downloadManager.enqueue(request);

        BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int ch;
                ParcelFileDescriptor file;
                StringBuffer strContent = new StringBuffer("");
                StringBuffer countryData = new StringBuffer("");

                try {
                    file = downloadManager.openDownloadedFile(downloadReference);
                    FileInputStream fileInputStream
                            = new ParcelFileDescriptor.AutoCloseInputStream(file);
                    while( (ch = fileInputStream.read()) != -1)
                        strContent.append((char)ch);

                    JSONArray questionArray = new JSONArray(strContent.toString());

                    QuizApp app = (QuizApp) context.getApplicationContext();
                    app.processInfo(questionArray);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        context.getApplicationContext().registerReceiver(downloadReceiver, filter);

        // remove toast
        toast = Toast.makeText(context, "Complete", Toast.LENGTH_SHORT);
        toast.show();
    }
}
