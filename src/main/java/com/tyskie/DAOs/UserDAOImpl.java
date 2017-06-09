package com.tyskie.DAOs;

import com.tyskie.DBConnection.DatabaseConnection;

import javax.enterprise.context.Dependent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Thijs on 9-6-2017.
 */
@Dependent
public class UserDAOImpl implements UserDAO {
    private DatabaseConnection connection = new DatabaseConnection();
    private Connection conn = connection.getConnection();

    private PreparedStatement preparedStatement;
    private String query;
    private ResultSet rs;

    @Override
    public void createUser(String username, String password) {
        try{
            query = "insert into users values ?,?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkUser(String username, String password) {
        boolean exists = false;
        try{
            query = "select * from users where username = ? and password = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}
