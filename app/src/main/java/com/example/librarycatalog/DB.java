package com.example.librarycatalog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class DB extends SQLiteOpenHelper{
private Context context;
final private String column_id = "column_id";
final private String book_name="book_name";
static String DB_name="BookLib.db";
final private String author_name= "author_name";
private final String isbn="isbn";
private final String BOOKLIB="booklibrary";
    public DB(Context context) {
        super(context,DB_name,null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, book_name TEXT, author_name TEXT, isbn INTEGER );", BOOKLIB,column_id));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOKLIB);
        onCreate(db);
    }

    public void AddBook(String b_name, String a_name, String bn){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(book_name,b_name);
        cv.put(author_name,a_name);
        cv.put(isbn,bn);
        long res = db.insert(BOOKLIB,null,cv);
        if (res==-1){
            Toast.makeText(context, "Task Failed", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
    }


     Cursor ReadAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data =null;
        String query = "SELECT * FROM " + BOOKLIB;
        if(data ==null){
            data=db.rawQuery(query,null);
        }
        return data;
    }
    public void UpdateBook(String uid, String ub_name, String ua_name, String ubn){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(book_name,ub_name);
        cv.put(author_name,ua_name);
        cv.put(isbn,ubn);
        int r=db.update(BOOKLIB,cv,column_id+"=?",new String[]{uid});
        if (r==-1){
            Toast.makeText(context, "Task Failed", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
    }

    public void delete_row(String d_id){
        SQLiteDatabase db = this.getReadableDatabase();
        int r = db.delete(BOOKLIB,column_id+"=?",new String[]{d_id});
        if (r==-1){
            Toast.makeText(context, "Task Failed", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();

    }

    public void delete_all(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + BOOKLIB);

    }


}