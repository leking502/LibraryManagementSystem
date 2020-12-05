package com.company;

public class BookData {
    public static int bookTotal = 0;
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


    public String GetBookNumber(){return bookNumber;}
    public String GetBookName(){return bookName;}
    public String GetLendingDate(){return lendingDate;}
    public String GetBorrowingSituation(){return borrowingSituation;}
    public void ChangeborrowingSituation(String borrowingSituation){
        this.borrowingSituation = borrowingSituation;
    }


}
