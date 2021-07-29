package edu.labIV.exception;

import java.time.LocalDateTime;

public class WrongPasswordExcepcion extends AccountException {

    public WrongPasswordExcepcion() {
        idError = 6;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error "+ idError +": Contraseña incorrecta");
    }
}
