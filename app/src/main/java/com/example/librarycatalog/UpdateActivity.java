package com.example.librarycatalog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText bname,author,bn;
    DB udb;
    String bid, Bookname,Authorname,Booknum;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        udb = new DB(this);
        bname = (EditText) findViewById(R.id.ubname);
        author = (EditText) findViewById(R.id.uaname);
        bn = (EditText) findViewById(R.id.uBn);
        Button ubn=(Button) findViewById(R.id.updatebtn);
        Button dbn=(Button) findViewById(R.id.deletebtn);
        if(getIntent().hasExtra("id")&getIntent().hasExtra("bname")&getIntent().hasExtra("author")&getIntent().hasExtra("bn")){

            bid=getIntent().getStringExtra("id");
            Bookname=getIntent().getStringExtra("bname");
            Authorname=getIntent().getStringExtra("author");
            Booknum=getIntent().getStringExtra("bn");
            bname.setText(Bookname);
            author.setText(Authorname);
            bn.setText(Booknum);
        }
        else
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        actionBar=getSupportActionBar();
        actionBar.setTitle(Bookname);

        ubn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bookname= String.valueOf(bname.getText());
                Authorname= String.valueOf(author.getText());
                Booknum= String.valueOf(bn.getText());
                udb.UpdateBook(bid,Bookname,Authorname,Booknum);
            }
        });

        dbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    private void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setTitle("Delete " + Bookname + "?");
        builder.setMessage(String.format("Are you sure to delete %s ?",Bookname));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                udb.delete_row(bid);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });



    }
}