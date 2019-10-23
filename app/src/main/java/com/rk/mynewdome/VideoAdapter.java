package com.rk.mynewdome;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class VideoAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<VideoInfo> mVideoInfos;

    VideoAdapter(Context context, List<VideoInfo> videoInfos) {
        this.mVideoInfos = videoInfos;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mVideoInfos.size();
    }

    @Override
    public VideoInfo getItem(int position) {
        return mVideoInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoInfoHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.local_video_list_item, parent, false);
            holder = new VideoInfoHolder();
            holder.ivData = convertView.findViewById(R.id.iv_data);
            holder.tvArtist = convertView.findViewById(R.id.tv_artist);
            holder.tvAlbum = convertView.findViewById(R.id.tv_album);
            convertView.setTag(holder);
        } else {
            holder = (VideoInfoHolder) convertView.getTag();
        }

        VideoInfo videoInfo = getItem(position);
        holder.ivData.setImageBitmap(BitmapFactory.decodeFile(videoInfo.thumbnailData));
        holder.tvArtist.setText(videoInfo.artist);
        holder.tvAlbum.setText(videoInfo.album);

        return convertView;
    }

    private static final class VideoInfoHolder {
        ImageView ivData;
        TextView tvArtist;
        TextView tvAlbum;
    }
}
