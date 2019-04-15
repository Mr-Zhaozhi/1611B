package com.bawei.zhaozhiwei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.zhaozhiwei.bean.ZhuBean;
import com.bawei.zhaozhiwei.mvp.Contract;
import com.bawei.zhaozhiwei.mvp.Presenter;
import com.google.gson.Gson;

public class ZhuActivity extends AppCompatActivity implements Contract.IView {

    private Button mButton;
    private EditText name,pass;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        mButton = findViewById(R.id.mButton1);
        pass = findViewById(R.id.mPass1);
        name = findViewById(R.id.mUser1);
        //初始化Present
        presenter = new Presenter();
        presenter.onAttachView(this);
        //注册监听
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = ZhuActivity.this.name.getText().toString();
                String pass1 = ZhuActivity.this.pass.getText().toString();
                presenter.placeData(name1,pass1);
            }
        });

    }

    @Override
    public void getView(String data) {

    }

    @Override
    public void getZhu(String data) {
        //解析
        Gson gson = new Gson();
        ZhuBean zhuBean = gson.fromJson(data, ZhuBean.class);
        String message = zhuBean.getMessage();
        String status = zhuBean.getStatus();
        if (data!=null){
            if (status.equals("0000")){
                //成功跳转
                Toast.makeText(this,message.toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ZhuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                //失败吐司
                Toast.makeText(this,message.toString(),Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"返回数据为空",Toast.LENGTH_LONG).show();

        }

    }
}
