package com.example.andy.app_test.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 2016/5/24.
 */
public class Bean {


    /**
     * offset : 0
     * limit : 10000
     * count : 10
     * sort :
     * results : [{"_id":"1","o_tlc_agency_name":"臺北市中正運動中心","o_tlc_agency_category":"2","o_tlc_agency_categorychild":"運動中心","o_tlc_agency_purpose":"提供多元、豐富、安全且平價的運動空間，鼓勵市民養成長期運動的良好習慣、培育自我健康管理能力。館內設有室內溫水游泳池、體適能健身中心、多功能綜合球場、桌球室、舞蹈教室、高爾夫球練習場、撞球場、10米空氣槍射擊場、羽球場及30米射箭場。","o_tlc_agency_service":"個人運動休閒、運動課程及營隊辦理、各項表演活動、記者會、大型運動競賽、企業職工體育活動、健康諮詢、檢測、體能訓練。","o_tlc_agency_region":"11","o_tlc_agency_opentime":"每日6:00-22:00，農曆除夕及初一休館","o_tlc_agency_address":"10048臺北市中正區信義路1段1號","o_tlc_agency_phone":"(02) 3322-5016","o_tlc_agency_fax":"(02) 3322-5019","o_tlc_agency_email":"jjservice@mail.jjsports.com.tw","o_tlc_agency_admincategory":"","o_tlc_agency_img_front":"","o_tlc_agency_img_inner":"","o_tlc_agency_link":"http://www.jjsports.com.tw/"}]
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int offset;
        private int limit;
        private int count;
        private String sort;
        /**
         * _id : 1
         * o_tlc_agency_name : 臺北市中正運動中心
         * o_tlc_agency_category : 2
         * o_tlc_agency_categorychild : 運動中心
         * o_tlc_agency_purpose : 提供多元、豐富、安全且平價的運動空間，鼓勵市民養成長期運動的良好習慣、培育自我健康管理能力。館內設有室內溫水游泳池、體適能健身中心、多功能綜合球場、桌球室、舞蹈教室、高爾夫球練習場、撞球場、10米空氣槍射擊場、羽球場及30米射箭場。
         * o_tlc_agency_service : 個人運動休閒、運動課程及營隊辦理、各項表演活動、記者會、大型運動競賽、企業職工體育活動、健康諮詢、檢測、體能訓練。
         * o_tlc_agency_region : 11
         * o_tlc_agency_opentime : 每日6:00-22:00，農曆除夕及初一休館
         * o_tlc_agency_address : 10048臺北市中正區信義路1段1號
         * o_tlc_agency_phone : (02) 3322-5016
         * o_tlc_agency_fax : (02) 3322-5019
         * o_tlc_agency_email : jjservice@mail.jjsports.com.tw
         * o_tlc_agency_admincategory :
         * o_tlc_agency_img_front :
         * o_tlc_agency_img_inner :
         * o_tlc_agency_link : http://www.jjsports.com.tw/
         */
        @SerializedName("results")
        private List<ResultsBean> Sport_Centers;

        public List<ResultsBean> getSport_Centers() {
            return Sport_Centers;
        }

        public void setSport_Centers(List<ResultsBean> Sport_Centers) {
            this.Sport_Centers = Sport_Centers;
        }

        public int getOffset() {
            return offset;
        }



        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }



        public static class ResultsBean {
            private String _id;
            private String o_tlc_agency_name;
            private String o_tlc_agency_opentime;
            private String o_tlc_agency_address;
            private String o_tlc_agency_phone;
            private String o_tlc_agency_fax;
            private String o_tlc_agency_email;
            private String o_tlc_agency_img_front;
            private String o_tlc_agency_img_inner;

            public String getO_tlc_agency_img_front() {
                return o_tlc_agency_img_front;
            }

            public void setO_tlc_agency_img_front(String o_tlc_agency_img_front) {
                this.o_tlc_agency_img_front = o_tlc_agency_img_front;
            }

            public String getO_tlc_agency_img_inner() {
                return o_tlc_agency_img_inner;
            }

            public void setO_tlc_agency_img_inner(String o_tlc_agency_img_inner) {
                this.o_tlc_agency_img_inner = o_tlc_agency_img_inner;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getO_tlc_agency_name() {
                return o_tlc_agency_name;
            }

            public void setO_tlc_agency_name(String o_tlc_agency_name) {
                this.o_tlc_agency_name = o_tlc_agency_name;
            }

            public String getO_tlc_agency_opentime() {
                return o_tlc_agency_opentime;
            }

            public void setO_tlc_agency_opentime(String o_tlc_agency_opentime) {
                this.o_tlc_agency_opentime = o_tlc_agency_opentime;
            }

            public String getO_tlc_agency_address() {
                return o_tlc_agency_address;
            }

            public void setO_tlc_agency_address(String o_tlc_agency_address) {
                this.o_tlc_agency_address = o_tlc_agency_address;
            }

            public String getO_tlc_agency_phone() {
                return o_tlc_agency_phone;
            }

            public void setO_tlc_agency_phone(String o_tlc_agency_phone) {
                this.o_tlc_agency_phone = o_tlc_agency_phone;
            }

            public String getO_tlc_agency_fax() {
                return o_tlc_agency_fax;
            }

            public void setO_tlc_agency_fax(String o_tlc_agency_fax) {
                this.o_tlc_agency_fax = o_tlc_agency_fax;
            }

            public String getO_tlc_agency_email() {
                return o_tlc_agency_email;
            }

            public void setO_tlc_agency_email(String o_tlc_agency_email) {
                this.o_tlc_agency_email = o_tlc_agency_email;
            }
        }
    }
}
