package com.tyskie.DAOs;

import com.tyskie.Domain.User;

import java.util.List;

/**
 * Created by Thijs on 9-6-2017.
 */
public interface UserDAO {
    void createUser(String username, String password);

    boolean checkUser(String username, String password);

    List<User> getAllUsers();
}
