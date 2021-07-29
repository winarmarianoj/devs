package edu.labIV.exception;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class NullAccountException extends AccountException {

    public NullAccountException() {
        idError = 4;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": La cuenta es nula");
    }
}
