package edu.labIV.exception;

import java.time.LocalDateTime;

public class NullTextException extends PostException {

    public NullTextException() {
        idError = 13;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": El texto es nulo.");
    }
}
