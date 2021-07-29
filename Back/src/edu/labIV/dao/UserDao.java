package edu.labIV.dao;

import edu.labIV.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao<User> {

    private static final String USR_TABLE = "tplab.user";
    private static final String USR_ID = "account_id";
    private static final String USR_NAME = "name";
    private static final String USR_LAST_NAME = "last_name";
    private static final String USR_STATUS = "status";
    private static final String USR_BIRTH_DATE = "birth_date";
    private static final String USR_PIC_PATH = "profile_picture_path";

    @Override
    public boolean save(User entity) {
        boolean executed = false;
        String sql = "INSERT INTO " + USR_TABLE + "("+ USR_ID +", "+ USR_NAME +", "+ USR_LAST_NAME +", "+ USR_STATUS +
                ", " + USR_BIRTH_DATE + ", " + USR_PIC_PATH + ")  VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getLastname());
            statement.setString(4, entity.getStatus());
            statement.setObject(5, entity.getBirthdate());
            statement.setString(6, entity.getProfilePicturePath());
            executed = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    @Override
    public boolean update(User entity) {
        boolean executed = false;
        String sql = "UPDATE " + USR_TABLE + " SET " + USR_STATUS + " = ?," + USR_NAME + " = ?," +
                        USR_LAST_NAME + " = ?," + USR_BIRTH_DATE + " = ?, " + USR_PIC_PATH + " = ? " +
                        "WHERE " + USR_ID + " = ?";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setString(1, entity.getStatus());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getLastname());
            statement.setObject(4, entity.getBirthdate());
            statement.setString(5, entity.getProfilePicturePath());
            statement.setInt(6, entity.getId());
            executed = statement.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    public boolean delete(int id) {
        boolean executed = false;
        String sql = "DELETE FROM " + USR_TABLE + " WHERE " + USR_ID + " = " + id;
        try{
            Statement statement = db.createStatement();
            executed = statement.executeUpdate(sql) == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    protected boolean delete(int id, int id2) {
        return false;
    }

    @Override
    public User get(int id) {
        User user = null;
        String sql = "SELECT * FROM " + USR_TABLE + " WHERE " + USR_ID + " = '" + id + "'";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                user = new User();
                user.setId(id);
                user.setName(resultSet.getString(USR_NAME));
                user.setLastname(resultSet.getString(USR_LAST_NAME));
                user.setStatus(resultSet.getString(USR_STATUS));
                user.setBirthdate(resultSet.getObject(USR_BIRTH_DATE, LocalDate.class));
                user.setProfilePicturePath(resultSet.getString(USR_PIC_PATH));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    protected User get(int id, int id2) {
        return null;
    }

    public List<User> getAll() {
        User user;
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM " + USR_TABLE + ";";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(USR_ID));
                user.setName(resultSet.getString(USR_NAME));
                user.setLastname(resultSet.getString(USR_LAST_NAME));
                user.setStatus(resultSet.getString(USR_STATUS));
                user.setBirthdate(resultSet.getObject(USR_BIRTH_DATE, LocalDate.class));
                userList.add(user);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return userList;
    }

    @Override
    protected List<User> getAll(int id) {
        return null;
    }


}
