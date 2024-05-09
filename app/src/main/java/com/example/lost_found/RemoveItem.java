package com.example.lost_found;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;


public class RemoveItem extends AppCompatActivity {

    TextView getPost_ID,getName,getPhone,getDescription,getDate,getLocation;
    MyDatabaseHelper dbHelper;
    Button removeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        Intent getMyintent=getIntent();
        String getId=getMyintent.getStringExtra("ID");
        String getPostID=getMyintent.getStringExtra("Type");
        String get_Name=getMyintent.getStringExtra("Name");
        String get_Phone=getMyintent.getStringExtra("Phone");
        String get_Desc=getMyintent.getStringExtra("Dec");
        String get_Date=getMyintent.getStringExtra("Date");
        String get_Location=getMyintent.getStringExtra("Loc");

        dbHelper=new MyDatabaseHelper(this);

        getPost_ID=findViewById(R.id.postType);
        getName=findViewById(R.id.advertName);
        getPhone=findViewById(R.id.phoneNo);
        getDescription=findViewById(R.id.decription);
        getDate=findViewById(R.id.date);
        getLocation=findViewById(R.id.location);
        removeItem=findViewById(R.id.removeButton);

        getPost_ID.setText(getPostID);
        getName.setText(get_Name);
        getPhone.setText(get_Phone);
        getDescription.setText(get_Desc);
        getDate.setText(get_Date);
        getLocation.setText(get_Location);

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.delete("MyTable","id=?",new String[]{getId});
                Intent intent=new Intent(RemoveItem.this,ShowLostFound.class);
                startActivity(intent);
            }
        });


    }
}