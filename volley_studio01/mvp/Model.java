package com.bawei.volley_studio01.mvp;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bawei.volley_studio01.volley.VolleyHttp;

/*Time:2019/4/16
 *Author:zhaozhiwei
 *Description:
 */public class Model implements Contract.IModel {


    private boolean netWorkConneted;
    private Handler handler = new Handler();
    @Override
    public void PlaceData(final Context context, String url, final PCallBack pCallBack) {
        netWorkConneted = VolleyHttp.getInstance().isNetWorkConneted(context);
        if (netWorkConneted){

            VolleyHttp.getInstance().VolleyHttpGet(url, new VolleyHttp.NetCallBack() {
                @Override
                public void onSucceed(String data) {
                    pCallBack.onSucceed(data);
                }

                @Override
                public void onFaile(VolleyError error) {

                }
            });
        }else{
            Log.i("AA","没网");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context,"没网",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).start();//start不能忘！！！！
        }

    }


}
