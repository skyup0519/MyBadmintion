package com.example.andy.app_test.model;


import android.graphics.Bitmap;

//《功能》管理 比賽局數、分數的物件(比賽時間可能很長 我希望生命週期隨著app而消長)
public class Game_badminton_servicer {

    private  String Point_1_fraction;
    private  String Point_2_fraction;
    private  String Point_3_fraction;
    private  String name;
    private  Bitmap photo;

    //Constructor
    public Game_badminton_servicer(String point_1_fraction,
                                   String point_2_fraction,
                                   String point_3_fraction,
                                   String name,
                                   Bitmap photo) {
        Point_1_fraction = point_1_fraction;
        Point_2_fraction = point_2_fraction;
        Point_3_fraction = point_3_fraction;
        this.name = name;
        this.photo = photo;
    }

    //Getter and Seter
    public String getPoint_1_fraction() {
        return Point_1_fraction;
    }

    public void setPoint_1_fraction(String point_1_fraction) {
        Point_1_fraction = point_1_fraction;
    }

    public String getPoint_2_fraction() {
        return Point_2_fraction;
    }

    public void setPoint_2_fraction(String point_2_fraction) {
        Point_2_fraction = point_2_fraction;
    }

    public String getPoint_3_fraction() {
        return Point_3_fraction;
    }

    public void setPoint_3_fraction(String point_3_fraction) {
        Point_3_fraction = point_3_fraction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
