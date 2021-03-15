/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SingletonJDBC {

    private static SingletonJDBC singleton = null;
    private String user = null;
    private String password = null;
    private String url = null;
    
    private SingletonJDBC() {
        user = "Amalia";
        password = "Amalia";
        url = "jdbc:oracle:thin:@localhost:1521:xe";
    }
    
    public static SingletonJDBC getInstance() {
        if(singleton == null) {
            return new SingletonJDBC();
        }
        else {
            return singleton;
        }
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
