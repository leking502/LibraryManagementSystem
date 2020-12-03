package com.company;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static UserData mainUserData;
    private static List<UserData> userDataList;
    private String userName;
    private String password;
    private String jurisdiction;
    private String borrowingCardPeriod;
    static final int size = 4;

    private UserData(String userName, String password, String jurisdiction, String borrowingCardPeriod) {
        this.userName = userName;
        this.password = password;
        this.jurisdiction = jurisdiction;
        this.borrowingCardPeriod = borrowingCardPeriod;
    }
    public static UserData LoadMainUserData(String userName, String password, String bookOnLoan, String borrowingCardPeriod){
        if(mainUserData == null)
            return mainUserData = new UserData(userName,password,bookOnLoan,borrowingCardPeriod);
        return mainUserData;
    }

    public static UserData GetMainUserData(){return mainUserData;}
    public String GetUserName(){return userName;}
    public String GetUserPassword(){return password;}
    public String GetUserJurisdiction(){return jurisdiction;}
    public String GetUserBorrowingCardPeriod(){return borrowingCardPeriod;}

    public String GetMainUserName(){return mainUserData.userName;}
    public String GetMainUserPassword(){return mainUserData.password;}
    public String GetMainUserJurisdiction(){return mainUserData.jurisdiction;}
    public String GetMainUserBorrowingCardPeriod(){return mainUserData.borrowingCardPeriod;}

    public static List<UserData> GetUserDataList(){return userDataList;}
    public static void AddUser(String userName, String userPassword, String jurisdiction, String borrowingCardPeriod){
        if(userDataList == null){
            userDataList = new ArrayList<>();
            userDataList.add(new UserData(userName,userPassword,jurisdiction,borrowingCardPeriod));
            return;
        }
        userDataList.add(new UserData(userName,userPassword,jurisdiction,borrowingCardPeriod));
    }
}







