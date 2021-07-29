package edu.labIV.dao;

import edu.labIV.entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AccountDao extends Dao<Account> {

    private static final String ACC_TABLE = "tplab.account";
    private static final String ACC_ID = "account_id";
    private static final String ACC_EMAIL = "email";
    private static final String ACC_PASSWORD = "password";
    private static final String ACC_ACTIVE = "active";
    private static final String ACC_TRIES = "available_tries";

    @Override
    public boolean save(Account entity) {
        boolean executed = false;
        String sql = "INSERT INTO " + ACC_TABLE + "("+ ACC_EMAIL +", "+ ACC_PASSWORD +", "+ ACC_ACTIVE +", "+ ACC_TRIES +") VALUES(?,?,?,?)";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());
            statement.setBoolean(3, entity.isActive());
            statement.setInt(4, entity.getAttempts());
            executed = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executed;
    }

    @Override
    public boolean update(Account entity) {
        boolean executed = false;
        String sql = "UPDATE " + ACC_TABLE + " SET " +  ACC_PASSWORD + " = ?," +
                ACC_ACTIVE + " = ?," + ACC_TRIES + " = ?" +
                " WHERE " + ACC_ID + " = ?";
        try{
            PreparedStatement statement = db.createPrepareStatement(sql);
            statement.setString(1, entity.getPassword());
            statement.setBoolean(2, entity.isActive());
            statement.setInt(3, entity.getAttempts());
            statement.setInt(4, entity.getId());
            executed = statement.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return executed;
    }

    @Override
    public boolean delete(int id) {
        boolean executed = false;
        String sql = "DELETE FROM " + ACC_TABLE + " WHERE " + ACC_ID + " = " + id;
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
    public Account get(int id) {
        Account account = null;
        String sql = "SELECT * FROM "+ACC_TABLE+" WHERE " + ACC_ID + " = '" + id + "'";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                account = new Account();
                account.setId(id);
                account.setEmail(resultSet.getString(ACC_EMAIL));
                account.setPassword(resultSet.getString(ACC_PASSWORD));
                account.setActive(resultSet.getBoolean(ACC_ACTIVE));
                account.setAttempts(resultSet.getInt(ACC_TRIES));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return account;
    }

    @Override
    protected Account get(int id, int id2) {
        return null;
    }

    @Override
    protected List<Account> getAll() {
        return null;
    }

    @Override
    protected List<Account> getAll(int id) {
        return null;
    }

    public int getIdFromEmail(String email){
        int id = 0;
        String sql = "SELECT " + ACC_ID + " FROM " + ACC_TABLE + " WHERE " + ACC_EMAIL + " = '" + email + "'";
        try{
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                id = resultSet.getInt(ACC_ID);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return id;
    }
}
