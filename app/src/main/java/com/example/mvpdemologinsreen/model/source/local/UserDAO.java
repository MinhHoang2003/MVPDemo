package com.example.mvpdemologinsreen.model.source.local;

import com.example.mvpdemologinsreen.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {


    List<User> getUsers() throws SQLException;

    boolean addUser(User user);

    boolean isValidUser(User user) throws SQLException;

    boolean hasEmail(User user) throws SQLException;
}
