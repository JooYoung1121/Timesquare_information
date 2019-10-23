package com.example.jooyoung.db_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminMain extends AppCompatActivity{
    Intent _intent;
    Button admin_waiting,admin_reservation,log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        admin_waiting = (Button)findViewById(R.id.admin_waiting);
        admin_reservation = (Button)findViewById(R.id.admin_reservation);
        log_out = (Button)findViewById(R.id.log_out);

        admin_waiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),Store_waiting.class);
                startActivity(_intent);
            }
        });

        admin_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),Reservation_check.class);
                startActivity(_intent);

            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),Main.class);
                LoginActivity.current_user.set_check("false");
                Main.check = 0;

                startActivity(_intent);
            }
        });


    }
}
