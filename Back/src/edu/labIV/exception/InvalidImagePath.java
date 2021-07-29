package edu.labIV.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InvalidImagePath extends PostException {

    public InvalidImagePath(String path) {
        idError = 11;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ":" + path + " no existe.");
    }
}
