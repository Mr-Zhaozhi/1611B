package com.bawei.volley_studio01.volley;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bawei.volley_studio01.application.Appli;

/*Time:2019/4/16
 *Author:zhaozhiwei
 *Description:
 */public class VolleyHttp {

     private static Context context;
     private static VolleyHttp volleyHttp;
     //懒汉式
    public static synchronized VolleyHttp getInstance(){
        if (volleyHttp==null){
            volleyHttp = new VolleyHttp();
        }
        return volleyHttp;
    }
    public boolean isNetWorkConneted(Context context){
        if (context!=null){
            ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = systemService.getActiveNetworkInfo();
            if (networkInfo!=null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
     //GEt请求
    public void VolleyHttpGet(String url, final NetCallBack netCallBack){
       StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netCallBack.onSucceed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netCallBack.onFaile(error);
            }
        });
        stringRequest.setTag("testPost");
        Appli.getVolley().add(stringRequest);
    }


    public interface NetCallBack{
        void onSucceed(String data);
        void onFaile(VolleyError error);
    }



}
