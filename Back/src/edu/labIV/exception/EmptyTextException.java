package edu.labIV.exception;

import java.time.LocalDateTime;

public class EmptyTextException extends PostException {

    public EmptyTextException() {
        idError = 14;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": El texto esta vacio.");
    }
}
