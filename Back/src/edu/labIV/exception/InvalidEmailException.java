package edu.labIV.exception;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidEmailException extends AccountException {

    public InvalidEmailException(String email) {
        idError = 2;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": Cuenta invalida: " + email + " no es un email valido.");
    }

}
