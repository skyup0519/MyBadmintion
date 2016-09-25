package com.example.andy.app_test.model;


import java.util.ArrayList;
import java.util.List;

public class Main_badminton_servicer {


    //預設第一局　局數為1
    private static int current_typ_point = 1;

    //最大分數
    private  int max_fraction = 11;

    //最小分數
    private  int min_fraction = 0;

    //最大局數
    private  int max_point = 3;

    //最小局數
    private  int min_point = 1;


    private static Game_badminton_servicer[] list_service = new Game_badminton_servicer[2];

    private static List<String> viewpageUrl = new ArrayList<>();


    //Getter and Setter



    /**
     * MODE_SAVEDATA_UP = 0  (上面局數)
     * MODE_SAVEDATA_DOWN = 1 (下面局數)
     */
    //當前比賽的資料
    public static Game_badminton_servicer[] getList_service() {
        return list_service;
    }

    public static void setList_service(Game_badminton_servicer[] list_service) {
        Main_badminton_servicer.list_service = list_service;
    }

    //當前比賽的局數 start
    public static int getCurrent_typ_point() {
        return current_typ_point;
    }

    public static void setCurrent_typ_point(int current_typ_point) {
        Main_badminton_servicer.current_typ_point = current_typ_point;
    }


    //最大局數
    public int getMax_fraction() {
        return max_fraction;
    }

    public void setMax_fraction(int max_fraction) {
        this.max_fraction = max_fraction;
    }


    //最小局數
    public int getMin_fraction() {
        return min_fraction;
    }

    public void setMin_fraction(int min_fraction) {
        this.min_fraction = min_fraction;
    }

    //最大分數
    public int getMax_point() {
        return max_point;
    }

    public void setMax_point(int max_point) {
        this.max_point = max_point;
    }

    //最小分數
    public int getMin_point() {
        return min_point;
    }

    public void setMin_point(int min_point) {
        this.min_point = min_point;
    }



    //ViewPage　圖片網路位置 start
    public static List<String> getViewpageUrl() {
        return viewpageUrl;
    }

    public static void setViewpageUrl(List<String> viewpageUrl) {
        Main_badminton_servicer.viewpageUrl = viewpageUrl;
    }

    //SaveData 隊伍編號
    public static class SaveData {

        /**
         * 上方 隊伍編號
         */
        public static final int MODE_UP = 0 ;

        /**
         * 下方 隊伍編號
         */
        public static final int MODE_DOWN = 1 ;
    }


//    public static enum  ENUM_SAVE_DATA {
//        MODE_UP,
//        MODE_DOWN;
//    }
}
