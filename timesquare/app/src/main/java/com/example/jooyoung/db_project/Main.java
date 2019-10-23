package com.example.jooyoung.db_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

public class Main extends AppCompatActivity {
    Button login_or_myinfo,cate,floor_map_button ;
    Intent _intent;
    User current_user;
    static int check = 0;
    JSPReservation time_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timesquare);
        login_or_myinfo = (Button)findViewById(R.id.login_button);
        cate = (Button)findViewById(R.id.category_list);
        _intent = getIntent();
        time_check = new JSPReservation();
        String type,result = null,sendmsg = null;
        type = "past";
        sendmsg = "type=" + type ;

        try {
            result = time_check.execute(sendmsg).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } // 이전 시간 예약 다 정리


        if(check != 0) {
            if (LoginActivity.current_user.get_check().equals("true")) {
                current_user = new User(_intent.getStringExtra("id"), _intent.getStringExtra("password"),
                        _intent.getStringExtra("name"), _intent.getStringExtra("phone"));
                login_or_myinfo.setText("내 정보");
                login_or_myinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _intent = new Intent(getApplicationContext(), Mypage.class);
                        _intent.putExtra("id", current_user.get_id());
                        _intent.putExtra("password", current_user.get_password());
                        _intent.putExtra("name", current_user.get_name());
                        _intent.putExtra("phone", current_user.get_phone());

                        startActivity(_intent);
                        }
                        }
                );
            } else {
                current_user = new User();
                login_or_myinfo.setText("로그인");
                login_or_myinfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(_intent);
                        }
                        }
                );
            }
        }
        else{
            current_user = new User();
            login_or_myinfo.setText("로그인");
            login_or_myinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(_intent);
                    }
                    }
            );
        }


        cate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),Category_view.class);
                startActivity(_intent);
            }
        });

        floor_map_button = (Button)findViewById(R.id.map_button);
        floor_map_button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(), FloorMapActivity.class);
                startActivity(_intent);
            }
        });


    }
}
