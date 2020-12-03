package com.company;

import javax.management.openmbean.ArrayType;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Date;

public class Data {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/librarydata?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";
    static  public List<String[]> userDataList = new ArrayList<>();
    //加载数据
    static void LoadData(){
        Connection conn = null;
        Statement stmt = null;

        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName(JDBC_DRIVER);
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //创建一个Statement对象
            stmt = conn.createStatement();


            String userDataSQL;
            String bookDataSQL;
            userDataSQL = "SELECT UserName, Password, Jurisdiction, BorrowingCardPeriod from userdata";
            bookDataSQL = "SELECT BookNumber, BookName, BorrowingSituation,PublicationTime from bookdata";
            ResultSet rsUser = stmt.executeQuery(userDataSQL);
            //加载用户数据
            while(rsUser.next()){
                // 通过字段检索
                String userName  = rsUser.getString("UserName");
                String passWorld = rsUser.getString("Password");
                String bookOnLoan = rsUser.getString("Jurisdiction");
                String borrowingCardPeriod = rsUser.getString("BorrowingCardPeriod");

                // 输出数据
                String[] userData = new String[4];
                userData[0] = userName;
                userData[1] = passWorld;
                userData[2] = bookOnLoan;
                userData[3] = borrowingCardPeriod;

                userDataList.add(userData);

            }
            rsUser.close();
            //加载书本数据
            ResultSet rsBook = stmt.executeQuery(bookDataSQL);
            while (rsBook.next()){
                // 通过字段检索
                String bookNumber  = rsBook.getString("BookNumber");
                String bookName = rsBook.getString("BookName");
                String borrowingSituation = rsBook.getString("BorrowingSituation");
                String lendingDate = rsBook.getString("PublicationTime");
                // 输出数据
                BookData.AddBook(bookNumber,bookName,borrowingSituation,lendingDate);
            }
            rsBook.close();
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
    static void AddBookData(String bookName ,String publicationTime){
        Date date = new Date();
        int bookNumber = Math.abs ((bookName + publicationTime+date.toString()).hashCode());
        Connection conn = null;
        PreparedStatement pstmt;

        try {
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //创建一个Statement对象


            String bookInsSQL;
            bookInsSQL = "insert into BookData (BookNumber,BookName,BorrowingSituation,PublicationTime) values(?,?,?,?)";
            pstmt = conn.prepareStatement (bookInsSQL);
            pstmt.setString(1, Integer.toString(bookNumber));
            pstmt.setString(2, bookName);
            pstmt.setString(3, "未借出");
            pstmt.setString(4, publicationTime);
            pstmt.executeUpdate();

            BookData.AddBook(Integer.toString(bookNumber),bookName,"未借出",publicationTime);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static UserData FindUser(String name){
        for(int i = 0 ;i<userDataList.size();i++){
            if(Objects.equals(userDataList.get(i)[0], name)){
                System.out.println("找到用户");
                return UserData.CreateUserDate(
                        userDataList.get(i)[0],
                        userDataList.get(i)[1],
                        userDataList.get(i)[2],
                        userDataList.get(i)[3]);
            }
        }
        System.out.println("用户不存在");
        return null;
    }
}
