package com.example.librarycatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBook extends AppCompatActivity {

    EditText BName;
    EditText AName;
    EditText BNum;
    Button addBook;
    DB db;


    private String BookName,AuthorName;
    private String BookNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        db=new DB(AddBook.this);

        BName = (EditText) findViewById(R.id.bname);
        AName = (EditText) findViewById(R.id.aname);
        BNum = (EditText) findViewById(R.id.Bn);
        addBook=(Button)findViewById(R.id.abb);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            BookName= BName.getText().toString();
            AuthorName= AName.getText().toString();
            BookNumber =BNum.getText().toString();
            db.AddBook(BookName,AuthorName, BookNumber);

            }
        });
    }
}