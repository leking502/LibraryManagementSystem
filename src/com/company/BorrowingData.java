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
            return null;
        }
        int size_ = 0;
        for (int i = 0; i < borrowingDataList.size(); i++) {
            if (Objects.equals(borrowingDataList.get(i).userName, userName)) {
                size_++;
            }
        }
        Object[][] data = new Object[size_][size];
        int i = 0;
        for (int j = 0; j < borrowingDataList.size(); j++) {
            if (Objects.equals(borrowingDataList.get(j).userName, userName)) {
                data[i][0] =  borrowingDataList.get(j).bookNumber;
                data[i][1] = BookData.FindBookForNum(borrowingDataList.get(j).bookNumber).GetBookName();
                data[i][2] = borrowingDataList.get(j).borrowingDate;
                i++;
            }
            if (i == size_) {
                return borrowingTable = data;
            }
        }
        return null;
    }
}
