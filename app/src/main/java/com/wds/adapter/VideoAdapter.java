package com.wds.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.wds.bean.VideoBean;
import com.wds.jiandao.R;
import com.wds.jiandao.WebActivity;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context context;
    private List<VideoBean.DataBean.ListBean> listBeans = new ArrayList<>();

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public void setListBeans(List<VideoBean.DataBean.ListBean> listBeans) {
        this.listBeans.addAll(listBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.video_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final VideoBean.DataBean.ListBean listBean = listBeans.get(i);
        viewHolder.tvVideo.setText(listBean.getTheme());
        viewHolder.tvClVideo.setText(listBean.getDescription());
        //饺子播放器
        viewHolder.jzVideoPlayerStandard.setUp(listBean.getVideo_url(),listBean.getTheme());

        //设置图片的url
        Glide.with(context).load(listBean.getImage_url()).into(viewHolder.jzVideoPlayerStandard.thumbImageView);
        viewHolder.tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("link",listBean.getLink());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        JzvdStd jzVideoPlayerStandard;
        TextView tvVideo;
        TextView tvClVideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jzVideoPlayerStandard=itemView.findViewById(R.id.vv_cl_video);
            tvVideo=itemView.findViewById(R.id.tv_video);
            tvClVideo=itemView.findViewById(R.id.tv_cl_video);
        }
    }

}
