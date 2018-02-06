package com.henry.videoviewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Henry on 06/02/2018.
 */

public class Localiser {

    public void localiseBatch(File pathFile) throws IOException {

        //call batch_posenet.py

        //open results file
        String resultsPath = "";
        File resultsFile = new File(resultsPath,"r");
        FileReader fileReader = new FileReader(resultsFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //iterate over result file lines and take positions
        //image    //label1    //label2    ...
        StringBuffer stringBuffer = new StringBuffer();
        String singleLine;
        while ((singleLine = bufferedReader.readLine()) != null) {
            stringBuffer.append(singleLine);
            stringBuffer.append("\n");
        }
        fileReader.close();


    }

}
