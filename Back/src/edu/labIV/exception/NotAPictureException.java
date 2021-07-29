package edu.labIV.exception;

import java.time.LocalDateTime;

public class NotAPictureException extends UserException{

    public NotAPictureException(String path){
        idError = 17;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + path + " no es un archivo de imagen valido.");
    }

}
