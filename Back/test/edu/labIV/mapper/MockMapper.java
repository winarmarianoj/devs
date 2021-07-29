package edu.labIV.mapper;

import edu.labIV.dao.MockDao;

public class MockMapper {

    MockDao dao = new MockDao();

    public void setReturnValue(boolean returnValue){
        dao.setReturnValue(returnValue);
    }

    public boolean save() {
        return dao.save();
    }

    public boolean update() {
        return dao.update();
    }

    public boolean delete() {
        return dao.delete();
    }

    public Object get(){
        return dao.get();
    }

}
