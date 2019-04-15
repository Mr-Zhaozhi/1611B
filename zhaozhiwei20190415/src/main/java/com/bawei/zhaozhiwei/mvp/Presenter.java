package com.bawei.zhaozhiwei.mvp;

/*Time:2019/4/15
 *Author:zhaozhiwei
 *Description:mvp中Present层
 */public class Presenter implements Contract.IPresenter {

    private Model model;
    private Contract.IView iView;
    //绑定
    @Override
    public void onAttachView(Contract.IView iView) {
        this.iView = iView;
        model = new Model();
    }
//解决内存泄漏
    @Override
    public void onDeachView() {
        if (iView!=null){
            iView=null;
        }
        if (model!=null){
            model=null;
        }
    }
//登录
    @Override
    public void place(String name, String pass) {
        model.getData(name, pass, new Contract.IModel.PCallback() {
            @Override
            public void onSuceed(String data) {
                iView.getView(data);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
//注册
    @Override
    public void placeData(String name, String pass) {
        model.ZhuData(name, pass, new Contract.IModel.ZCallback() {
            @Override
            public void onSuceed(String data) {
                iView.getZhu(data);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
