package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookData {
    private static List<BookData> bookDataList;
    private static Object[][] bookDateTable;
    private String bookNumber;
    private String callNumber;
    private String collectionPlace;
    private String bookName;
    private String responsiblePerson;
    private String press;
    private String lendingDate;
    private String ISBN;
    private String borrowingSituation;
    static final int size = 9;



    public BookData(String bookNumber, String callNumber, String collectionPlace, String bookName, String responsiblePerson, String press, String lendingDate, String ISBN, String borrowingSituation) {
        this.bookNumber = bookNumber;
        this.callNumber = callNumber;
        this.collectionPlace = collectionPlace;
        this.bookName = bookName;
        this.responsiblePerson = responsiblePerson;
        this.press = press;
        this.lendingDate = lendingDate;
        this.ISBN = ISBN;
        this.borrowingSituation = borrowingSituation;
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
    public static void AddBook(String bookNumber, String callNumber, String collectionPlace, String bookName, String responsiblePerson, String press, String lendingDate, String ISBN, String borrowingSituation){
        if(bookDataList == null){
            bookDataList = new ArrayList<>();
            bookDataList.add(new BookData(bookNumber,callNumber,collectionPlace,bookName,responsiblePerson,press,lendingDate,ISBN,borrowingSituation));
            return;
        }
        bookDataList.add(new BookData(bookNumber,callNumber,collectionPlace,bookName,responsiblePerson,press,lendingDate,ISBN,borrowingSituation));
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
            date[i][1] = bookDataList.get(i).callNumber;
            date[i][2] = bookDataList.get(i).collectionPlace;
            date[i][3] = bookDataList.get(i).bookName;
            date[i][4] = bookDataList.get(i).responsiblePerson;
            date[i][5] = bookDataList.get(i).press;
            date[i][6] = bookDataList.get(i).lendingDate;
            date[i][7] = bookDataList.get(i).ISBN;
            date[i][8] = bookDataList.get(i).borrowingSituation;
        }
        return bookDateTable = date;
    }
}
