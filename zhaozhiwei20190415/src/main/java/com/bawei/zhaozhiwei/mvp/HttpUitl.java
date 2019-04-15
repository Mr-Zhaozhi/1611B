package com.bawei.zhaozhiwei.mvp;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.common.io.ByteStreams;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/*Time:2019/4/15
 *Author:zhaozhiwei
 *Description:
 */public class HttpUitl {
     private static final HttpUitl instance = new HttpUitl();

     //饿汉式
     public static HttpUitl getInstance(){
         return instance;
    }
    //POSt请求
    public void Post(final String url, final String name, final String pwd, final NetCallback netCallback){
         new AsyncTask<String,Void,String>(){
             @Override
             protected String doInBackground(String... strings) {

                 return PostDataHttp(strings[0],strings[1],strings[2]);
             }

             @Override
             protected void onPostExecute(String s) {
                 super.onPostExecute(s);
                 if (!TextUtils.isEmpty(s)){
                     netCallback.onSucceed(s);
                 }else {
                     netCallback.onFail(503,"未找到");
                 }
             }
         }.execute(url,name,pwd);

    }
    private String PostDataHttp(String url,String name,String pwd){
        HttpURLConnection httpURLConnection = null;
        try {
            URL url1 = new URL(url);
            httpURLConnection = (HttpURLConnection) url1.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(5000);
            //请求头
            String body="phone=" + URLEncoder.encode(name)+"&pwd="+URLEncoder.encode(pwd);
            httpURLConnection.getOutputStream().write(body.getBytes());
            if (httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                String s = new String(ByteStreams.toByteArray(inputStream));
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    //回调接口
     interface NetCallback{
         void onSucceed(String data);
         void onFail(int i,String data);
     }
}
