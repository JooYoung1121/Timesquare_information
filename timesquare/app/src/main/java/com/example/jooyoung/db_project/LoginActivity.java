package com.example.jooyoung.db_project;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends AppCompatActivity {

    JSPLogin _login;
    JSPAdmin _admin;
    static User current_user;
    EditText id_input, pass_input;
    Button check_login,user_join;
    Intent _intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        id_input = (EditText)findViewById(R.id.id);
        pass_input = (EditText)findViewById(R.id.password);
        check_login = (Button)findViewById(R.id._login);
        user_join = findViewById(R.id._join);
        check_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = id_input.getText().toString();
                String pass = pass_input.getText().toString();

                if (id.equals("admin")) {
                    _admin = new JSPAdmin();
                    String type = "admin";
                    String result = null;
                    String sendmsg = null;
                    sendmsg = "type=" + type + "&id=" + id + "&pass=" + pass;
                    try {
                        result = _admin.execute(sendmsg).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    if (result.equals("true")) {
                        Main.check++;
                        current_user = new User(id,pass);
                        _intent = new Intent(getApplicationContext(), AdminMain.class);
                        _intent.putExtra("id", current_user.get_id());
                        _intent.putExtra("password", current_user.get_password());
                        _intent.putExtra("check", current_user.get_check());

                        startActivity(_intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "없는 아이디거나 비밀번호가 잘못 입력되었습니다.  ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    _login = new JSPLogin();
                    String type = "login";
                    String result = null;
                    String sendmsg = null;
                    sendmsg = "login_type=" + type + "&id=" + id + "&pass=" + pass;
                    try {
                        result = _login.execute(sendmsg).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    Log.i("결과 : ", result);
                    String sp[] = result.split("@");

                    if (sp[0].equals("true")) {
                        Main.check++;
                        current_user = new User(sp[1], sp[2], sp[3], sp[4]);
                        _intent = new Intent(getApplicationContext(), Main.class);
                        _intent.putExtra("id", current_user.get_id());
                        _intent.putExtra("password", current_user.get_password());
                        _intent.putExtra("name", current_user.get_name());
                        _intent.putExtra("phone", current_user.get_phone());
                        _intent.putExtra("check", current_user.get_check());
                        Toast.makeText(getApplicationContext(), "로그인 성공 ! 환영합니다. " + current_user.get_name() + "님", Toast.LENGTH_SHORT).show();
                        startActivity(_intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "없는 아이디거나 비밀번호가 잘못 입력되었습니다.  ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        user_join.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(_intent);

            }
        });





    }

}
