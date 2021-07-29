package edu.labIV.exception;


import java.time.LocalDateTime;

public class NotAnImageException extends PostException {

    public NotAnImageException(String path) {
        idError = 12;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": " + path + " no es un formato de imagen valido.");
    }
}
