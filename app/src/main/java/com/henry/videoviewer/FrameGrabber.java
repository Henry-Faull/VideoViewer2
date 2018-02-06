package com.henry.videoviewer;

import android.Manifest;
import android.content.ContentProvider;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Picture;
import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
import org.jcodec.scale.AWTUtil;
//import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by Henry on 05/02/2018.
 */

public class FrameGrabber {
    File videoFile;
    Uri videoUri;

    public FrameGrabber(Uri inputUri, Context context, PlayerActivity activity) {

        videoUri = inputUri;
        //videoFile = new File(inputUri);
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        String yourRealPath = "";
        Cursor cursor = context.getContentResolver().query(inputUri, filePathColumn, null, null, null);
        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            yourRealPath = cursor.getString(columnIndex);
        } else {
            //boooo, cursor doesn't have rows ...
        }
        cursor.close();

        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        videoFile = new File(yourRealPath);

    }

    public void grabFrames(float rate) throws IOException, JCodecException {
        //take frames a Hz = rate input

        Log.i("progress","grab frames");
        SeekableByteChannel channel = NIOUtils.readableChannel(videoFile);
        MP4Demuxer dmx = MP4Demuxer.createRawMP4Demuxer(channel);
        DemuxerTrack track = dmx.getVideoTrack();

        int totalFrames = track.getMeta().getTotalFrames();
        double videoLength = track.getMeta().getTotalDuration();
        double frameRate = totalFrames/videoLength;
        int forward = (int)Math.floor(frameRate/rate);

        Log.i("framerate",Double.toString(frameRate));
        Log.i("duration",Double.toString(videoLength));

        //iterate through, taking the 15th frame each time

        for(int i=0; i < videoLength; i++) {
            Log.i("forward",Integer.toString(forward));
            Log.i("videoabc",videoFile.getName());
            Picture frame = FrameGrab.getFrameFromFile(videoFile, i * forward);

            //ImageIO.write(AWTUtil.toBufferedImage(frame), "png", image);


            //return a linked list of frames
            Log.i("framing", Integer.toString(i));
        }
    }
}
