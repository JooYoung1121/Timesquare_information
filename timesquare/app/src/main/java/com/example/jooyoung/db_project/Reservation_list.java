package com.example.jooyoung.db_project;

public class Reservation_list {
    String id;
    String store_id;
    String store_name;
    String user_id;
    String date;
    String time;
    String user_name;

    public Reservation_list(){
        id = null;
        store_id = null;
        store_name = null;
        date = null;
        time = null;
        user_id = null;
        user_name = null;
    }
    public Reservation_list(String store_id, String store_name,String user_id, String date,String time,String id){
        this.id = id;
        this.store_id = store_id;
        this.store_name = store_name;
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        user_name = null;
    }
    public Reservation_list(String store_id, String store_name,String user_id, String date,String time,String id,String user_name){
        this.id = id;
        this.store_id = store_id;
        this.store_name = store_name;
        this.user_id = user_id;
        this.date = date;
        this.time = time;
        this.user_name = user_name;
    }

    public String get_id(){return id;}
    public String get_store_id(){return store_id;}
    public String get_store_name(){return store_name;}
    public String get_date(){return date;}
    public String get_time(){return time;}
    public String get_user_id(){return user_id;}
    public String get_user_name(){return user_name;}
    public void set_id(String id){this.id = id;}
    public void set_store_id(String store_id){this.store_id = store_id;}
    public void set_store_name(String store_name){this.store_name = store_name;}
    public void set_date(String date){this.date = date;}
    public void set_tims(String time){this.time = time;}
    public void set_user_id(String user_id){this.user_id = user_id;}
}
