package com.example.jooyoung.db_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReservationActivity extends AppCompatActivity {
    Button setting,reservation;
    CalendarView calender;
    TimePicker time;
    String date,re_time = null; // 날짜 , 시간
    String store,id;
    AlertDialog.Builder select;
    Intent _intent;
    JSPReservation _reservation;// jsp 부분

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        final List<String> selectlist = new ArrayList<>();
        _intent = getIntent();
        store = _intent.getStringExtra("name");
        id = _intent.getStringExtra("id");
        selectlist.add("날짜 선택");
        selectlist.add("시간 선택");
        final CharSequence[] items = selectlist.toArray(new String[selectlist.size()]);
        final List SelectedItems = new ArrayList();
        final int defaultItem = 0;
        SelectedItems.add(defaultItem);



        setting = (Button)findViewById(R.id.reservation_setting);
        reservation = (Button)findViewById(R.id.reservation_time);
        calender = (CalendarView)findViewById(R.id.calender);
        time = (TimePicker)findViewById(R.id.timepicker);
        select = new AlertDialog.Builder(this);
        final AlertDialog.Builder re_check = new AlertDialog.Builder(this);
        time.setVisibility(View.INVISIBLE);
        calender.setVisibility(View.INVISIBLE);



        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select.setTitle("예약 설정");
                select.setSingleChoiceItems(items, defaultItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectedItems.clear();
                        SelectedItems.add(which);
                    }
                });
                select.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "";
                        if(!SelectedItems.isEmpty()){
                            msg = selectlist.get((int)SelectedItems.get(0));
                        }
                        Log.i("체크",msg);
                        if(msg.equals("날짜 선택")){
                            time.setVisibility(View.INVISIBLE);
                            calender.setVisibility(View.VISIBLE);
                            calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                @Override
                                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                    date = Integer.toString(year) + "/" + Integer.toString(month+1) + "/" + Integer.toString(dayOfMonth);

                                }
                            });

                        }
                        else
                        {
                            time.setVisibility(View.VISIBLE);
                            calender.setVisibility(View.INVISIBLE);
                            time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                                @Override
                                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                                    re_time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);
                                }
                            });
                        }
                    }
                });

                select.create();
                select.show();
            }
        });


        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date == null || re_time == null){
                    if(date == null && re_time != null){
                        re_check.setTitle("알림").setMessage("시간이 입력이 안되었습니다.")
                                .setCancelable(true).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                    }
                    else if(date != null && re_time == null){
                        re_check.setTitle("알림").setMessage("날짜가 입력이 안되었습니다.")
                                .setCancelable(true).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                    }
                    else
                    {
                        re_check.setTitle("알림").setMessage("날짜와 시간 둘다 입력이 안되었습니다.")
                                .setCancelable(true).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                    }
                    re_check.create();
                    re_check.show();

                }
                else{
                    String type,result = null,sendmsg = null;
                    _reservation = new JSPReservation();
                    type = "reservation";
                    sendmsg = "type=" + type + "&date=" + date + "&time=" + re_time + "&user=" + LoginActivity.current_user.get_id()
                                         + "&s_id=" + id;

                    try {
                        result = _reservation.execute(sendmsg).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } // JSP 예약 부분

                    if(result.equals("true")){
                        Toast.makeText(getApplicationContext(),"예약되었습니다. ",Toast.LENGTH_LONG).show();
                        _intent = new Intent(getApplicationContext(),Main.class);
                        startActivity(_intent);
                    } // 예약이 성공적으로 이루어 질 경우 (예약이 안겹치고 오픈 시간일때 예약을 하거나 )
                    else if(result.equals("false&over")){
                        Toast.makeText(getApplicationContext(),"영업시간 외에 시간을 선택하셨습니다. 다시 입력하세요 ",Toast.LENGTH_LONG).show();
                    }
                    else if(result.equals("false&null")){
                        Toast.makeText(getApplicationContext(),"입력되지 않은 값이 존재합니다.  ",Toast.LENGTH_LONG).show();
                    }
                    else if(result.equals("false&today")){
                        Toast.makeText(getApplicationContext(),"당일 예약은 불가능 합니다.   ",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"에러 ",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
}
