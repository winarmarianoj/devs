package edu.labIV.dao;

import edu.labIV.db.DataBaseConnection;

import java.util.List;

public abstract class Dao <T> {

    protected final DataBaseConnection db;

    public Dao() {
        this.db = DataBaseConnection.getInstance();
    }

    abstract boolean save(T entity);

    abstract boolean update(T entity);

    abstract boolean delete(int id);

    abstract boolean delete(int id, int id2); //usado en friend y publication

    abstract T get(int id);

    abstract T get(int id, int id2); //usado en friend y publication

    abstract List<T> getAll();

    abstract List<T> getAll(int id);

}


