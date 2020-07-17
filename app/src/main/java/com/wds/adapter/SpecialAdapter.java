package com.wds.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wds.bean.SpecialBean;
import com.wds.jiandao.R;
import com.wds.jiandao.WebActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SpecialBean.DataBean.BannerListBean> banner_bean = new ArrayList<>();
    private List<SpecialBean.DataBean.ListBean> list_bean = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    private static final int BANNER = 0;
    private static final int LIST = 1;

    public SpecialAdapter(Context context) {
        this.context = context;
    }

    public void setBanner_Bean(List<SpecialBean.DataBean.BannerListBean> banner_bean) {
        this.banner_bean.addAll(banner_bean);
        notifyDataSetChanged();
        if (banner_bean.size() > 0 && list.size() == 0) {
            for (SpecialBean.DataBean.BannerListBean bannerListBean : banner_bean) {
                list.add(bannerListBean.getTheme());
            }
        }
    }

    public void setList_bean(List<SpecialBean.DataBean.ListBean> list_bean) {
        this.list_bean.addAll(list_bean);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && banner_bean.size() > 0) {
            return BANNER;
        } else {
            return LIST;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case BANNER:
                View inflate = LayoutInflater.from(context).inflate(R.layout.special_banner_itme, viewGroup, false);
                return new BannerViewHolder(inflate);
            case LIST:
                inflate = LayoutInflater.from(context).inflate(R.layout.special_list_itme, viewGroup, false);
                return new ListViewHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        switch (itemViewType) {
            case BANNER:
                final BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
                bannerViewHolder.bannerSpecialItem.setBannerTitles(list)
                        .setImages(banner_bean)
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                SpecialBean.DataBean.BannerListBean bean = (SpecialBean.DataBean.BannerListBean) path;
                                Glide.with(context).load(bean.getImage_url()).into(imageView);
                            }
                        }).start();
                bannerViewHolder.bannerSpecialItem.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int i) {
                        bannerViewHolder.progressBar.setProgress(i+1);
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
                break;
            case LIST:
                if (banner_bean.size()>0){
                    i=i-1;
                }
                final SpecialBean.DataBean.ListBean listBean = list_bean.get(i);
                ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
                listViewHolder.tvSpecialName.setText(listBean.getColumn_name());
                listViewHolder.tvSpecialTheme.setText(listBean.getTheme());
                Glide.with(context).load(listBean.getImage_url()).into(listViewHolder.ivSpecialItem);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra("link",listBean.getLink());
                        context.startActivity(intent);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        int size = banner_bean.size() > 0 ? list_bean.size() + 1 : list_bean.size();
        return size;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner bannerSpecialItem;
        ProgressBar progressBar;
        public BannerViewHolder(View inflate) {
            super(inflate);
            bannerSpecialItem = inflate.findViewById(R.id.banner_special_item);
            progressBar=inflate.findViewById(R.id.pb_special_item);
        }
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSpecialItem;
        TextView tvSpecialTheme;
        TextView tvSpecialName;

        public ListViewHolder(View inflate) {
            super(inflate);
           ivSpecialItem=inflate.findViewById(R.id.iv_special_item);
           tvSpecialTheme=inflate.findViewById(R.id.tv_special_theme);
           tvSpecialName=inflate.findViewById(R.id.tv_special_name);
        }
    }
}
