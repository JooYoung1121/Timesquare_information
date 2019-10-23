package com.example.jooyoung.db_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Reservation_check extends AppCompatActivity {
    ListView reservation_list;
    ArrayList<Reservation_list> re_list = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    JSPReservation re_check, re_cancel;
    JSPAdmin re_all;
    TextView re_store_name, re_user_name, re_date, re_time;
    Button cancel, empty_list;
    Intent _intent;
    String reservation_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_check);
        reservation_list = (ListView) findViewById(R.id.reservation_list);
        re_store_name = (TextView) findViewById(R.id.re_store_name);
        re_user_name = (TextView) findViewById(R.id.re_user_name);
        re_date = (TextView) findViewById(R.id.re_date);
        re_time = (TextView) findViewById(R.id.re_time);
        empty_list = (Button) findViewById(R.id.empty_list);
        cancel = (Button) findViewById(R.id.reservation_cancel);

        if (LoginActivity.current_user.get_id().equals("admin")) {
            String sendmsg, result = null;
            String type = "reserve_check";
            sendmsg = "type=" + type;
            re_all = new JSPAdmin();
            try {
                result = re_all.execute(sendmsg).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if (result.equals("")) {
                cancel.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "예약한 내역이 없습니다. ", Toast.LENGTH_LONG).show();
            } else {
                String sp1[] = result.split("%");
                final int len_sp1 = sp1.length;
                for (int i = 0; i < len_sp1; i++) {
                    String sp2[] = sp1[i].split("@");
                    re_list.add(new Reservation_list(sp2[0], sp2[1], sp2[2], sp2[3], sp2[4], sp2[5],sp2[6]));
                }

                for (int i = 0; i < len_sp1; i++) {
                    data.add("가게 이름 : " + re_list.get(i).get_store_name());
                }

                ListAdapter adapter = new ListAdapter(this, R.layout.search_list, data);
                reservation_list.setAdapter(adapter);

                reservation_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        cancel.setVisibility(View.VISIBLE);
                        TextView tx = (TextView) findViewById(R.id.search_name);
                        re_store_name.setText(re_list.get(position).get_store_name());
                        re_user_name.setText(re_list.get(position).get_user_name());
                        re_date.setText(re_list.get(position).get_date());
                        re_time.setText(re_list.get(position).get_time());
                        reservation_id = re_list.get(position).get_id();

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _intent = new Intent(getApplicationContext(), AdminMain.class);
                        re_cancel = new JSPReservation();
                        String sendmsg, result = null;
                        String type = "cancel";
                        sendmsg = "type=" + type + "&re_id=" + reservation_id;

                        try {
                            result = re_cancel.execute(sendmsg).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        Log.i("결과", result);
                        if (result.equals("true")) {
                            Toast.makeText(getApplicationContext(), "예약이 취소되었습니다. ", Toast.LENGTH_SHORT).show();
                            startActivity(_intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "취소 실패  ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            empty_list.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {
                    _intent = new Intent(getApplicationContext(), AdminMain.class);
                    startActivity(_intent);
                }
            });


        } else {
            String sendmsg, result = null;
            String type = "reservation_list";
            sendmsg = "type=" + type + "&user=" + LoginActivity.current_user.get_id();
            re_check = new JSPReservation();
            try {
                result = re_check.execute(sendmsg).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Log.i("결과", result);
            if (result.equals("")) {
                cancel.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "예약한 내역이 없습니다. ", Toast.LENGTH_LONG).show();
            } else {
                String sp1[] = result.split("%");
                final int len_sp1 = sp1.length;
                for (int i = 0; i < len_sp1; i++) {
                    String sp2[] = sp1[i].split("@");
                    re_list.add(new Reservation_list(sp2[0], sp2[1], sp2[2], sp2[3], sp2[4], sp2[5]));
                }

                for (int i = 0; i < len_sp1; i++) {
                    data.add("가게 이름 : " + re_list.get(i).get_store_name());
                }

                ListAdapter adapter = new ListAdapter(this, R.layout.search_list, data);
                reservation_list.setAdapter(adapter);

                reservation_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        cancel.setVisibility(View.VISIBLE);
                        TextView tx = (TextView) findViewById(R.id.search_name);
                        re_store_name.setText(re_list.get(position).get_store_name());
                        re_user_name.setText(LoginActivity.current_user.get_name());
                        re_date.setText(re_list.get(position).get_date());
                        re_time.setText(re_list.get(position).get_time());
                        reservation_id = re_list.get(position).get_id();

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _intent = new Intent(getApplicationContext(), Main.class);
                        re_cancel = new JSPReservation();
                        String sendmsg, result = null;
                        String type = "cancel";
                        sendmsg = "type=" + type + "&re_id=" + reservation_id;

                        try {
                            result = re_cancel.execute(sendmsg).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        Log.i("결과", result);
                        if (result.equals("true")) {
                            Toast.makeText(getApplicationContext(), "예약이 취소되었습니다. ", Toast.LENGTH_SHORT).show();
                            startActivity(_intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "취소 실패  ", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
            empty_list.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {
                    _intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(_intent);
                }
            });


        }
    }
}
