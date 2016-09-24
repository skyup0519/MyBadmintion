package com.example.andy.app_test.model;


import com.example.andy.app_test.bean.Bean;

import java.util.List;

/**
 * Created by user on 2016/5/25.
 */

//（目的） 為什麼要做這一個model 因為Bean的東西太複雜
//        使用的人使用不需要這麼麻煩，所以我做一個小弟出來處理Bean
public class Getter_SportBean_service {
    private Bean bean;

    private List<Bean.ResultBean.ResultsBean> attractions;

    public Getter_SportBean_service(Bean bean) {
        this.attractions = bean.getResult().getSport_Centers();
        this.bean = bean;

    }

    public List<Bean.ResultBean.ResultsBean> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Bean.ResultBean.ResultsBean> attractions) {
        this.attractions = attractions;
    }

    public Bean getBean() {
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }
    public String get_center_name(int index){

        return bean.getResult().getSport_Centers().get(index).getO_tlc_agency_name();
    }
    public String get_center_mail(int index){

        return bean.getResult().getSport_Centers().get(index).getO_tlc_agency_email();
    }
    public String get_center_fax(int index){

        return bean.getResult().getSport_Centers().get(index).getO_tlc_agency_fax();
    }
    public String get_center_address(int index){

        return bean.getResult().getSport_Centers().get(index).getO_tlc_agency_address();
    }
    public String get_center_opentime(int index){

        return bean.getResult().getSport_Centers().get(index).getO_tlc_agency_opentime();
    }
    public String get_center_phone(int index){

        return bean.getResult().getSport_Centers().get(index).getO_tlc_agency_phone();
    }
    public  String get_center_front(int index){
        return bean.getResult().getSport_Centers().get(index).getO_tlc_agency_img_front();
    }

    public  String get_center_inner(int index){
        return bean.getResult().getSport_Centers().get(index).getO_tlc_agency_img_inner();
    }
}
