package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BorrowingData {
    private static List<BorrowingData> borrowingDataList;
    private static Object[][] borrowingTable;
    private String userName;
    private String bookNumber;
    private Date borrowingDate;
    static final int size = 3;

    public  String GetUserName(){ return userName;}
    public String GetBookNumber(){ return bookNumber;}
    public Date GetBorrowingDate() { return borrowingDate; }

    public BorrowingData(String userName, String bookNumber, Date borrowingDate) {
        this.userName = userName;
        this.bookNumber = bookNumber;
        this.borrowingDate = borrowingDate;
    }
    static void BorBook(String bookNumber, java.sql.Date date){
        BookData data = Data.FindBookForNum(bookNumber);
        if (data != null) {
            data.ChangeborrowingSituation("已借出");
            Data.UpDataBook(bookNumber,"已借出");
            Data.InsBorData(UserData.GetMainUserData().GetUserName(), data.GetBookNumber(),date);
        }
    }
    static void ReBorBook(String bookNumber){
        BookData data = Data.FindBookForNum(bookNumber);
        if(data != null){
            data.ChangeborrowingSituation("未借出");
            Data.UpDataBook(bookNumber,"未借出");
            Data.DelBorData(bookNumber);
        }
    }
    static Object[][] GetUserBorTable(String userName){
        Object[][] table = Data.GetBorrDataTable(userName,Data.BorrDataType.UserName);
        for(Object[] borrData : table){
            borrData[0] = borrData[1];
            borrData[1] = Objects.requireNonNull(Data.FindBookForNum(borrData[1].toString())).GetBookName();
        }
        return table;
    }
}
