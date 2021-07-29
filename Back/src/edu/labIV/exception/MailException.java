package edu.labIV.exception;

import java.time.LocalDateTime;

public class MailException extends Exception{
    protected int idError;
    protected String error;

    public MailException(String mail) {
        idError = 18;
        String currentTime = LocalDateTime.now().toString().replace("T", " ");
        setError("["+ currentTime +"] Error " + idError + ": No se pudo enviar mail a " + mail +".");
    }

    public String getError(){
        return this.error;
    }

    protected void setError(String message){
        this.error = message;
    }
}
