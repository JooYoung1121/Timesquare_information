package com.example.jooyoung.db_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

public class Store_information extends AppCompatActivity{

    TextView store_floor,store_name,store_time,store_phone,store_category,store_waiting,wait;
    ImageView floor_map;
    Button reservation;
    String floor,name,time,phone,id,fid,waiting,_store_category;
    Intent _intent;
    LinearLayout le;
    ImageView store_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_information);
        store_logo = (ImageView)findViewById(R.id.imageView3);
        store_floor = (TextView)findViewById(R.id.store_floor);
        store_name = (TextView)findViewById(R.id.store_name);
        store_time = (TextView)findViewById(R.id.store_time);
        store_phone = (TextView)findViewById(R.id.store_phone);
        reservation = (Button)findViewById(R.id.reservation);
        le = (LinearLayout)findViewById(R.id.show_login);
        store_category = (TextView)findViewById(R.id.store_classifying);
        store_waiting = (TextView)findViewById(R.id.waiting_number);
        wait = (TextView)findViewById(R.id.wait);

        _intent = getIntent();
        name = _intent.getStringExtra("name");
        time = _intent.getStringExtra("opentime");
        floor = _intent.getStringExtra("FLR");
        phone = _intent.getStringExtra("phone");
        id = _intent.getStringExtra("id");
        fid = _intent.getStringExtra("fid");
        waiting = _intent.getStringExtra("waiting");
        _store_category = _intent.getStringExtra("store_category");


        Context c = getApplicationContext();
        int _id = c.getResources().getIdentifier("drawable/_" + id, null, c.getPackageName());
        store_logo.setImageResource(_id);
        if(fid.equals("3") || fid.equals("4")){
            store_waiting.setText(waiting);
            wait.setVisibility(View.VISIBLE);
            store_waiting.setVisibility(View.VISIBLE);
        }
        else
        {
            wait.setVisibility(View.INVISIBLE);
            store_waiting.setVisibility(View.INVISIBLE);
        }

        store_floor.setText(floor);
        store_name.setText(name);
        store_time.setText(time);
        store_phone.setText(phone);
        store_category.setText(_store_category);

        floor_map=(ImageView)findViewById(R.id.floor_map);
        switch (floor){
            case "B2F": floor_map.setImageResource(R.drawable.shop_b2f);
                break;
            case "B1F": floor_map.setImageResource(R.drawable.shop_b1f);
                break;
            case "1F": floor_map.setImageResource(R.drawable.shop_1f);
                break;
            case "2F": floor_map.setImageResource(R.drawable.shop_2f);
                break;
            case "3F": floor_map.setImageResource(R.drawable.shop_3f);
                break;
            case "4F": floor_map.setImageResource(R.drawable.shop_4f);
                break;
            case "5F": floor_map.setImageResource(R.drawable.shop_5f);
                break;
        }

        if(Main.check != 0){
            if(fid.equals("3") || fid.equals("4")) {
                le.setVisibility(View.VISIBLE);
                reservation.setText("예약");
                reservation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _intent = new Intent(getApplicationContext(),ReservationActivity.class);
                        _intent.putExtra("id",id);
                        _intent.putExtra("name",name);
                        startActivity(_intent);
                    }
                });
            }

            else if(fid.equals("18")){
                if(name.equals("CGV")){
                    reservation.setText("웹 연결");
                    le.setVisibility(View.VISIBLE);
                    reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.cgv.co.kr/"));
                            startActivity(_intent);
                        }
                    });
                }
                else if(name.equals("교보문고")){
                    reservation.setText("웹 연결");
                    le.setVisibility(View.VISIBLE);
                    reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.kyobobook.co.kr/"));
                            startActivity(_intent);
                        }
                    });
                }
                else if(name.equals("키즈 앤 키즈")){
                    reservation.setText("웹 연결");
                    le.setVisibility(View.VISIBLE);
                    reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.kidsnkeys.co.kr/"));
                            startActivity(_intent);
                        }
                    });
                }
                else if(name.equals("딸기가 좋아")){
                    reservation.setText("웹 연결");
                    le.setVisibility(View.VISIBLE);
                    reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://dalki.com"));
                            startActivity(_intent);
                        }
                    });
                }
                else {
                    le.setVisibility(View.INVISIBLE);
                }
            }
            else{
                le.setVisibility(View.INVISIBLE);
            }

        }
        else
        {
            if(fid.equals("18")){
                if(name.equals("CGV")){
                    reservation.setText("웹 연결");
                    le.setVisibility(View.VISIBLE);
                    reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.cgv.co.kr/"));
                            startActivity(_intent);
                        }
                    });
                }
                else if(name.equals("교보문고")){
                    reservation.setText("웹 연결");
                    le.setVisibility(View.VISIBLE);
                    reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.kyobobook.co.kr/"));
                            startActivity(_intent);
                        }
                    });
                }
                else if(name.equals("키즈 앤 키즈")){
                    reservation.setText("웹 연결");
                    le.setVisibility(View.VISIBLE);
                    reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.kidsnkeys.co.kr/"));
                            startActivity(_intent);
                        }
                    });
                }
                else if(name.equals("딸기가 좋아")){
                    reservation.setText("웹 연결");
                    le.setVisibility(View.VISIBLE);
                    reservation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://dalki.com"));
                            startActivity(_intent);
                        }
                    });
                }
                else {
                    le.setVisibility(View.INVISIBLE);
                }
            }
            else {
                le.setVisibility(View.INVISIBLE);
            }
        }
    }
}
