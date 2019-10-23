package com.example.jooyoung.db_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Category_view extends AppCompatActivity {

    ArrayList<Category_list> _list = new ArrayList<>();
    ArrayList<Category_list> title_list = new ArrayList<>();
    ArrayList<Category_list> wait = new ArrayList<>();
    ArrayList<MyGroup> cate_list = null;
    ArrayList<MyGroup> title = null;
    JSPCategory category,waiting;
    String id,name,f_id,opentime,phone,FLR = null;
    ExpandableListView cate;
    String sendmsg , result = null;
    Intent _intent;
    LinearLayout store_search ;


    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        cate = (ExpandableListView)findViewById(R.id.category);
        category = new JSPCategory();
        cate_list = new ArrayList<MyGroup>();
        String type = "category";
        sendmsg = "type=" + type;

        try {
            result = category.execute(sendmsg).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("결과",result);

        String sp1[] = result.split("%");
        int len_sp1 = sp1.length;
        for(int i=0;i<len_sp1;i++){
            String sp2[] = sp1[i].split("@");
           _list.add(new Category_list(sp2[0],sp2[1],sp2[2],sp2[3],sp2[4],sp2[5]));
        }

        waiting = new JSPCategory();
        type = "waiting";
        sendmsg = "type=" + type;
        try {
            result = waiting.execute(sendmsg).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("search 결과(전체)",result);

        String sp3[] = result.split("%");
        final int len_sp3 = sp3.length;
        for(int i=0;i<len_sp3;i++){
            String sp4[] = sp3[i].split("@");
            wait.add(new Category_list(sp4[0],sp4[1],sp4[2],sp4[3],sp4[4],sp4[5],sp4[6]));
        } // 웨이팅 받아오려고 쓴 부분


        final String[] Store = {"카페/베이커리", "델리/아이스크림" ,"맛집", "레스토랑", "패스트푸드", "홈데코/디지털"
                , "뷰티" ,"문구/팬시", "글로벌패션" ,"여성패션" ,"남성패션" ,"편집샵", "캐주얼" ,"스포츠/아웃도어"
                ,"아동", "이너웨어" ,"패션잡화","기타"};
        String[] f_id = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"};
        final int count[] = new int[18];
        int check=0 ;
        for(int j=0;j<18;j++){
            MyGroup temp = new MyGroup(Store[j]);
            for(int k=0;k<len_sp1;k++){
                if(_list.get(k).get_fid().equals(f_id[j])){
                    temp.add(_list.get(k).get_name());
                    check++;
                }
            }
            count[j] = check;
            cate_list.add(temp);
        }

        final BaseExpandableAdater Adater = new BaseExpandableAdater(getApplicationContext(),R.layout.parent_cate,R.layout.child_cate,cate_list);
        cate.setAdapter(Adater);

        store_search = (LinearLayout) findViewById(R.id.search_store);
        store_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _intent = new Intent(getApplicationContext(),SearchActivity.class);

                startActivity(_intent);

            }
        });


        cate.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                int location=0;

                if(groupPosition == 0) {
                    location = childPosition;
                    _intent = new Intent(getApplicationContext(), Store_information.class);

                    _intent.putExtra("name", _list.get(location).get_name());
                    _intent.putExtra("opentime", _list.get(location).get_opentime());
                    _intent.putExtra("phone", _list.get(location).get_phone());
                    _intent.putExtra("FLR", _list.get(location).get_FLR());
                    _intent.putExtra("id",_list.get(location).get_id());
                    _intent.putExtra("fid",_list.get(location).get_fid());
                    for(int k=0;k<len_sp3;k++){
                        if(wait.get(k).get_id().equals(_list.get(location).get_id())){
                            _intent.putExtra("waiting",wait.get(k).get_waiting());
                        }
                    }
                    _intent.putExtra("store_category",Store[groupPosition]);
                }
                else{
                    location = count[groupPosition] - count[groupPosition-1] - childPosition;
                    _intent = new Intent(getApplicationContext(),Store_information.class);
                    _intent.putExtra("name",_list.get(count[groupPosition]-location).get_name());
                    _intent.putExtra("opentime",_list.get(count[groupPosition]-location).get_opentime());
                    _intent.putExtra("phone",_list.get(count[groupPosition]-location).get_phone());
                    _intent.putExtra("FLR",_list.get(count[groupPosition]-location).get_FLR());
                    _intent.putExtra("id",_list.get(count[groupPosition]-location).get_id());
                    _intent.putExtra("fid",_list.get(count[groupPosition]-location).get_fid());
                    for(int k=0;k<len_sp3;k++){
                        if(wait.get(k).get_id().equals(_list.get(count[groupPosition]-location).get_id())){
                            _intent.putExtra("waiting",wait.get(k).get_waiting());
                        }
                    }
                    _intent.putExtra("store_category",Store[groupPosition]);
                }

                startActivity(_intent);

                return false;
            }
        });


    }


}

