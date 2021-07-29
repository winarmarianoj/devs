package edu.labIV.exception;

import java.time.LocalDateTime;

public class InactiveAccount extends AccountException {

    public InactiveAccount() {
        idError = 5;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": La cuenta no esta activa");
    }
}
