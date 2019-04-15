package com.bawei.zhaozhiwei;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView name;
    private String name1;

    @SuppressLint({"WrongConstant", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.im);
        sharedPreferences = getSharedPreferences("User", Activity.MODE_APPEND);
        name1 = sharedPreferences.getString("name2", "name2");
        String pass = sharedPreferences.getString("pass2", "pass2");
        Log.i("AA",name.toString());
        init();
    }

    private void init() {
        name.setText(name1);
    }
}
