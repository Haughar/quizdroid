package edu.uw.alihaugh.quizdroid;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelFileDescriptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by alihaugh on 2/26/17.
 */

public class DownloadReceiver extends BroadcastReceiver {
    private DownloadManager downloadManager;
    private long downloadReference;

    @Override
    public void onReceive(Context context, Intent intent) {
        downloadManager = (DownloadManager)context.getSystemService(DOWNLOAD_SERVICE);

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

            JSONObject responseObj = new JSONObject(strContent.toString());
            JSONArray jArr = new JSONArray();
            JSONArray questionArr = jArr.put(responseObj);

            QuizApp app = (QuizApp) context.getApplicationContext();
            app.processInfo(questionArr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
