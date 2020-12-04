package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Date;

public class Data {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/librarydata?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";
    //加载数据
    static void LoadData(){
        Connection conn;
        Statement stmt;

        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName(JDBC_DRIVER);
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //创建一个Statement对象
            stmt = conn.createStatement();


            String userDataSQL;
            String bookDataSQL;
            String borDataSQL;
            userDataSQL = "SELECT UserName, Password, Jurisdiction, BorrowingCardPeriod from userdata";
            bookDataSQL = "SELECT BookNumber,CallNumber,CollectionPlace,BookName,ResponsiblePerson,Press,LendingDate,ISBN,BorrowingSituation from bookdata";
            borDataSQL = "SELECT UserName, BookNumber,BorrowingDate from borrowingdata";
            ResultSet rsUser = stmt.executeQuery(userDataSQL);
            //加载用户数据
            while(rsUser.next()){
                // 通过字段检索
                String userName  = rsUser.getString("UserName");
                String passWorld = rsUser.getString("Password");
                String jurisdiction = rsUser.getString("Jurisdiction");
                String borrowingCardPeriod = rsUser.getString("BorrowingCardPeriod");

                UserData.AddUser(userName,passWorld,jurisdiction,borrowingCardPeriod);

            }
            rsUser.close();
            //加载书本数据
            ResultSet rsBook = stmt.executeQuery(bookDataSQL);
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

                // 输出数据




                BookData.AddBook(bookNumber,callNumber,collectionPlace,bookName,responsiblePerson,press,lendingDate,ISBN,borrowingSituation);
            }
            rsBook.close();

            ResultSet rsBorrowing = stmt.executeQuery(borDataSQL);
            while (rsBorrowing.next()){
                // 通过字段检索
                String userName  = rsBorrowing.getString("UserName");
                String bookNumber = rsBorrowing.getString("BookNumber");
                Date borrowingDate = rsBorrowing.getDate("BorrowingDate");
                // 输出数据
                BorrowingData.AddBorrowingData(userName,bookNumber,borrowingDate);
            }
            rsBorrowing.close();

            stmt.close();
            conn.close();

        } catch (SQLException throwable) {
            System.out.println("无法连接数据库");
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动!");
            e.printStackTrace();
        }
    }
    static void UpData(){
        DelData();
        LoadData();
        if(!CheckMainUser(UserData.GetMainUserData().GetUserName())){
            MainWindow.Despose();
            JOptionPane.showMessageDialog(null,"你的账号已删除");
        }
    }
    static void DelData(){
        BookData.DelData();
        UserData.DelData();
        BorrowingData.DelData();
    }
    static void InsBookData(String callNumber, String collectionPlace, String bookName, String responsiblePerson, String press, String lendingDate, String ISBN, String borrowingSituation){
        Date date = new Date();
        int bookNumber = Math.abs ((bookName + lendingDate+date.toString()).hashCode());
        Connection conn = null;
        PreparedStatement pstmt;

        try {
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //创建一个Statement对象


            String bookInsSQL;
            bookInsSQL = "insert into BookData (BookNumber,CallNumber,CollectionPlace,BookName,ResponsiblePerson,Press,LendingDate,ISBN,BorrowingSituation) values(?,?,?,?)";
            pstmt = conn.prepareStatement (bookInsSQL);
            pstmt.setString(1, Integer.toString(bookNumber));
            pstmt.setString(2, bookName);
            pstmt.setString(3, "未借出");
            pstmt.setString(4, lendingDate);
            pstmt.executeUpdate();

            BookData.AddBook(Integer.toString(bookNumber),callNumber,collectionPlace,bookName,responsiblePerson,press,lendingDate,ISBN,borrowingSituation);
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

            UserData.AddUser(userName,userPassword,Jurisdiction,"正常");
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void InsBorData(String userName,String bookNumber,java.sql.Date date){
        Connection conn = null;
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
        BorrowingData.AddBorrowingData(userName,bookNumber,date);
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
        BookData.DelBook(bookNumber);

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
        UserData.DelUser(userName);
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
        BorrowingData.DelBorrowingData(bookNumber);
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

    static boolean CheckMainUser(String userName){
        List<UserData> list = UserData.GetUserDataList();
        if(list != null){
            for(UserData userData : list){
                if(Objects.equals(userData.GetUserName(), userName)){
                    return true;
                }
            }
        }
        return false;
    }


    static UserData SetMainUser(String name){
        for(int i = 0 ;i<UserData.GetUserDataList().size();i++){
            if(Objects.equals(UserData.GetUserDataList().get(i).GetUserName(), name)){
                System.out.println("找到用户");
                return UserData.LoadMainUserData(
                        UserData.GetUserDataList().get(i).GetUserName(),
                        UserData.GetUserDataList().get(i).GetUserPassword(),
                        UserData.GetUserDataList().get(i).GetUserJurisdiction(),
                        UserData.GetUserDataList().get(i).GetUserBorrowingCardPeriod());
            }
        }
        System.out.println("用户不存在");
        return null;
    }
}
