package com.example.jooyoung.db_project;

public class Waiting_list {
    String store_id;
    String mount;

    public Waiting_list(){
        store_id = null;
        mount = null;
    }
    public Waiting_list(String store_id,String mount){
        this.store_id = store_id;
        this.mount = mount;
    }

    public String get_store_id(){return store_id;}
    public String get_mount(){return mount;}
    public void set_store_id(String store_id){this.store_id = store_id;}
    public void set_mount(String mount){this.mount = mount;}
}
