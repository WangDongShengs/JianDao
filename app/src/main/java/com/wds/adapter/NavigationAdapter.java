package com.wds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wds.bean.HomeTabBean;
import com.wds.jiandao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {
    private List<HomeTabBean.DataBean.ListBean> list = new ArrayList<>();
    private Context context;

    public NavigationAdapter(List<HomeTabBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_navigation, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeTabBean.DataBean.ListBean listBean = list.get(i);
        viewHolder.homeDrawerItemTvValue.setText(listBean.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_drawer_item_iv_tag)
        ImageView homeDrawerItemIvTag;
        @BindView(R.id.home_drawer_item_tv_value)
        TextView homeDrawerItemTvValue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
