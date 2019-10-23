package com.example.jooyoung.db_project;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.String;
import java.net.URI;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FloorMapActivity extends AppCompatActivity {

    public Button b2f, b1f, _1f, _2f, _3f, _4f, _5f;
    public ImageView floor_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_map);

        floor_image = (ImageView)findViewById(R.id.floor_map_image);



        Button.OnClickListener onClickListener = new Button.OnClickListener(){

            public void onClick(View v){
                switch (v.getId()){
                    case R.id.floor_b2f_button :
                        floor_image.setImageResource(R.drawable.shop_b2f);
                        changeBtnColor(0);
                        break;
                    case R.id.floor_b1f_button :
                        floor_image.setImageResource(R.drawable.shop_b1f);
                        changeBtnColor(1);
                        break;
                    case R.id.floor_1f_button :
                        floor_image.setImageResource(R.drawable.shop_1f);
                        changeBtnColor(2);
                        break;
                    case R.id.floor_2f_button :
                        floor_image.setImageResource(R.drawable.shop_2f);
                        changeBtnColor(3);
                        break;
                    case R.id.floor_3f_button :
                        floor_image.setImageResource(R.drawable.shop_3f);
                        changeBtnColor(4);
                        break;
                    case R.id.floor_4f_button :
                        floor_image.setImageResource(R.drawable.shop_4f);
                        changeBtnColor(5);
                        break;
                    case R.id.floor_5f_button :
                        floor_image.setImageResource(R.drawable.shop_5f);
                        changeBtnColor(6);
                        break;
                }
            }
        };

        b2f = (Button)findViewById(R.id.floor_b2f_button);
        b2f.setOnClickListener(onClickListener);
        b1f = (Button)findViewById(R.id.floor_b1f_button);
        b1f.setOnClickListener(onClickListener);
        _1f = (Button)findViewById(R.id.floor_1f_button);
        _1f.setOnClickListener(onClickListener);
        _2f = (Button)findViewById(R.id.floor_2f_button);
        _2f.setOnClickListener(onClickListener);
        _3f = (Button)findViewById(R.id.floor_3f_button);
        _3f.setOnClickListener(onClickListener);
        _4f = (Button)findViewById(R.id.floor_4f_button);
        _4f.setOnClickListener(onClickListener);
        _5f = (Button)findViewById(R.id.floor_5f_button);
        _5f.setOnClickListener(onClickListener);
    }

    public void changeBtnColor (int oper){
        b2f.setBackgroundColor(getResources().getColor(R.color.gray_blue));
        b1f.setBackgroundColor(getResources().getColor(R.color.gray_blue));
        _1f.setBackgroundColor(getResources().getColor(R.color.gray_blue));
        _2f.setBackgroundColor(getResources().getColor(R.color.gray_blue));
        _3f.setBackgroundColor(getResources().getColor(R.color.gray_blue));
        _4f.setBackgroundColor(getResources().getColor(R.color.gray_blue));
        _5f.setBackgroundColor(getResources().getColor(R.color.gray_blue));

        switch (oper){
            case 0:b2f.setBackgroundColor(getResources().getColor(R.color.strong_blue));
                break;
            case 1:b1f.setBackgroundColor(getResources().getColor(R.color.strong_blue));
                break;
            case 2:_1f.setBackgroundColor(getResources().getColor(R.color.strong_blue));
                break;
            case 3:_2f.setBackgroundColor(getResources().getColor(R.color.strong_blue));
                break;
            case 4:_3f.setBackgroundColor(getResources().getColor(R.color.strong_blue));
                break;
            case 5:_4f.setBackgroundColor(getResources().getColor(R.color.strong_blue));
                break;
            case 6:_5f.setBackgroundColor(getResources().getColor(R.color.strong_blue));
                break;
        }
    }
}
