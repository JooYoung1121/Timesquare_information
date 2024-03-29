package com.example.jooyoung.db_project;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSPReservation extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;

    @Override
    protected String doInBackground(String... strings) {
        try{
            String str;
            URL url = new URL("http://172.16.212.184:8080/tibero_Connection/tibero_reservation.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            sendMsg = strings[0];

            osw.write(sendMsg);
            osw.flush();

            if(conn.getResponseCode()==conn.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer  = new StringBuffer();

                while ((str=reader.readLine())!=null){
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            }
            else{
                Log.i("통신 결과", conn.getResponseCode()+"에러");
            }
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return receiveMsg;
    }
}