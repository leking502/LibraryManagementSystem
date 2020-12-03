package com.company;

public class UserData {
    private static UserData userData;
    private String userName;
    private String password;
    private String jurisdiction;
    private String borrowingCardPeriod;

    private UserData(String userName, String password, String jurisdiction, String borrowingCardPeriod) {
        this.userName = userName;
        this.password = password;
        this.jurisdiction = jurisdiction;
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







