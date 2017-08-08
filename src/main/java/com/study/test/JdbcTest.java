package com.study.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by BGL on 2017/6/3.
 */
public class JdbcTest {
    private static Properties prop = null;
    static{
        String fileName = "datasource.properties";
        prop = new Properties();
        try {
            InputStreamReader is = new InputStreamReader(JdbcTest.class.getClassLoader().getResourceAsStream(fileName),"UTF-8");
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
        try{
            Class.forName(prop.getProperty("db.driverClassName"));
            Connection conn = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.username"),prop.getProperty("db.password"));
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("name"));
            }
            System.out.println(prop.getProperty("db.driverClassName"));
        }catch(Exception e){

        }
    }
}
