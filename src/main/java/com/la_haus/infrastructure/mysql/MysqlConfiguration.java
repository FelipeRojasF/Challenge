package com.la_haus.infrastructure.mysql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Configuration
public class MysqlConfiguration {
    private final static String url = "jdbc:mysql://localhost:3306/";
    private final static String user = "root";
    private final static String password = "felipe";

    //@Bean
    public Connection getDataBase() throws SQLException {
        System.out.println("Pase por aqui, sql");
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

}
