package com.example.librarycatalog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.Myviewholder> {

    LinkedList<Book> b1 ;
    Context con;
    Activity activity;
    CustomAdaptor(Activity activity,Context context, LinkedList<Book> b){
            this.b1=b;
            this.con=context;
            this.activity=activity;
    }

    @Override
    public Myviewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(con);
        View view = inflator.inflate(R.layout.card,parent,false);
        return new Myviewholder(view);

    }

    @Override
    public void onBindViewHolder( Myviewholder holder, int position) {
        holder.id.setText(b1.get(position).getBookid());
        holder.bnm.setText(b1.get(position).getBookname());
        holder.an.setText(b1.get(position).getAuthorname());
        holder.bn.setText(b1.get(position).getBookNum());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(con, UpdateActivity.class);
                i.putExtra("id",b1.get(holder.getAdapterPosition()).getBookid());
                i.putExtra("bname",b1.get(holder.getAdapterPosition()).getBookname());
                i.putExtra("author",b1.get(holder.getAdapterPosition()).getAuthorname());
                i.putExtra("bn",b1.get(holder.getAdapterPosition()).getBookNum());
                activity.startActivityForResult(i,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return b1.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
    TextView id,bnm,an,bn;
        public Myviewholder(View view){
            super(view);

        id = (TextView) view.findViewById(R.id.idd);
        bnm = (TextView) view.findViewById(R.id.bnm);
        an = (TextView) view.findViewById(R.id.an);
        bn = (TextView) view.findViewById(R.id.bn);
        }



    }
}
