package com.bawei.volley_studio01.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/*Time:2019/4/16
 *Author:zhaozhiwei
 *Description:
 */public class Appli extends Application {

    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(getApplicationContext());

    }
    //不能忘括号
    public static RequestQueue getVolley(){
        return requestQueue;
    }
}
