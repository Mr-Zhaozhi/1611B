package com.bawei.zhaozhiwei.mvp;

/*Time:2019/4/15
 *Author:zhaozhiwei
 *Description:M层
 */public class Model implements Contract.IModel {
    String Base_url="http://172.17.8.100/small/user/v1/login";
    String url="http://172.17.8.100/small/user/v1/register";
    //登录请求数据
     @Override
    public void getData(String name, String pwd, final PCallback pCallback) {
         HttpUitl.getInstance().Post(Base_url, name, pwd, new HttpUitl.NetCallback() {
             @Override
             public void onSucceed(String data) {
                 pCallback.onSuceed(data);
             }

             @Override
             public void onFail(int i, String data) {

             }
         });
     }
    //注册请求数据

    @Override
    public void ZhuData(String name, String pwd, final ZCallback zCallback) {
        HttpUitl.getInstance().Post(url, name, pwd, new HttpUitl.NetCallback() {
            @Override
            public void onSucceed(String data) {
                zCallback.onSuceed(data);
            }

            @Override
            public void onFail(int i, String data) {

            }
        });
    }
}
