package com.example.lost_found;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button create_advert,show_lost_found,map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_advert=findViewById(R.id.createnewAdvert_Button);
        map=findViewById(R.id.showmap);
        show_lost_found=findViewById(R.id.Showlostfounditems_button);

        //Create New Advert
        create_advert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,Create_New_Advert.class);
                startActivity(intent);
            }
        });

        //Show Lost & Found Items
        show_lost_found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sentTOShow=new Intent(MainActivity.this,ShowLostFound.class);
                startActivity(sentTOShow);
            }
        });

        //map

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sentTOmap=new Intent(MainActivity.this, ShowAllItemOnMap.class);
                startActivity(sentTOmap);
            }
        });

    }
}