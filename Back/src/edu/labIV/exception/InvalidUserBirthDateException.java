package edu.labIV.exception;

import java.time.LocalDateTime;

public class InvalidUserBirthDateException extends UserException{

    public InvalidUserBirthDateException(String birthDate){
        idError = 9;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + birthDate + " no es una fecha valida.");
    }
}
