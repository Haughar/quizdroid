package edu.uw.alihaugh.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by alihaugh on 2/15/17.
 */

public class UpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //pop up toast
        SharedPreferences sp = context.getSharedPreferences("quizAppPref", Context.MODE_PRIVATE);
        String notification = "Downloading from " + sp.getString("url", "http://tednewardsandbox.site44.com/questions.json");
        Toast toast = Toast.makeText(context, notification, Toast.LENGTH_SHORT);
        toast.show();

        // read file from assets to device
         final AsyncTask<String, String, String> downloadedString = new DownloadFileFromUrl().execute(sp.getString("url", "http://tednewardsandbox.site44.com/questions.json"));
         String thing = downloadedString.toString();

        // remove toast
        toast = Toast.makeText(context, "Complete", Toast.LENGTH_SHORT);
        toast.show();
    }
}
