package com.henry.videoviewer;

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

    public FrameGrabber(String inputPath) {

        videoFile = new File(inputPath);
    }

    public void grabFrames(float rate) throws IOException {
        //take frames a Hz = rate input

        Log.i("progress","grab frames");
        SeekableByteChannel channel = NIOUtils.readableChannel(videoFile);
        MP4Demuxer dmx = MP4Demuxer.createRawMP4Demuxer(channel);
        DemuxerTrack track = dmx.getVideoTrack();

        int totalFrames = track.getMeta().getTotalFrames();
        double videoLength = track.getMeta().getTotalDuration();
        double frameRate = totalFrames/videoLength;
        double forward = frameRate/rate;


        //iterate through, taking the 15th frame each time

        //return a linked list of frames

    }
}
