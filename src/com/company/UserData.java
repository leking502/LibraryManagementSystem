package com.company;


public class UserData {
    private static UserData mainUserData;
    private String userNumber;
    private String userName;
    private String password;
    private String jurisdiction;
    private String borrowingCardPeriod;
    static private Object[][] userDataTable;
    static final int size = 4;


    public UserData(String userName, String password, String jurisdiction, String borrowingCardPeriod) {
        this.userName = userName;
        this.password = password;
        this.jurisdiction = jurisdiction;
        this.borrowingCardPeriod = borrowingCardPeriod;
    }
    public static void SetMainUserData(UserData mainuser){
            mainUserData = mainuser;
    }

    public static void Cancellation(){
        mainUserData = null;
    }
    public static UserData GetMainUserData(){return mainUserData;}

    public String GetUserName(){return userName;}
    public String GetUserPassword(){return password;}
    public String GetUserJurisdiction(){return jurisdiction;}
    public String GetUserBorrowingCardPeriod(){return borrowingCardPeriod;}

    public static String GetMainUserName(){return mainUserData.userName;}
    public static String GetMainUserPassword(){return mainUserData.password;}
    public static String GetMainUserJurisdiction(){return mainUserData.jurisdiction;}
    public static String GetMainUserBorrowingCardPeriod(){return mainUserData.borrowingCardPeriod;}


}







