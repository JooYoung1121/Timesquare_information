package com.example.jooyoung.db_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Myinformation_Update extends AppCompatActivity {
    JSPJoin update;
    Intent _intent;
    EditText pw_check,pw_update,name_update,phone_update;
    Button pw_button,update_button;
    String id,pw,pw_che,name,phone = null;
    InputMethodManager imm;
    LinearLayout input_area,li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_update);
        _intent = getIntent();
        pw_check = (EditText)findViewById(R.id.pw_check);
        pw_update = (EditText)findViewById(R.id.pw_update);
        name_update = (EditText)findViewById(R.id.name_update);
        phone_update = (EditText)findViewById(R.id.phone_update);
        pw_button = (Button)findViewById(R.id.pw_button);
        update_button = (Button)findViewById(R.id.update_button);
        input_area = (LinearLayout)findViewById(R.id.change_input);
        li = (LinearLayout)findViewById(R.id.li);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        input_area.setVisibility(View.INVISIBLE);

        input_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(pw_update.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(name_update.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(phone_update.getWindowToken(), 0);
            }
        });

        li.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(pw_update.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(name_update.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(phone_update.getWindowToken(), 0);
            }
        });

        pw_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw_che = pw_check.getText().toString();
                if(LoginActivity.current_user.get_password().equals(pw_che)){
                    input_area.setVisibility(View.VISIBLE);


                    name_update.setText(LoginActivity.current_user.get_name());
                    phone_update.setText(LoginActivity.current_user.get_phone());
                    imm.hideSoftInputFromWindow(pw_check.getWindowToken(), 0);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다. ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update = new JSPJoin();
                pw = pw_update.getText().toString();
                name = name_update.getText().toString();
                phone = phone_update.getText().toString();
                id = LoginActivity.current_user.get_id();
                String type = "update";
                String result = null;
                String sendmsg = null;
                sendmsg = "type=" + type + "&id=" + id + "&pass=" + pw + "&name=" + name + "&phone=" + phone;
                try {
                    result = update.execute(sendmsg).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(result.equals("true")){
                    LoginActivity.current_user.set_name(name);
                    LoginActivity.current_user.set_password(pw);
                    LoginActivity.current_user.set_phone(phone);
                    Toast.makeText(getApplicationContext(),"정보가 변경되었습니다. ",Toast.LENGTH_SHORT).show();
                    _intent = new Intent(getApplicationContext(),Main.class);
                    startActivity(_intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"누락된 정보가 있거나 전과 같은 비밀번호를 입력하셨습니다.  ",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
