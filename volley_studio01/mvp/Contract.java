package com.bawei.volley_studio01.mvp;

import android.content.Context;

/*Time:2019/4/16
 *Author:zhaozhiwei
 *Description:
 */public class Contract {
     public interface IView{
         void getShow(String data);
     }
     //model层
    public interface IModel{
         void PlaceData(Context context,String url, PCallBack pCallBack);
         interface PCallBack{
             void onSucceed(String data);
             void onFail(String error);
         }
     }
     //P层
    public interface IPresenter{
         void onAttachVieww(IView iView);
         void onDeachVieww();
         void ChuanData(Context context,String url);
     }
}
