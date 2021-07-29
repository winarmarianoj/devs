package edu.labIV.mapper;

import edu.labIV.dao.UserDao;
import edu.labIV.entity.User;

import java.util.List;

public class UserMapper {
    
    private final UserDao userDao;

    public UserMapper() {
        this.userDao = new UserDao();
    }
    
    public boolean save(User user){
        return userDao.save(user);
    }

    public boolean delete(int id){
        return userDao.delete(id);
    }

    public User get(int id){
        return userDao.get(id);
    }

    public List<User> getAll(){
    	return userDao.getAll();
    }
  
    public boolean update(User user) {
        return userDao.update(user);
    }
}
