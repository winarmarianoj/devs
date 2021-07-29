package edu.labIV.exception;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class ExistingAccountException extends AccountException{

    public ExistingAccountException(String mail) {
        idError = 1;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error "+ idError +": Ya existe una cuenta vinculada a " + mail);
    }
}
