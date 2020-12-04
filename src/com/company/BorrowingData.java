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

    public static void DelData(){
        borrowingDataList = null;
    }

    public BorrowingData(String userName, String bookNumber, Date borrowingDate) {
        this.userName = userName;
        this.bookNumber = bookNumber;
        this.borrowingDate = borrowingDate;
    }

    static void AddBorrowingData(String userName, String bookNumber, Date borrowingDate){
        if(borrowingDataList == null){
            borrowingDataList = new ArrayList<>();
            borrowingDataList.add(new BorrowingData(userName,bookNumber,borrowingDate));
            return;
        }
        borrowingDataList.add(new BorrowingData(userName,bookNumber,borrowingDate));
    }
    static void BorBook(String bookNumber, java.sql.Date date){
        BookData data = BookData.FindBookForNum(bookNumber);
        if (data != null) {
            data.ChangeborrowingSituation("已借出");
            Data.UpDataBook(bookNumber,"已借出");
            Data.InsBorData(UserData.GetMainUserData().GetUserName(), data.GetBookNumber(),date);
        }
    }
    static void ReBorBook(String bookNumber){
        BookData data = BookData.FindBookForNum(bookNumber);
        if(data != null){
            data.ChangeborrowingSituation("未借出");
            Data.UpDataBook(bookNumber,"未借出");
            Data.DelBorData(bookNumber);
        }
    }

    static void DelBorrowingData(String bookNumber){
        if(borrowingDataList == null){
            return;
        }
        borrowingDataList.removeIf(borrowingData -> Objects.equals(borrowingData.bookNumber,bookNumber));
    }
    static Object[][] GetBorrowingTable(String userName){
        if (borrowingDataList == null){
            return new Object[0][0];
        }
        int size_ = 0;
        for (BorrowingData borrowingData : borrowingDataList) {
            if (Objects.equals(borrowingData.userName, userName)) {
                size_++;
            }
        }
        Object[][] data = new Object[size_][size];
        int i = 0;
        for (BorrowingData borrowingData : borrowingDataList) {
            if (Objects.equals(borrowingData.userName, userName)) {
                data[i][0] = borrowingData.bookNumber;
                data[i][1] = Objects.requireNonNull(BookData.FindBookForNum(borrowingData.bookNumber)).GetBookName();
                data[i][2] = borrowingData.borrowingDate;
                i++;
            }
            if (i == size_) {
                return borrowingTable = data;
            }
        }
        return new Object[0][0];
    }
}
