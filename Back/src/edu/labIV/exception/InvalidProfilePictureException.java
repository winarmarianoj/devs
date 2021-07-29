package edu.labIV.exception;


import java.time.LocalDateTime;

public class InvalidProfilePictureException extends UserException {

    public InvalidProfilePictureException(String path) {
        idError = 16;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": " + path + " no es un formato de imagen valido.");
    }
}
