package com.bawei.volley_studio01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.volley_studio01.MainActivity;
import com.bawei.volley_studio01.R;
import com.bawei.volley_studio01.bean.JsonBean;
import com.bumptech.glide.Glide;

import java.util.List;

/*Time:2019/4/16
 *Author:zhaozhiwei
 *Description:
 */   public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {

     Context context;
     List<JsonBean.ResultBean> list;

    public ShowAdapter(Context context, List<JsonBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).getMasterPic()).into(viewHolder.image);
        viewHolder.text.setText(list.get(i).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.mImage);
            text = itemView.findViewById(R.id.mText);

        }
    }
}
