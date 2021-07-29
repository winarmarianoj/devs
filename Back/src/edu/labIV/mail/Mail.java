package edu.labIV.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class Mail {

    protected static String getHTMLContent(String htmlFilePath) {
        File htmlFile = new File(htmlFilePath);
        StringBuilder builder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(htmlFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while( (line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
