package com.example.lost_found;

import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.os.Bundle;
import com.example.lost_found.databinding.ActivityCreateNewAdvertBinding;


public class Create_New_Advert extends AppCompatActivity {

    ActivityCreateNewAdvertBinding binding;
    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_advert);

        Intent getintent=getIntent();

        binding=ActivityCreateNewAdvertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper=new MyDatabaseHelper(this);

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                if(binding.Lost.isChecked())
                {
                    contentValues.put("PostType","Lost");
                } else if (binding.Found.isChecked()) {
                    contentValues.put("PostType","Found");
                }
                contentValues.put("Name",binding.Name.getText().toString());
                contentValues.put("Phone",binding.Phone.getText().toString());
                contentValues.put("Description",binding.Description.getText().toString());
                contentValues.put("Date",binding.Date.getText().toString());
                contentValues.put("Location",binding.Location.getText().toString());
                db.insert("MyTable",null,contentValues);

                Intent backToMain=new Intent(Create_New_Advert.this,MainActivity.class);
                startActivity(backToMain);

            }
        });

    }

}