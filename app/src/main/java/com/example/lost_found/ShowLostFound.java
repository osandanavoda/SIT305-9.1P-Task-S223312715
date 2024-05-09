package com.example.lost_found;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShowLostFound extends AppCompatActivity {

    MyDatabaseHelper dbHelper;
    RecyclerView recyclerView;
    ArrayList<String> Type,Name,Phone,Dec,Date,Loc,ID;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lost_found);

        dbHelper=new MyDatabaseHelper(this);

        Type=new ArrayList<>();
        Name=new ArrayList<>();
        Phone=new ArrayList<>();
        Dec=new ArrayList<>();
        Date=new ArrayList<>();
        Loc=new ArrayList<>();
        ID=new ArrayList<>();

        adapter=new MyAdapter(this,Type,Name);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(ShowLostFound.this,RemoveItem.class);
                intent.putExtra("ID",ID.get(position));
                intent.putExtra("Type",Type.get(position));
                intent.putExtra("Name",Name.get(position));
                intent.putExtra("Phone",Phone.get(position));
                intent.putExtra("Dec",Dec.get(position));
                intent.putExtra("Date",Date.get(position));
                intent.putExtra("Loc",Loc.get(position));
                startActivity(intent);

            }
        });

        ShowData();



    }

    private void ShowData() {
        Cursor cursor=dbHelper.getdata();

        while(cursor.moveToNext())
        {
            ID.add(cursor.getString(0));
            Type.add(cursor.getString(1));
            Name.add(cursor.getString(2));
            Phone.add(cursor.getString(3));
            Dec.add(cursor.getString(4));
            Date.add(cursor.getString(5));
            Loc.add(cursor.getString(6));
        }




    }
}