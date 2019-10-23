package com.example.jooyoung.db_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Store_waiting extends AppCompatActivity {
    JSPAdmin waiting;
    JSPCategory name;
    ArrayList<Category_list> admin_waiting = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    TextView store_name;
    ListView store_list;
    Button go_main, regis_waiting;
    Intent _intent;
    EditText input_amount;
    String store_id;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_list);
        store_name = (TextView) findViewById(R.id.waiting_store_name);
        store_list = (ListView) findViewById(R.id.admin_waiting_list);
        go_main = (Button) findViewById(R.id.go_main);
        regis_waiting = (Button) findViewById(R.id.regis_waiting);
        input_amount = (EditText)findViewById(R.id.input_amount);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        String sendmsg, result = null;
        String type = "waiting";
        sendmsg = "type=" + type;
        name = new JSPCategory();
        try {
            result = name.execute(sendmsg).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String sp1[] = result.split("%");
        final int len_sp1 = sp1.length;
        for (int i = 0; i < len_sp1; i++) {
            String sp2[] = sp1[i].split("@");
           if (sp2[2].equals("4") || sp2[2].equals("3")) {
                admin_waiting.add(new Category_list(sp2[0], sp2[1], sp2[2], sp2[3], sp2[4], sp2[5], sp2[6]));
            }
        }
        for (int i = 0; i < admin_waiting.size(); i++) {
            data.add("가게 이름 : " + admin_waiting.get(i).get_name());
        }

        ListAdapter adapter = new ListAdapter(this, R.layout.search_list, data);
        store_list.setAdapter(adapter);

        store_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                store_name.setText(admin_waiting.get(position).get_name());
                store_id = admin_waiting.get(position).get_id();
            }
        });

        regis_waiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = null;
                amount = input_amount.getText().toString();
                imm.hideSoftInputFromWindow(regis_waiting.getWindowToken(), 0);
                if(amount == null){
                    Toast.makeText(getApplicationContext(), "웨이팅 값을 입력하세요. ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String sendmsg, result = null;
                    String type = "waiting_update";
                    sendmsg = "type=" + type + "&id=" + store_id + "&amount=" + amount;
                    waiting = new JSPAdmin();
                    try {
                        result = waiting.execute(sendmsg).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    if(result.equals("true")){
                        String store_name = null;
                        for(int i=0;i<len_sp1;i++){
                            if(store_id.equals(admin_waiting.get(i).get_id())){
                                store_name = admin_waiting.get(i).get_name();
                            }
                        }
                        Toast.makeText(getApplicationContext(), store_name + "에 현재 웨이팅은 " + amount + "입니다.", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "실패! ", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),AdminMain.class);
                startActivity(_intent);
            }
        });

    }
}
