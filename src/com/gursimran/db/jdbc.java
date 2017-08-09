package com.gursimran.db;

import com.gursimran.model.user;

import java.sql.*;

/**
 * Created by gursimransingh on 08/08/17.
 */
public class jdbc {
    Connection conn;
    PreparedStatement preparedStatement;

    public jdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openConnection(){
        String url = "jdbc:mysql://localhost/lms";
        String user = "root";
        String pass = "root";

        try{
            conn = DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int registerUser(user user){
        int check = 0;
        try {
            String sql = "INSERT INTO `user` VALUES (NULL, ?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());

            check = preparedStatement.executeUpdate();
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        return check;
    }
    public boolean loginUser(user user){
        boolean login =false;
        try{
            String sql = "SELECT * FROM user where Email = ? AND Password = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            login = resultSet.next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return login;
    }
    public String getName(user user){
        String name=null;
        try{
            String sql = "SELECT Name FROM user WHERE Email = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmail());
            ResultSet resultSet  = preparedStatement.executeQuery();
            name = resultSet.getString("Name");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }

    public void closeConnection(){
        try{
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
