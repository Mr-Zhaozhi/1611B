package com.bawei.volley_studio01;

import android.content.Context;
import android.media.midi.MidiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bawei.volley_studio01.adapter.ShowAdapter;
import com.bawei.volley_studio01.bean.JsonBean;
import com.bawei.volley_studio01.mvp.Contract;
import com.bawei.volley_studio01.mvp.Presenter;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.IView {

    private Presenter presenter;
    String url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?&page=1&count=30&keyword=%E7%94%B7%E9%9E%8B";
    private RecyclerView mRecy;
    private ShowAdapter showAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecy = findViewById(R.id.mRecy);

        presenter = new Presenter();
        presenter.onAttachVieww(this);
        presenter.ChuanData(this,url);
    }

    @Override
    public void getShow(String data) {
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(data, JsonBean.class);
        List<JsonBean.ResultBean> result = jsonBean.getResult();
        showAdapter = new ShowAdapter(MainActivity.this,result);
        mRecy.setAdapter(showAdapter);

        gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecy.setLayoutManager(gridLayoutManager);
    }


}
