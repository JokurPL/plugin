package me.jokur.nauka;

import com.sun.net.httpserver.HttpServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetInfo {


    private Connection con;
    private Statement st;
    private ResultSet rs;

    public void DBConnect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql:host:nazwa", "login", "haslo");
            st = con.createStatement();


        }catch(Exception ex)
        {
            System.out.println("Blad DBConnect(): "+ex);
        }

    }


    public void getData()
    {
        try
        {
            String query = "Select text From javadb";
            rs = st.executeQuery(query);
            System.out.println("Tekst z bazy danych: ");
            String tekst = rs.getString("text");
            System.out.println("Tekst z bazy danych: "+tekst);


        }catch(Exception ex)
        {
            System.out.println("Blad getData(): "+ex);
        }


    }
}
