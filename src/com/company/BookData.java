package com.company;

import java.util.ArrayList;
import java.util.List;

public class BookData {
    private static List<BookData> bookData;
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
    public static void AddBook(String bookNumber, String bookName, String borrowingSituation, String lendingDate){
        if(bookData == null){
            bookData = new ArrayList<>();
            bookData.add(new BookData(bookNumber,bookName,borrowingSituation,lendingDate));
            return;
        }
        bookData.add(new BookData(bookNumber,bookName,borrowingSituation,lendingDate));
    }
    public static Object[][] GetBookTable(){
        if(bookData == null){
            return null;
        }
        Object[][] date = new Object[bookData.size()][size];
        for (int i = 0 ; i < bookData.size();i++){
            date[i][0] = bookData.get(i).bookNumber;
            date[i][1] = bookData.get(i).bookName;
            date[i][2] = bookData.get(i).borrowingSituation;
            date[i][3] = bookData.get(i).lendingDate;
        }
        return bookDateTable = date;
    }
}
