package com.example.andy.app_test.Adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.andy.app_test.R;
import com.example.andy.app_test.model.Getter_SportBean_service;
import com.example.andy.app_test.myapp.MyApp;


/**
 * Created by user on 2016/5/25.
 */
public class RecyclerViewAdapter_sportCenter extends RecyclerView.Adapter<RecyclerViewAdapter_sportCenter.MyViewHolder> {

    //1.實做一個Holder出來 (創建一個內部類別 並 extends RecyclerView.ViewHolder)
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView m_tv_center_name;
        private TextView m_tv_center_closeday;
        private TextView m_tv_center_fax;
        private TextView m_tv_center_mail;
        private TextView m_tv_center_positon;
        private TextView m_tv_center_MAP;
        private TextView m_tv_center_導航;
        private TextView m_tv_center_phone;
        private ImageView m_iv_center_front;
        private ImageView m_iv_center_inner;


        public MyViewHolder(View itemView) {
            super(itemView);
            m_tv_center_name = (TextView) itemView.findViewById(R.id.item_recycler_view_tv_center_name);
            m_tv_center_closeday = (TextView) itemView.findViewById(R.id.item_recycler_view_tv_center_closeday);
            m_tv_center_fax = (TextView) itemView.findViewById(R.id.item_recycler_view_tv_center_fax);
            m_tv_center_mail = (TextView) itemView.findViewById(R.id.item_recycler_view_tv_center_mail);
            m_tv_center_phone = (TextView) itemView.findViewById(R.id.item_recycler_view_tv_center_phone);
            m_tv_center_positon = (TextView) itemView.findViewById(R.id.item_recycler_view_tv_center_positon);
            m_tv_center_MAP = (TextView) itemView.findViewById(R.id.item_recycler_view_tv_center_MAP);
            m_tv_center_導航 = (TextView) itemView.findViewById(R.id.item_recycler_view_tv_center_導航);
            m_iv_center_front = (ImageView) itemView.findViewById(R.id.item_recycler_view_iv_front);
//            m_iv_center_inner = (ImageView) itemView.findViewById(R.id.item_recycler_view_iv_inner);


        }
    }

    //2.實現RecyclerView.Adapter的三個抽象方法


    @Override//建立Holider (Inflater=吹氣，Inflate=膨脹)
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //先設定View(把布局在畫面中膨脹出來)
        //創造出一個Holider並把View塞進去 (用Holider記住這些東西)
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new MyViewHolder(v);

    }

    @Override//設定View的資料
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //我們已經在Myapp存取了TaipeiAttractions，所以用getTaipeiAttractions()就可取出
        Getter_SportBean_service SportBean_services = MyApp.getGetterSportBeanservice();

        //如果取得的資料為空值，標題就顯示"資料空白 "+position(第幾頁)
        if (SportBean_services == null) {
            holder.m_tv_center_name.setText("稍等 下載資料中 " + position);
            return;
        }
        //設定景點名稱 跟其他資訊
        holder.m_tv_center_name.setText(SportBean_services.get_center_name(position));
        holder.m_tv_center_closeday.setText("營業時間：" + SportBean_services.get_center_opentime(position));
        holder.m_tv_center_fax.setText("傳真：" + SportBean_services.get_center_fax(position));
        holder.m_tv_center_mail.setText(Html.fromHtml("<font color=\"#FFFFFF\"> E-MAIL： </font>" + "<u>" + SportBean_services.get_center_mail(position) + "</u>"));
        if (SportBean_services.get_center_address(position).substring(5).length() < 9) {
            holder.m_tv_center_positon.setText("位置：" + SportBean_services.get_center_address(position));
        } else {
            holder.m_tv_center_positon.setText("位置：" + SportBean_services.get_center_address(position).substring(5));
        }
        holder.m_tv_center_MAP.setTag(SportBean_services.get_center_name(position));
        holder.m_tv_center_導航.setTag(SportBean_services.get_center_address(position).substring(5));
        holder.m_tv_center_phone.setText(Html.fromHtml("<font color=\"#FFFFFF\"> 電話： </font>" + "<u>" + SportBean_services.get_center_phone(position) + "</u>"));

        Context context = holder.m_iv_center_front.getContext();
//        Context context2 = holder.m_iv_center_inner.getContext();

        String image_front = SportBean_services.get_center_front(position);
//        String image_inner = SportBean_services.get_center_inner(position);


        Glide.with(context)
                .load(image_front)
                .placeholder(R.drawable.icon_glide0)
                .diskCacheStrategy(DiskCacheStrategy.RESULT )
                .into(holder.m_iv_center_front);

//        Glide.with(context2)
//                .load(image_inner)
////                .placeholder(R.drawable.icon_glide)
//                .diskCacheStrategy(DiskCacheStrategy.NONE )
//                .into(holder.m_iv_center_inner);


    }

    @Override
    public int getItemCount() {
        Getter_SportBean_service SportBean_services = MyApp.getGetterSportBeanservice();
        if (SportBean_services == null) {
            return 2;
        }
        return SportBean_services.getBean().getResult().getCount();
    }


}
