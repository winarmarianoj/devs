package edu.labIV.logger;

import java.io.*;

public class Logger {

    private static final String LOG_PATH = "log/ErrorLog.txt";

    private static Logger logger;
    private  BufferedWriter bufferedWriter;

    private Logger() {
        openLog();
    }

    public static Logger getInstance(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }

    private void openLog(){
        File log = new File(LOG_PATH);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(log));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logError(String message){
        try{
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //no esta en uso
    public void closeLog(){
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
