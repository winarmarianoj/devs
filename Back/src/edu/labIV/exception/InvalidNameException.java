package edu.labIV.exception;

import java.time.LocalDateTime;

public class InvalidNameException extends UserException{

    public InvalidNameException(String name){
        idError = 7;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + name + " no es un nombre valido.");
    }
}
