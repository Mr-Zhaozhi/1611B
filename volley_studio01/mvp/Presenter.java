package com.bawei.volley_studio01.mvp;

import android.content.Context;

/*Time:2019/4/16
 *Author:zhaozhiwei
 *Description:
 */public class Presenter implements Contract.IPresenter {


    private Model model;
    private Contract.IView iView;

    @Override
    public void onAttachVieww(Contract.IView iView) {
        model = new Model();
        this.iView = iView;
    }

    @Override
    public void onDeachVieww() {

    }

    @Override
    public void ChuanData(Context context,String url) {
        model.PlaceData(context,url, new Contract.IModel.PCallBack() {
            @Override
            public void onSucceed(String data) {
                iView.getShow(data);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
