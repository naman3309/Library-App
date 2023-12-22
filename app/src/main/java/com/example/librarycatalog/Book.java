package com.example.librarycatalog;

public class Book {
    private String bookid;
    private String bookname;
    private String authorname;
    private String isbn;

    Book(String bookid,String bnm,String an,String bn){
        this.bookid=bookid;
        this.bookname=bnm;
        this.authorname=an;
        this.isbn=bn;
    }


    public String getBookid() {
        return bookid;
    }
    public String getBookname() {
        return bookname;
    }
    public String getAuthorname() {
        return authorname;
    }
    public String getBookNum() {
        return isbn;
    }


}
