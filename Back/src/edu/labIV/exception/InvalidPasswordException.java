package edu.labIV.exception;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class InvalidPasswordException extends AccountException {

    public InvalidPasswordException(String password) {
        idError = 3;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": Cuenta invalida: " + password + " no es una contraseña valida.");
    }
}
