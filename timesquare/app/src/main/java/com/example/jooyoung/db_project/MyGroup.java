package com.example.jooyoung.db_project;

import java.util.ArrayList;

public class MyGroup {

    public ArrayList<String> child;
    public String groupName;
    MyGroup(String name){
        groupName = name;
        child = new ArrayList<String>();
    }
    MyGroup(String name,String id , String _name, String f_id, String opentime, String phone, String FLR){
        groupName = name;
        child = new ArrayList<String>();
        child.add(id);
        child.add(_name);
        child.add(f_id);
        child.add(opentime);
        child.add(phone);
        child.add(FLR);

    }
    MyGroup(String name,String id , String _name,String opentime, String phone, String FLR){
        groupName = name;
        child = new ArrayList<String>();
        child.add(id);
        child.add(_name);
        child.add(opentime);
        child.add(phone);
        child.add(FLR);

    }
    public void add(String _name)
    {
        child.add(_name);
    }

}
