package com.example.andy.app_test.Adaptor;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.andy.app_test.R;
import java.util.List;

/**
 * Created by user on 2016/9/17.
 */


//繼承RecyclerView.Adapter <???> ，<???>需要一個Holder
public class RecyclerViewAdapter_mainMenu
        extends RecyclerView.Adapter<RecyclerViewAdapter_mainMenu.MainMenuViewHolder> {

    private List<Bitmap> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;



    //內部類別 Holder 並繼承
    public static class MainMenuViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_icon;

        public MainMenuViewHolder(View itemView) {
            super(itemView);

            iv_icon = (ImageView)itemView.findViewById(R.id.item_main_recycler_view_ivItem);
        }
    }

    public RecyclerViewAdapter_mainMenu(List<Bitmap> list) {
        this.list = list;
    }

    //Observer interface (自制RecyclerView 監聽器)
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int postion);
    }


    // CreateViewHolder
    @Override
    public MainMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_recycler_view, parent, false);

        return new MainMenuViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MainMenuViewHolder holder, final int position) {

        holder.iv_icon.setImageBitmap(list.get(position));
        holder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnItemClickListener != null) {
                    //注意這邊使用tag的方法獲取資訊
                    mOnItemClickListener.onItemClick(v,position);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return 4;
    }

    //外部類別使用： (放入一個監聽器)
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
