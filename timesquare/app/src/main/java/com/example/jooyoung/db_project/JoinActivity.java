package com.example.jooyoung.db_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.concurrent.ExecutionException;

public class JoinActivity extends AppCompatActivity {
    JSPJoin _join,du_id;
    EditText id, pass, name, phone;
    Button join,dup_id;
    Intent _intent;
    Context c = this;
    InputMethodManager imm;
    RelativeLayout re;
    boolean du_check = false;
    String input_id,input_pass,input_name,input_phone,type;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.join);
        id = (EditText)findViewById(R.id.input_id);
        pass = (EditText)findViewById(R.id.input_pass);
        name = (EditText)findViewById(R.id.input_name);
        phone = (EditText)findViewById(R.id.input_phone);
        join = (Button)findViewById(R.id.send_join);
        re = (RelativeLayout)findViewById(R.id.join_re);
        dup_id = (Button)findViewById(R.id.dup_id);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        join.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(du_check == true) {
                    _join = new JSPJoin();
                    input_id = id.getText().toString();
                    input_pass = pass.getText().toString();
                    input_name = name.getText().toString();
                    input_phone = phone.getText().toString();
                    type = "join";
                    String result = null;
                    String sendmsg = null;
                    sendmsg = "type=" + type + "&id=" + input_id + "&pass=" + input_pass + "&name=" + input_name + "&phone=" + input_phone;
                    try {
                        result = _join.execute(sendmsg).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    Log.i("결과", result);

                    if (result.equals("true")) {
                        _intent = new Intent(getApplicationContext(), Main.class);
                        startActivity(_intent);
                    } else {
                        AlertDialog.Builder alert = new AlertDialog.Builder(c);
                        alert.setTitle("알림")
                                .setMessage("회원가입에 실패했습니다. 다시 입력해주세요")
                                .setCancelable(true)
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog dialog = alert.create();
                        dialog.show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"아이디 중복체크를 해주세요.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(id.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(pass.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(name.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(phone.getWindowToken(), 0);
            }
        });

        dup_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                du_id = new JSPJoin();
                String input_id = id.getText().toString();
                type = "dup_id";
                String result = null;
                String sendmsg = null;
                sendmsg = "type=" + type + "&id=" + input_id;
                try {
                    result = du_id.execute(sendmsg).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(result.equals("true")){
                    Toast.makeText(getApplicationContext(),"사용 가능한 아이디 입니다. ",Toast.LENGTH_SHORT).show();
                    du_check = true;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"중복된 아이디입니다.. ",Toast.LENGTH_SHORT).show();
                    du_check = false;
                }

            }
        });

    }
}
