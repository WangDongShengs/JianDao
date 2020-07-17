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
import com.wds.bean.HomeBean;
import com.wds.custom.MyTextView;
import com.wds.jiandao.R;
import com.wds.jiandao.WebActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;


public class HomeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<HomeBean.DataBean.BannerListBean> banner_list = new ArrayList<>();
    private List<HomeBean.DataBean.FlashListBean> flash_list = new ArrayList<>();
    private List<HomeBean.DataBean.ArticleListBean> article_list = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    private static final int BANNER = 0;
    private static final int FALSH = 1;
    private static final int ARTICLE = 3;
    private static final int PAGER = 2;
    private static final int VIDEO = 4;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setBanner_list(List<HomeBean.DataBean.BannerListBean> banner_list) {
        this.banner_list.addAll(banner_list);
        notifyDataSetChanged();
        if (banner_list.size() > 0 && list.size() == 0) {
            for (HomeBean.DataBean.BannerListBean bannerListBean : banner_list) {
                list.add(bannerListBean.getTheme());
            }
        }
    }

    public void setFlash_list(List<HomeBean.DataBean.FlashListBean> flash_list) {
        this.flash_list.addAll(flash_list);
        notifyDataSetChanged();
    }

    public void setArticle_list(List<HomeBean.DataBean.ArticleListBean> article_list) {
        this.article_list.addAll(article_list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && banner_list.size() > 0) {
            return BANNER;
        } else if (position == 1 && flash_list.size() > 0) {
            return FALSH;
        }else if (article_list.get(position).getView_type()==2) {
            return PAGER;
        }else if(article_list.get(position).getView_type()==3) {
        return ARTICLE;
        }else if (article_list.get(position).getView_type()==4){
            return VIDEO;
        }
        return 3;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case BANNER:
                View inflate = LayoutInflater.from(context).inflate(R.layout.home_banner_itme, viewGroup, false);
                return new BannerViewHolder(inflate);

            case FALSH:
                inflate = LayoutInflater.from(context).inflate(R.layout.home_falsh_itme, viewGroup, false);
                return new FalshViewHolder(inflate);

            case PAGER:
                inflate = LayoutInflater.from(context).inflate(R.layout.home_pager_article_item, viewGroup, false);
                return new PagerViewHolder(inflate);

            case ARTICLE:
                inflate = LayoutInflater.from(context).inflate(R.layout.home_article_itme, viewGroup, false);
                return new ArticleViewHolder(inflate);


            case VIDEO:
                inflate = LayoutInflater.from(context).inflate(R.layout.home_video_article_item, viewGroup, false);
                return new VideoViewHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        switch (itemViewType){
            case BANNER:
                final BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
                bannerViewHolder.bannerHomeItem
                        .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                        .setBannerTitles(list)
                        .setImages(banner_list)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                HomeBean.DataBean.BannerListBean bean = (HomeBean.DataBean.BannerListBean) path;
                                Glide.with(context).load(bean.getImage_url()).into(imageView);
                            }
                        }).start();
                bannerViewHolder.bannerHomeItem.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    bannerViewHolder.progressBar.setProgress(i + 1);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
                break;
            case FALSH:
                String theme = "";
                HomeBean.DataBean.FlashListBean flashListBean = flash_list.get(i);
                for (int i1 = 0; i1 < flash_list.size(); i1++) {

                    theme+=flash_list.get(i1).getTheme()+"        ";
                }
                FalshViewHolder falshViewHolder = (FalshViewHolder) viewHolder;
                falshViewHolder.tvHomeFalsh.setText(theme);

                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, WebActivity.class);
                            intent.putExtra("link",flashListBean.getLink());
                            context.startActivity(intent);
                        }
                    });

                break;
            case ARTICLE:
                ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
                articleViewHolder.tvHomeFalshName.setText(article_list.get(i-1).getColumn_name());
                articleViewHolder.tvHomeFalshTheme.setText(article_list.get(i-1).getTheme());
                Glide.with(context).load(article_list.get(i-1).getImage_url()).into(articleViewHolder.ivHomeArticle);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra("link", article_list.get(i-1).getLink());
                        context.startActivity(intent);
                    }
                });
                break;
            case PAGER:
                PagerViewHolder pagerViewHolder = (PagerViewHolder) viewHolder;
                pagerViewHolder.tvHomePagerArticleItem.setText(article_list.get(i-1).getTheme());
                pagerViewHolder.tvClHomePagerArticleItem.setText(article_list.get(i-1).getColumn_name());
                Glide.with(context).load(article_list.get(i-1).getImage_url()).into(pagerViewHolder.ivHomePagerArticleItem);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra("link", article_list.get(i-1).getLink());
                        context.startActivity(intent);
                    }
                });
                break;
            case VIDEO:
                VideoViewHolder videoViewHolder = (VideoViewHolder) viewHolder;
                videoViewHolder.tvHomePagerArticleItem.setText(article_list.get(i-1).getTheme());
                videoViewHolder.tvClHomePagerArticleItem.setText(article_list.get(i-1).getColumn_name());
                //饺子播放器
                videoViewHolder.vvHomePagerArticleItem.setUp(article_list.get(i-1).getVideo_url(), article_list.get(i-1).getTheme());
                //设置图片的url
                Glide.with(context).load(article_list.get(i-1).getImage_url()).into(videoViewHolder.vvHomePagerArticleItem.thumbImageView);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra("link", article_list.get(i-1).getLink());
                        context.startActivity(intent);
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        int size = banner_list.size() > 0 ? article_list.size() + 1 : article_list.size();
        if (flash_list.size() > 0) {
            return ++size;
        }
        return size;
    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner bannerHomeItem;
        ProgressBar progressBar;

        public BannerViewHolder(View inflate) {
            super(inflate);
            bannerHomeItem = inflate.findViewById(R.id.banner_home_item);
            progressBar = inflate.findViewById(R.id.pb_home_item);
        }
    }

    private class FalshViewHolder extends RecyclerView.ViewHolder {
        MyTextView tvHomeFalsh;

        public FalshViewHolder(View inflate) {
            super(inflate);
            tvHomeFalsh = inflate.findViewById(R.id.tv_home_falsh);
        }
    }

    private class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHomeArticle;
        TextView tvHomeFalshTheme;
        TextView tvHomeFalshName;

        public ArticleViewHolder(View inflate) {
            super(inflate);
            ivHomeArticle = inflate.findViewById(R.id.iv_home_article);
            tvHomeFalshTheme = inflate.findViewById(R.id.tv_home_falsh_theme);
            tvHomeFalshName = inflate.findViewById(R.id.tv_home_falsh_name);
        }
    }

    private class PagerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHomePagerArticleItem;
        TextView tvHomePagerArticleItem;
        TextView tvClHomePagerArticleItem;

        public PagerViewHolder(View inflate) {
            super(inflate);
            ivHomePagerArticleItem = inflate.findViewById(R.id.iv_home_pager_article_item);
            tvHomePagerArticleItem = inflate.findViewById(R.id.tv_home_pager_article_item);
            tvClHomePagerArticleItem = inflate.findViewById(R.id.tv_cl_home_pager_article_item);
        }
    }

    private class VideoViewHolder extends RecyclerView.ViewHolder {
        JzvdStd vvHomePagerArticleItem;
        TextView tvHomePagerArticleItem;
        TextView tvClHomePagerArticleItem;

        public VideoViewHolder(View inflate) {
            super(inflate);
            vvHomePagerArticleItem = inflate.findViewById(R.id.vv_home_pager_article_item);
            tvHomePagerArticleItem = inflate.findViewById(R.id.tv_home_pager_article_item);
            tvClHomePagerArticleItem = inflate.findViewById(R.id.tv_cl_home_pager_article_item);
        }
    }

}
