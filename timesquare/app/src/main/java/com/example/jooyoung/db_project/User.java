package com.example.jooyoung.db_project;

public class User {
    String id, password,name,phone,login_check;
    public User(){
        id = null;
        password = null;
        name = null;
        phone = null;
        login_check = "false";
    }

    public User(String id,String password,String name, String phone){
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        login_check = "true";
    }

    public User(String id,String password){
        this.id = id;
        this.password = password;
        this.name = null;
        this.phone = null;
        login_check = "true";
    }

    public String get_id(){return id;}
    public String get_password(){return password;}
    public String get_name(){return name;}
    public String get_phone(){return phone;}
    public String get_check(){return login_check;}
    public void set_id(String id){this.id = id;}
    public void set_password(String password){this.password = password;}
    public void set_name(String name){this.name = name;}
    public void set_phone(String phone){this.phone = phone;}
    public void set_check(String check){this.login_check = check;}

}
