package com.example.jooyoung.db_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Mypage extends AppCompatActivity{

    TextView user_id,user_name,user_phone,user_reservation;
    Button logout,update_information,reservation_check;
    String id,password,name,phone;
    Intent _intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);
        logout = (Button)findViewById(R.id.logout);
        update_information = (Button)findViewById(R.id.update_information);
        reservation_check = (Button)findViewById(R.id.my_reservation);
        _intent = getIntent();
        user_id = (TextView)findViewById(R.id.user_id);
        user_name = (TextView)findViewById(R.id.user_name);
        user_phone = (TextView)findViewById(R.id.user_phone);
        user_id.setText(LoginActivity.current_user.get_id());
        user_name.setText(LoginActivity.current_user.get_name());
        user_phone.setText(LoginActivity.current_user.get_phone());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),Main.class);
                LoginActivity.current_user.set_check("false");
                Main.check = 0;

                startActivity(_intent);
            }
        });

        update_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _intent.putExtra("id",_intent.getStringExtra("id"));
                _intent.putExtra("name",_intent.getStringExtra("name"));
                _intent.putExtra("phone",_intent.getStringExtra("phone"));
                _intent = new Intent(getApplicationContext(),Myinformation_Update.class);
                startActivity(_intent);
            }
        });

        reservation_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),Reservation_check.class);

                startActivity(_intent);
            }
        });

    }
}
