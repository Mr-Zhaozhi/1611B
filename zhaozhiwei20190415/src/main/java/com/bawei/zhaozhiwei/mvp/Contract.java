package com.bawei.zhaozhiwei.mvp;

/*Time:2019/4/15
 *Author:zhaozhiwei
 *Description:契约类
 */public class Contract {
     //View层
     public interface IView{
         void getView(String data);
         void getZhu(String data);
    }
    //model层
    public interface IModel{
         void getData(String name,String pwd,PCallback pCallback);
         interface PCallback{
             void onSuceed(String data);
             void onFail(String error);
         }
        void ZhuData(String name, String pwd, ZCallback zCallback);
        interface ZCallback{
            void onSuceed(String data);
            void onFail(String error);

        }
    }
    //Present层
    public interface IPresenter{
         void onAttachView(IView iView);
         void onDeachView();
         void place(String  name,String pass);
         void placeData(String  name,String pass);

     }

}
