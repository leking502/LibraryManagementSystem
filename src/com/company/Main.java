package com.company;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.sql.*;

public class Main {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/date?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "123456";
    public static void main(String[] args)
    {
        Connection conn = null;
        Statement stmt = null;

        try {
            //调用Class.forName()方法加载驱动程序
            Class.forName(JDBC_DRIVER);
            //调用DriverManager对象的getConnection()方法，获得一个Connection对象
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //创建一个Statement对象
            stmt = conn.createStatement();


            String sql;
            sql = "SELECT UserName, PassWord, BookOnLoan,BorrowingCardPeriod from userdata";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                // 通过字段检索
                String userName  = rs.getString("UserName");
                String passWorld = rs.getString("PassWord");
                String bookOnLoan = rs.getString("BookOnLoan");
                String borrowingCardPeriod = rs.getString("BorrowingCardPeriod");

                // 输出数据
                System.out.print(userName+"\t");
                System.out.print(passWorld+"\t");
                System.out.print(bookOnLoan+"\t");
                System.out.print(borrowingCardPeriod);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException throwables) {
            System.out.println("无法连接数据库");
            //throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到MySQL驱动!");
            //e.printStackTrace();
        }
        try
        {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.ShowWindow();
        MainWindow mainWindow = new MainWindow();
        mainWindow.ShowWindow();
    }
}
