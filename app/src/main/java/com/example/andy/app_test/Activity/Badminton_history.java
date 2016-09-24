package com.example.andy.app_test.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andy.app_test.DrawersGod;
import com.example.andy.app_test.R;


public class Badminton_history extends DrawersGod {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badminton_history);
        findId();
        go();
    }

    @Override
    public LinearLayout tagLayout() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.badminton_history_container);
        return layout;
    }

    private void findId() {
        textView = (TextView) findViewById(R.id.tv_history);

    }

    private void go() {
        setTitle("羽球介紹");
        StringBuilder message = new StringBuilder();

        message.append("『歷史記載』\n上能找到的最早的羽毛球是毽子，中國人在公元前5世紀開始踢毽子，不過這是用腳的運動。而後，一種稱為Battledore and Shuttlecock，類似羽毛球的遊戲曾在歐亞兩地流行了近二千年。 \n")
                .append("後來日本亦自創了一種羽毛球，球板以木製成，球則是而櫻桃核加上羽毛製成，不過由於製造成本太貴，而且球身太重，速度太快，不便於打擊，因此漸漸在日本消失。 \n")
                .append("19世紀中葉，印度出現了現代羽毛球運動的前身「Poona」，並傳至英國。1870年代，現代羽毛球在英國的Badminton莊園正式問世。第一部羽毛球運動的比賽規則於1877年在印度的Karachi地區制訂。所以羽毛球運動也有人稱為「Indian Game」。 \n")
                .append("1893年，英國羽毛球協會成立，重新修訂並統一了羽毛球比賽的規則。1934年，第一個世界性的羽毛球組織——國際羽毛球聯合會在英國成立。1981年與成立於1978年的世界羽毛球聯合會合併，成立了現今的國際羽毛球聯合會。從此羽毛球運動開始在世界各國普及，截至1996年6月，「國際羽聯」已有124個會員國或地區。 \n")
                .append("1992年，首次成為奧運會比賽項目。 \n\n")
                .append("『羽球的球速』\n")
                .append("「扣殺」是羽球中最有威力的擊球方式，會使羽球向下墜落至對手的中場位置。被扣殺的羽球的最高速率可超越任何其他涉及球拍的球類運動的最高球速。須注意的是，所謂「最高速率」是指羽球剛離開球拍後的初速，此後羽球球速下降得非常快。\n")
                .append("\n")
                .append("目前羽球的最快球速紀錄為馬來西亞羽球雙打選手陳文宏在2009年9月日本公開賽中創造。陳文宏雖在首輪即慘遭淘汰，但在賽場外卻意外獲得廠商的新產品測試機會，沒想到因禍得福，以421公里殺球時速改寫日本好手川上直樹保有的414公里的金氏世界紀錄。陳文宏的記錄是由旁人餵球而且不用考慮下網、出界等情況下全力殺球的極速，而正式比賽進行中因為必須考慮殺球成功率所以極速會慢些。單打比賽中最快的球速為310公里/小時，由韓國選手李炫一於2006年世界錦標賽準決賽中對陣中國選手鮑春來時創下，其次為馬來西亞選手李宗偉所創下的307公里/小時。反而由世界羽聯所記錄的最快殺球球速 - 由印尼選手陶菲克所創下的305公里/小時[11]，其實只是世界上第三快的殺球罷了。雙打比賽的記錄為中國選手傅海峰所保持的332km/h，混雙記錄為中國選手張軍的317km/h，均在2005年蘇迪曼盃創下。\n")
                .append("目前羽球的最快球速紀錄為馬來西亞羽球雙打選手陳文宏在2009年9月日本公開賽中創造。陳文宏雖在首輪即慘遭淘汰，但在賽場外卻意外獲得廠商的新產品測試機會，沒想到因禍得福，以421公里殺球時速改寫日本好手川上直樹保有的414公里的金氏世界紀錄。陳文宏的記錄是由旁人餵球而且不用考慮下網、出界等情況下全力殺球的極速，而正式比賽進行中因為必須考慮殺球成功率所以極速會慢些。單打比賽中最快的球速為310公里/小時，由韓國選手李炫一於2006年世界錦標賽準決賽中對陣中國選手鮑春來時創下，其次為馬來西亞選手李宗偉所創下的307公里/小時。反而由世界羽聯所記錄的最快殺球球速 - 由印尼選手陶菲克所創下的305公里/小時[11]，其實只是世界上第三快的殺球罷了。雙打比賽的記錄為中國選手傅海峰所保持的332km/h，混雙記錄為中國選手張軍的317km/h，均在2005年蘇迪曼盃創下。\n")
                .append("\n")
                .append("但是，在2013年7月28日。日本優乃克（Yonex）邀請馬來西亞的李宗偉，陳文宏及田兒賢一來共同測試新球拍的極限。結果最後由左手持拍的陳文宏以這款專門為極速殺球所開發的球拍，打出時速493公里的新世界紀錄，取得金氏世界紀錄（Guinness World Record）的認證。李宗偉也打破陳文宏之前的記錄，測得時速456公里，但最後的\"速球王\"的頭銜還是由陳文宏保持，他打出時速達493公里的新世界紀錄。");


        textView.setText(message);

    }


    public void onclick(View view) {
        finish();
    }
}
