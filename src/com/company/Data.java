package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Date;

public class Data {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/librarydata?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";
    //加载数据
    public enum BookDataType{
        BookNumber,
        CallNumber,
        CollectionPlace,
        BookName,
        ResponsiblePerson,
        Press,LendingDate,
        ISBN,
        BorrowingSituation
    }
    public enum UserDataType{
        UserName,
        Password,
        Jurisdiction,
        BorrowingCardPeriod
    }
    public enum BorrDataType {
        UserName,
        BookNumber,
        BorrowingDate
    }
    static Object[][] GetBookDataTable(String condition, BookDataType type){
        Connection conn;
        Statement stmt;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String bookDataSQL;
            if(type == null){
                bookDataSQL = "SELECT BookNumber,CallNumber,CollectionPlace,BookName,ResponsiblePerson,Press,LendingDate,ISBN,BorrowingSituation from bookdata";
            }else {
                bookDataSQL = "SELECT BookNumber,CallNumber,CollectionPlace,BookName,ResponsiblePerson,Press,LendingDate,ISBN,BorrowingSituation from bookdata where "+
                        "locate('"+ condition +"'," + type.toString() +") != 0";
            }
            ResultSet rsBook = stmt.executeQuery(bookDataSQL);
            List<Object[]> table = new ArrayList<>();
            int count = 0;
            while (rsBook.next()){
                // 通过字段检索
                String bookNumber  = rsBook.getString("BookNumber");
                String callNumber = rsBook.getString("CallNumber");
                String collectionPlace = rsBook.getString("CollectionPlace");
                String bookName = rsBook.getString("BookName");
                String responsiblePerson = rsBook.getString("ResponsiblePerson");
                String press = rsBook.getString("Press");
                String lendingDate = rsBook.getString("LendingDate");
                String ISBN = rsBook.getString("ISBN");
                String borrowingSituation = rsBook.getString("BorrowingSituation");
                Object[] bookData = new Object[9];
                bookData[0] = bookNumber;
                bookData[1] = callNumber;
                bookData[2] = collectionPlace;
                bookData[3] = bookName;
                bookData[4] = responsiblePerson;
                bookData[5] = press;
                bookData[6] = lendingDate;
                bookData[7] = ISBN;
                bookData[8] = borrowingSituation;
                table.add(bookData);
                count++;
            }
            BookData.bookTotal = count;
            rsBook.close();
            stmt.close();
            conn.close();
            return table.toArray(Object[][]::new);
        } catch (SQLException throwable) {
            System.out.println("无法连接数据库");
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动!");
            e.printStackTrace();
        }
        return new Object[0][0];
    }
    static Object[][] GetUserDataTable(String condition,UserDataType type) {
        Connection conn;
        Statement stmt;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String userDataSQL;
            if(type == null){
                userDataSQL = "SELECT UserName, Password, Jurisdiction, BorrowingCardPeriod from userdata";
            }else {
                userDataSQL = "SELECT UserName, Password, Jurisdiction, BorrowingCardPeriod from userdata where " +
                        "locate('"+ condition +"'," + type.toString() +") != 0";
            }
            ResultSet rsUser = stmt.executeQuery(userDataSQL);
            List<Object[]> table = new ArrayList<>();
            while (rsUser.next()) {
                // 通过字段检索
                String userName = rsUser.getString("UserName");
                String passWorld = rsUser.getString("Password");
                String jurisdiction = rsUser.getString("Jurisdiction");
                String borrowingCardPeriod = rsUser.getString("BorrowingCardPeriod");
                Object[] userData = new Object[4];
                userData[0] = userName;
                userData[1] = passWorld;
                userData[2] = jurisdiction;
                userData[3] = borrowingCardPeriod;
                table.add(userData);
            }
            rsUser.close();

            stmt.close();
            conn.close();
            return table.toArray(Object[][]::new);
        } catch (SQLException throwable) {
            System.out.println("无法连接数据库");
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动!");
            e.printStackTrace();
        }
        return new Object[0][0];
    }
    static Object[][] GetBorrDataTable(String condition, BorrDataType type){
        Connection conn;
        Statement stmt;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String borDataSQL;
            if (type == null){
                borDataSQL = "SELECT UserName, BookNumber,BorrowingDate from borrowingdata";
            }else {
                borDataSQL = "SELECT UserName, BookNumber,BorrowingDate from borrowingdata where " +
                "locate('"+ condition +"'," + type.toString() +") != 0";;
            }
            ResultSet rsBorrowing = stmt.executeQuery(borDataSQL);
            List<Object[]> table = new ArrayList<>();
            while (rsBorrowing.next()){
                String userName  = rsBorrowing.getString("UserName");
                String bookNumber = rsBorrowing.getString("BookNumber");
                Date borrowingDate = rsBorrowing.getDate("BorrowingDate");
                Object[] borrowingData = new Object[3];
                borrowingData[0] = userName;
                borrowingData[1] = bookNumber;
                borrowingData[2] = borrowingDate;
                table.add(borrowingData);
            }
            rsBorrowing.close();
            stmt.close();
            conn.close();
            return table.toArray(Object[][]::new);
        } catch (SQLException throwable) {
            System.out.println("无法连接数据库");
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动!");
            e.printStackTrace();
        }
        return new Object[0][0];
    }

    static BookData FindBookForNum(String bookNumber_){
        Connection conn;
        Statement stmt;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String bookDataSQL;
            bookDataSQL = "SELECT BookNumber,CallNumber,CollectionPlace,BookName,ResponsiblePerson,Press,LendingDate,ISBN,BorrowingSituation from bookdata where "+
            "BookNUmber = " + "'"+bookNumber_+"'";
            ResultSet rsBook = stmt.executeQuery(bookDataSQL);
            BookData bookData = null;
            while (rsBook.next()){
                if(bookData != null){
                    return bookData;
                }
                String bookNumber  = rsBook.getString("BookNumber");
                String callNumber = rsBook.getString("CallNumber");
                String collectionPlace = rsBook.getString("CollectionPlace");
                String bookName = rsBook.getString("BookName");
                String responsiblePerson = rsBook.getString("ResponsiblePerson");
                String press = rsBook.getString("Press");
                String lendingDate = rsBook.getString("LendingDate");
                String ISBN = rsBook.getString("ISBN");
                String borrowingSituation = rsBook.getString("BorrowingSituation");

                bookData = new BookData(bookNumber,callNumber,collectionPlace,bookName,responsiblePerson,press,lendingDate,ISBN,borrowingSituation);
            }

            rsBook.close();
            stmt.close();
            conn.close();
            return bookData;
        } catch (SQLException throwable) {
            System.out.println("无法连接数据库");
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动!");
            e.printStackTrace();
        }
        return null;
    }
    static UserData FindUser(String userName_){
        Connection conn;
        Statement stmt;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String userDataSQL;
            userDataSQL = "SELECT UserName, Password, Jurisdiction, BorrowingCardPeriod from userdata where UserName = "+"'"+userName_+"'";
            ResultSet rsUser = stmt.executeQuery(userDataSQL);
            UserData userData = null;
            while (rsUser.next()) {
                if(userData != null){
                    return userData;
                }
                String userName = rsUser.getString("UserName");
                String passWorld = rsUser.getString("Password");
                String jurisdiction = rsUser.getString("Jurisdiction");
                String borrowingCardPeriod = rsUser.getString("BorrowingCardPeriod");
                userData = new UserData(userName,passWorld,jurisdiction,borrowingCardPeriod);
            }
            rsUser.close();
            stmt.close();
            conn.close();
            return userData;
        } catch (SQLException throwable) {
            System.out.println("无法连接数据库");
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动!");
            e.printStackTrace();
        }
        return null;
    }

    static void InsBookData(String callNumber, String collectionPlace, String bookName, String responsiblePerson, String press, String lendingDate, String ISBN){
        Date date = new Date();
        int bookNumber = Math.abs ((bookName + lendingDate +  responsiblePerson + press + lendingDate+date.toString()).hashCode());
        Connection conn = null;
        PreparedStatement pstmt;

        try {
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //创建一个Statement对象


            String bookInsSQL;
            bookInsSQL = "insert into BookData (BookNumber,CallNumber,CollectionPlace,BookName,ResponsiblePerson,Press,LendingDate,ISBN,BorrowingSituation) values(?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement (bookInsSQL);
            pstmt.setString(1, Integer.toString(bookNumber));
            pstmt.setString(2, callNumber);
            pstmt.setString(3, collectionPlace);
            pstmt.setString(4, bookName);
            pstmt.setString(5, responsiblePerson);
            pstmt.setString(6, press);
            pstmt.setString(7, lendingDate);
            pstmt.setString(8, ISBN);
            pstmt.setString(9,"未借出");
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void InsUserData(String userName , String userPassword, String Jurisdiction){
        Connection conn = null;
        int userNumber = Math.abs ((userName + userPassword).hashCode());
        PreparedStatement pstmt;

        try {
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //创建一个Statement对象


            String userInsSQL;
            userInsSQL = "insert into userdata (UserNumber,UserName,Password,Jurisdiction,BorrowingCardPeriod) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement (userInsSQL);
            pstmt.setString(1,Integer.toString(userNumber) );
            pstmt.setString(2,userName );
            pstmt.setString(3, userPassword);
            pstmt.setString(4, Jurisdiction);
            pstmt.setString(5, "正常");
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void InsBorData(String userName,String bookNumber,java.sql.Date date){
        Connection conn;
        PreparedStatement pstmt;
        try {
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //创建一个Statement对象

            String userInsSQL;
            userInsSQL = "insert into borrowingdata (UserName,BookNumber,BorrowingDate) values(?,?,?)";
            pstmt = conn.prepareStatement (userInsSQL);
            pstmt.setString(1,userName );
            pstmt.setString(2,bookNumber );
            pstmt.setDate(3, date);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void DelBookData(String bookNumber){

        String sql = "delete from bookdata where BookNumber='" + bookNumber + "'";
        PreparedStatement pstmt;
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void DelUserData(String userName){
        String sql = "delete from userdata where UserName='" + userName + "'";
        PreparedStatement pstmt;
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void DelBorData(String bookNumber){
        String sql = "delete from borrowingdata where BookNumber='" + bookNumber + "'";
        PreparedStatement pstmt;
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void UpDataBook(String bookNumber,String type){

        String sql = "update bookdata set BorrowingSituation='" + type + "' where BookNumber='" + bookNumber + "'";
        PreparedStatement pstmt;
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void SetMainUser(String name){
        UserData userData = FindUser(name);
        if(userData == null){
            System.out.println("用户不存在");
            return;
        }
        UserData.SetMainUserData(userData);
    }
}
