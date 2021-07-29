package edu.labIV.exception;

public class UserException extends Exception{

    protected int idError;
    protected String error;

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public String getError(){
        return this.error;
    }

    protected void setError(String message){
        this.error = message;
    }
}
