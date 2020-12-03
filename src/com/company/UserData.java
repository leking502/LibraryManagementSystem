package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserData {
    private static UserData mainUserData;
    private static List<UserData> userDataList;
    private String userNumber;
    private String userName;
    private String password;
    private String jurisdiction;
    private String borrowingCardPeriod;
    static private Object[][] userDataTable;
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

    public static List<UserData> GetUserDataList(){
        if(userDataList == null)
            return new ArrayList<UserData>();
        return userDataList;
    }
    public static void AddUser(String userName, String userPassword, String jurisdiction, String borrowingCardPeriod){
        if(userDataList == null){
            userDataList = new ArrayList<>();
            userDataList.add(new UserData(userName,userPassword,jurisdiction,borrowingCardPeriod));
            return;
        }
        userDataList.add(new UserData(userName,userPassword,jurisdiction,borrowingCardPeriod));
    }
    public static void DelUser(String userName){
        userDataList.removeIf(userData -> Objects.equals(userData.userName, userName));
    }
    public static Object[][] GetUserTable(){
        if(userDataList == null){
            return null;
        }
        Object[][] date = new Object[userDataList.size()][size];
        for (int i = 0 ; i < userDataList.size();i++){
            date[i][0] = userDataList.get(i).userName;
            date[i][1] = userDataList.get(i).password;
            date[i][2] = userDataList.get(i).jurisdiction;
            date[i][3] = userDataList.get(i).borrowingCardPeriod;
        }
        return userDataTable = date;
    }
}







