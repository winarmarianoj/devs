package edu.labIV.exception;

import java.time.LocalDateTime;

public class InvalidLastNameException extends UserException {

    public InvalidLastNameException(String lastName){
        idError = 8;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + " :" + lastName + " no es un apellido valido.");
    }
}
