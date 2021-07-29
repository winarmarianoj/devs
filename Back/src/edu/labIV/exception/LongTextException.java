package edu.labIV.exception;

import edu.labIV.entity.Post;

import java.time.LocalDateTime;

public class LongTextException extends PostException {
    public LongTextException(int length) {
        idError = 15;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": Se intento guardar una cadena de " +length+  " caracteres, " +
                "cuando el maximo es: " + Post.MAX_TEXT_LENGTH);
    }
}