package com.henry.videoviewer;

import android.location.Location;

/**
 * Created by Henry on 04/02/2018.
 */

public class InfoNugget {
    private int startTime;
    private int endTime;
    private String infoTitle;
    private String infoBody;
    private float[] coordinates;
    private float[] rotation;

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public String getInfoBody() {
        return infoBody;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public float[] getRotation() {
        return rotation;
    }

    public InfoNugget() {
        startTime = 0;
        endTime = 1;
        infoTitle = "title";
        infoBody = "This is an item of interest";
        coordinates = new float[]{4, 5};
        rotation = new float[]{55,44,33};
    }

}
