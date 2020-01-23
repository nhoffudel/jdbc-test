package com.github.curriculeon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//String url = "jdbc:sqlite:/Users/Nick/dev/jdbc-test/src/main/java/com/github/curriculeon/chinook.db";
public class WhatIsJDBC {
    public static void main(String[] args) {
        List<String> albums = new ArrayList<String>();
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:/Users/Nick/dev/jdbc-test/src/main/java/com/github/curriculeon/chinook.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");
            Statement stmt = null;
            String query = "select * from albums";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String name = rs.getString("title");
                    albums.add(name);
//                    System.out.println(name);
                }
            } catch (SQLException e) {
                throw new Error("Problem1", e);
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }

        } catch (SQLException e) {
            throw new Error("Problem2", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        Collections.sort(albums);
        for (String i : albums) System.out.println(i);
    }
}