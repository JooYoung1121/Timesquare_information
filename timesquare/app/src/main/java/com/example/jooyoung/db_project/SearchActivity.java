package com.example.jooyoung.db_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchActivity extends AppCompatActivity{
    ListView search_store;
    EditText input_store;
    Spinner spinner_cate;
    private List<String> Store = null;
    ArrayList<Category_list> _list = new ArrayList<>();
    ArrayList<Category_list> wait = new ArrayList<>();
    ArrayList<String> arraylist;
    SearchAdapter searchAdapter;
    String sendmsg,result;
    JSPSearch search_name,lasting;
    JSPCategory category,waiting;
    RelativeLayout re;
    InputMethodManager imm;
    Context ct;
    Intent _intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_store);
        search_store = (ListView)findViewById(R.id.search_store);
        input_store = (EditText)findViewById(R.id.input_store);
        spinner_cate = (Spinner)findViewById(R.id.spinner_cate);
        Store = new ArrayList<String>();
        category = new JSPCategory();
        arraylist = new ArrayList<String>();
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        ct = this;
        String type;
        type = "category";
        sendmsg = "type=" + type;
        try {
            result = category.execute(sendmsg).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("search 결과(전체)",result);

        String sp1[] = result.split("%");
        final int len_sp1 = sp1.length;
        for(int i=0;i<len_sp1;i++){
            String sp2[] = sp1[i].split("@");
            _list.add(new Category_list(sp2[0],sp2[1],sp2[2],sp2[3],sp2[4],sp2[5]));
        } // 전체 부분 받아오기 그냥 객체 생성 부분이라 생각

        String[] Store_name = {"전체","카페/베이커리", "델리/아이스크림" ,"맛집", "레스토랑", "패스트푸드", "홈데코/디지털"
                , "뷰티" ,"문구/팬시", "글로벌패션" ,"여성패션" ,"남성패션" ,"편집샵", "캐주얼" ,"스포츠/아웃도어"
                ,"아동", "이너웨어" ,"패션잡화","기타"};

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

        List<String> spinner_item = new ArrayList<>();
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(this,R.layout.simple_spinner_item,spinner_item);

        for(int i=0;i<19;i++){
            spinner_item.add(Store_name[i]);
        }
        spinner_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner_cate.setAdapter(spinner_adapter);
        final String[] cate = new String[1];

        spinner_cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cate[0] = parent.getItemAtPosition(position).toString();
                if(cate[0].equals("전체")){
                    search_store.setVisibility(View.VISIBLE);
                    Store.clear();
                    arraylist.clear();
                    search_name = new JSPSearch();
                    String temp;
                    temp = "store_name";
                    sendmsg = "type=" + temp;
                    try {
                        result = search_name.execute(sendmsg).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Log.i("이름만 받아온거", result);
                    String sp3[] = result.split("%");
                    for (int i = 0; i < sp3.length; i++) {
                        Store.add(sp3[i]);
                    }
                    arraylist.addAll(Store);
                    searchAdapter = new SearchAdapter(Store,ct);
                    search_store.setAdapter(searchAdapter); // 카테고리 출력 부분
                    imm.hideSoftInputFromWindow(input_store.getWindowToken(), 0);
                }
                else
                {
                    Store.clear();
                    arraylist.clear();
                    search_store.setVisibility(View.VISIBLE);
                    lasting = new JSPSearch();
                    sendmsg = "type=" + cate[0];
                    try {
                        result = lasting.execute(sendmsg).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    Log.i("분류선택", result);
                    String sp3[] = result.split("%");
                    for (int i = 0; i < sp3.length; i++) {
                        Store.add(sp3[i]);
                    }
                    arraylist.addAll(Store);
                    searchAdapter = new SearchAdapter(Store,ct);
                    search_store.setAdapter(searchAdapter); // 카테고리 출력 부분
                    imm.hideSoftInputFromWindow(input_store.getWindowToken(), 0);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                search_store.setVisibility(View.INVISIBLE);

            }
        });


        input_store.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = input_store.getText().toString();
                search(text);

            }
        });

        search_store.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup) view;
                TextView tv = (TextView)vg.findViewById(R.id.search_name);
                imm.hideSoftInputFromWindow(input_store.getWindowToken(), 0);
                for(int i=0;i<len_sp1;i++){
                    if(_list.get(i).get_name().equals(tv.getText().toString())){
                        _intent = new Intent(getApplicationContext(), Store_information.class);

                        _intent.putExtra("name", _list.get(i).get_name());
                        _intent.putExtra("opentime", _list.get(i).get_opentime());
                        _intent.putExtra("phone", _list.get(i).get_phone());
                        _intent.putExtra("FLR", _list.get(i).get_FLR());
                        _intent.putExtra("id",_list.get(i).get_id());
                        _intent.putExtra("fid",_list.get(i).get_fid());
                        for(int k=0;k<len_sp3;k++){
                            if(wait.get(k).get_id().equals(_list.get(i).get_id())){
                                _intent.putExtra("waiting",wait.get(k).get_waiting());
                            }
                        }
                        _intent.putExtra("store_category", cate[0]);
                    }
                }
                startActivity(_intent);

            }
        });

    }

    public void search(String charText){
        Store.clear();
        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            Store.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    Store.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        searchAdapter.notifyDataSetChanged();
    }
}
