package com.bawei.zhaozhiwei;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.zhaozhiwei.bean.JsonBean;
import com.bawei.zhaozhiwei.mvp.Contract;
import com.bawei.zhaozhiwei.mvp.Presenter;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements Contract.IView {

    private Button mButton;
    private EditText name,pass;
    private Presenter presenter;
    private TextView mText;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.mButton);
        pass = findViewById(R.id.mPass);
        name = findViewById(R.id.mUser);
        mText = findViewById(R.id.mText);
        presenter = new Presenter();
        presenter.onAttachView(this);
        //登录按钮
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = MainActivity.this.name.getText().toString();
                String pass1 = MainActivity.this.pass.getText().toString();
                presenter.place(name1,pass1);
            }
        });
        //注册按钮
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZhuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 初始化sharedPreferences
        sharedPreferences = getSharedPreferences("User", MODE_APPEND);
        edit = sharedPreferences.edit();


    }

    @Override
    public void getView(String data) {
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(data, JsonBean.class);
        String message = jsonBean.getMessage();
        String status = jsonBean.getStatus();
        if (data!=null){
            if (status.equals("0000")){
                Toast.makeText(this,message.toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //sp储存数据
                String name2 = MainActivity.this.name.getText().toString();
                String pass2 = MainActivity.this.pass.getText().toString();
                edit.putString("name2",name2);
                edit.putString("pass2",pass2);
                edit.commit();
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this,message.toString(),Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"返回数据为空",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void getZhu(String data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDeachView();
    }
}
