package com.example.librarycatalog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    RecyclerView BookList;
    FloatingActionButton fb1;
    TextView t;
    ImageView i;
    DB ndb;
    LinkedList<Book> books = new LinkedList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ndb = new DB(MainActivity.this);
        t=(TextView)findViewById(R.id.ttw);
        i=(ImageView)findViewById(R.id.imw);
        BookList = (RecyclerView) findViewById(R.id.book_list);
        fb1 = (FloatingActionButton) findViewById(R.id.fb);
        BookList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        BookList.setAdapter(new CustomAdaptor(MainActivity.this,this,books));
        DisplayData();
        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddBook.class));
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
            recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all){
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            confirmDialog(this);
        }
        return super.onOptionsItemSelected(item);
    }

    void DisplayData() {
        Cursor c = ndb.ReadAllData();
        if (c.getCount() == 0) {
            t.setVisibility(View.VISIBLE);
            i.setVisibility(View.VISIBLE);
        } else {
            while (c.moveToNext()) {
                books.add(new Book(c.getString(0), c.getString(1), c.getString(2), c.getString(3)));

            t.setVisibility(View.GONE);
            i.setVisibility(View.GONE);
            }
        }
    }

    public void confirmDialog(Context con){
            AlertDialog.Builder builder = new AlertDialog.Builder(con);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are you sure to delete all ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ndb.delete_all();
                Intent i = new Intent(con,MainActivity.class);
                startActivity(i);
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