package com.company;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class UserData {
    private static UserData userData;
    private String userName;
    private String password;
    private String bookOnLoan;
    private String borrowingCardPeriod;

    private UserData(String userName, String password, String bookOnLoan, String borrowingCardPeriod) {
        this.userName = userName;
        this.password = password;
        this.bookOnLoan = bookOnLoan;
        this.borrowingCardPeriod = borrowingCardPeriod;
    }
    public static UserData CreateUserDate(String userName, String password, String bookOnLoan, String borrowingCardPeriod){
        if(userData == null)
            return userData = new UserData(userName,password,bookOnLoan,borrowingCardPeriod);
        return  userData;
    }

    public static UserData GetUserDate(){return userData;}
    public String GetName(){return userData.userName;}
    public String GetPassword(){return userData.password;}
}







