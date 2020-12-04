package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookData {
    private static List<BookData> bookDataList;
    private static Object[][] bookDateTable;
    private String bookNumber;
    private String bookName;
    private String borrowingSituation;
    private String lendingDate;
    static final int size = 4;

    private BookData(String bookNumber, String bookName, String borrowingSituation, String lendingDate) {
        this.bookNumber = bookNumber;
        this.bookName = bookName;
        this.borrowingSituation = borrowingSituation;
        this.lendingDate = lendingDate;
    }
    public static void DelData(){
        bookDataList = null;
    }
    public static int GetBookTotal(){
        return bookDataList.size();
    }
    public String GetBookNumber(){return bookNumber;}
    public String GetBookName(){return bookName;}
    public String GetLendingDate(){return lendingDate;}
    public String GetBorrowingSituation(){return borrowingSituation;}
    public static void AddBook(String bookNumber, String bookName, String borrowingSituation, String lendingDate){
        if(bookDataList == null){
            bookDataList = new ArrayList<>();
            bookDataList.add(new BookData(bookNumber,bookName,borrowingSituation,lendingDate));
            return;
        }
        bookDataList.add(new BookData(bookNumber,bookName,borrowingSituation,lendingDate));
    }
    public static void DelBook(String bookNumber){
        bookDataList.removeIf(bookData -> Objects.equals(bookData.bookNumber , bookNumber));
    }
    public static BookData FindBookForNum(String bookNumber){
        for(int i = 0 ; i < bookDataList.size(); i++){
            if(Objects.equals(bookDataList.get(i).bookNumber, bookNumber)){
                return  bookDataList.get(i);
            }
        }
        return null;
    }
    public void ChangeborrowingSituation(String borrowingSituation){
        this.borrowingSituation = borrowingSituation;
    }
    public static Object[][] GetBookTable(){
        if(bookDataList == null){
            return new Object[0][0];
        }
        Object[][] date = new Object[bookDataList.size()][size];
        for (int i = 0; i < bookDataList.size(); i++){
            date[i][0] = bookDataList.get(i).bookNumber;
            date[i][1] = bookDataList.get(i).bookName;
            date[i][2] = bookDataList.get(i).borrowingSituation;
            date[i][3] = bookDataList.get(i).lendingDate;
        }
        return bookDateTable = date;
    }
}
