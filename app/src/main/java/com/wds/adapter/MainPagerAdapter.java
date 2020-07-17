package com.wds.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wds.jiandao.HomeActivity;
import com.wds.jiandao.MainActivity;
import com.wds.jiandao.R;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends PagerAdapter {
    private Context context;
    List<Integer> list ;

    public MainPagerAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pager_item, null);
        container.addView(inflate);
        ImageView imageView = inflate.findViewById(R.id.iv_pager_item);
        TextView textView = inflate.findViewById(R.id.tv_pager_item);
        Button button = inflate.findViewById(R.id.btn_pager_item);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);

            }
        });
        if (position==list.size()-1){
            button.setVisibility(View.VISIBLE);
        }else {
            button.setVisibility(View.GONE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickItem!=null){
                    clickItem.item(position);
                }
            }
        });
        imageView.setImageResource(list.get(position));

        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }
    onClickItem clickItem;

    public void setClickItem(onClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public interface onClickItem {
        void item(int i);
    }
}
