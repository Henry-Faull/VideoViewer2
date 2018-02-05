package com.henry.videoviewer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class PlayerActivity extends AppCompatActivity {
    private Uri videoUri;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("progress","Player onCreate");

        Intent creatorIntent = getIntent();

        setContentView(R.layout.activity_player);

        //set up video player and set the video path
        videoView = findViewById(R.id.videoView);
        String video = creatorIntent.getStringExtra("filepath");
        videoUri = Uri.parse(video);
        videoView.setVideoURI(videoUri);

        //set up controls beneath the video
        MediaController controls = new MediaController(this);
        videoView.setMediaController(controls);
        controls.setAnchorView(videoView);
        controls.setMediaPlayer(videoView);

        processVideo();

    }

    //carry out localisation of the user's chosen video
    public void processVideo() {
        Log.i("progress", "Play processVideo");
        //int position = videoView.getCurrentPosition();

        //grab frames every 0.5 seconds?
        String videoPath = videoUri.getPath();
        FrameGrabber frameGrabber = new FrameGrabber(videoPath);
        //LinkedList<File> frames = frameGrabber.grabFrames(0.5);
        try {
            frameGrabber.grabFrames(2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //feed to localisation model

        //turn coordinates to global coordinates

        //go to Google and get info

        //turn info and time into a timestamp object

        startVideo();
    }

    //start the video
    public void startVideo() {
        Log.i("progress" , "Play startVideo");
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int videoDuration = videoView.getDuration();
                //duration is in milliseconds
                Log.i("durationtest", String.format("videoDuration " + videoDuration));
            }
        });


        videoView.start();

        //setOnInfoListener

    }

}
