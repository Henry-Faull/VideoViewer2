package com.henry.videoviewer;

import android.content.Intent;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private Intent requestFileIntent;
    private ParcelFileDescriptor parcelFileDescriptor;
    public static final String INTENT_MESSAGE = "filepath";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestFileIntent = new Intent(Intent.ACTION_PICK);
        requestFileIntent.setType("video/mp4");
        Log.i("progress", "onCreate");
    }

    //sends Intent to transition to viewing screen
    public void goToViewer(Uri vidURI) {
        Intent playerIntent  = new Intent(this, PlayerActivity.class);
        Log.i("progress","goToViewer");

        //packages in the video's uri
        playerIntent.putExtra(INTENT_MESSAGE, vidURI.toString());
        startActivity(playerIntent);
    }

    //sends Intent for the video file (talk to Gallery app)
    public void fetchVideo(View view) {
        Log.i("progress", "fetchVideo");
        startActivityForResult(requestFileIntent,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent returnIntent) {
        // test if selection worked
        Log.i("progress", "onActivityResult");
        if (resultCode != RESULT_OK) {
            return;
        } else {

            // get URI
            Uri returnUri = returnIntent.getData();
            try {
                parcelFileDescriptor = getContentResolver().openFileDescriptor(returnUri, "r");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("MainActivity", "File not found.");
                return;
            }

            goToViewer(returnUri);
        }
    }


}
