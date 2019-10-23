package com.example.jooyoung.db_project;

public class Category_list {
    String id;
    String name;
    String f_id;
    String opentime;
    String phone;
    String FLR;
    String waiting;

    public Category_list()
    {
        id = null;
        name = null;
        f_id = null;
        opentime = null;
        phone = null;
        FLR = null;
        waiting = null;
    }

    public Category_list(String id , String name, String f_id, String opentime, String phone, String FLR,String waiting)
    {
        this.id = id;
        this.name = name;
        this.f_id = f_id;
        this.opentime = opentime;
        this.phone = phone;
        this.FLR = FLR;
        this.waiting = waiting;
    }
    public Category_list(String id , String name, String f_id, String opentime, String phone, String FLR)
    {
        this.id = id;
        this.name = name;
        this.f_id = f_id;
        this.opentime = opentime;
        this.phone = phone;
        this.FLR = FLR;
    }
    public Category_list(String name){
        this.name = name;
    }


    public String get_id(){return id;}
    public String get_name(){return name;}
    public String get_fid(){return f_id;}
    public String get_opentime(){return opentime;}
    public String get_phone(){return phone;}
    public String get_FLR(){return FLR;}
    public String get_waiting(){return waiting;}
    public void set_id(String id){this.id = id;}
    public void set_name(String name){this.name = name;}
    public void set_opentime(String opentime){this.opentime = opentime;}
    public void set_phone(String phone){this.phone = phone;}
    public void set_FLR(String FLR){this.FLR = FLR;}
    public void set_waiting(String waiting){this.waiting = waiting;}

}
