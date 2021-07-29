package edu.labIV.dao;

public class MockDao{

    private boolean retValue;

    public void setReturnValue(boolean returnValue) {
        retValue = returnValue;
    }

    public boolean save() {
        return retValue;
    }

    public boolean update() {
        return retValue;
    }

    public boolean delete() {
        return retValue;
    }

    public Object get() {
        return new Object();
    }
}
