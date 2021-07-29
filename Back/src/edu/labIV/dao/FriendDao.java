package edu.labIV.dao;

import edu.labIV.entity.Friend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FriendDao extends Dao<Friend> {

    private static final String FRI_TABLE = "tplab.friend";
    private static final String FRI_USER_ID = "account_id";
    private static final String FRI_FRIEND_ID = "friend_id";
    private static final String FRI_STATUS = "status";

    @Override
    public boolean save(Friend entity) {
        boolean executed = false;
        String sql = "INSERT INTO " + FRI_TABLE + "("+ FRI_USER_ID +", "+ FRI_FRIEND_ID +", "+ FRI_STATUS +")  VALUES(?,?,?)";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getFriendId());
            statement.setString(3, entity.getStatus());
            executed = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    @Override
    public boolean update(Friend entity) {
        boolean executed = false;
        String sql = "UPDATE " + FRI_TABLE + " SET " + FRI_STATUS + " = ?" +
                " WHERE " + FRI_USER_ID + " = ?" + " AND " + FRI_FRIEND_ID + " = ?";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setString(1, entity.getStatus());
            statement.setInt(2, entity.getUserId());
            statement.setInt(3, entity.getFriendId());
            executed = statement.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    protected  boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(int userId, int friendId) {
        boolean executed = false;
        String sql = "DELETE FROM " + FRI_TABLE + " WHERE " + FRI_USER_ID + " = " + userId +
                " AND " + FRI_FRIEND_ID + " = " + friendId;
        try{
            Statement statement = db.createStatement();
            executed = statement.executeUpdate(sql) == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    protected Friend get(int id) {
        return null;
    }

    @Override
    public Friend get(int userId, int friendId) {
        Friend friend = null;
        String sql = "SELECT * FROM "+ FRI_TABLE +" WHERE " + FRI_USER_ID + " = '" + userId+ "'" +
                " AND " + FRI_FRIEND_ID + " = '" + friendId + "'";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                friend = new Friend(userId, friendId, resultSet.getString(FRI_STATUS));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return friend;
    }

    @Override
    protected List<Friend> getAll() {
        return null;
    }

    public List<Friend> getAll(int id) {
        Friend friend;
        List<Friend> friendList = new ArrayList<>();
        String sql = "SELECT * FROM " + FRI_TABLE + " WHERE " + FRI_USER_ID + " = " + id + ";";
        try {
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                friend = new Friend();
                friend.setUserId(resultSet.getInt(FRI_USER_ID));
                friend.setFriendId(resultSet.getInt(FRI_FRIEND_ID));
                friend.setStatus(resultSet.getString(FRI_STATUS));
                friendList.add(friend);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return friendList;
    }
}
