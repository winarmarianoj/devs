package edu.labIV.exception;

import java.time.LocalDateTime;

public class NullPostException extends PostException {

    public NullPostException() {
        idError = 10;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": El post es nulo.");
    }
}
