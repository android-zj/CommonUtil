package com.zj.commonutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zj.library.BadgeUtils;
import com.zj.library.BankInfoUtils;
import com.zj.library.CopyUtils;
import com.zj.library.PhoneUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       initData();
    }

    private void initData() {
        //复制内容到粘贴板
        CopyUtils.copy(MainActivity.this, "复制到粘贴板的内容");
        //获取粘贴板内容
        CopyUtils.paste(MainActivity.this);

        //判断是不是手机号
        boolean isMobilePhone = PhoneUtils.isMobilePhone("1234567890");
        //判断是不是座机号
        boolean isTelephone = PhoneUtils.isTelephone("021-1545268");
        //判断是否是合格的身份证号
        if (!IdCardUtils.checkIDCard("16361111222233334444")) {
            ToastUtils.showToast(MainActivity.this, "请输入正确身份证号!");
            return;
        }

        try {
            //设置10个未读消息，设置为0可清除
            BadgeUtils.getInstence().setBadge(MainActivity.this, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //检测是不是银行卡号
        boolean isBankCard = BankInfoUtils.checkBankCard("62220202001121747777");
        //获取银行信息
        String bankInfo = BankInfoUtils.getBankInfo("62220202001121747777");

        //日期
        String seconds = "yyyy.MM.dd HH:mm:ss"; //年月日时分秒格式
        String minute = "yyyy-MM-dd HH:mm";   //年月日时分格式
        DataUtils.getDateToString(Long.parseLong("1537524997000"),minute);//时间戳转时间
        String mDate = "yyyy-MM-dd";     //年月日格式
        String mTime =  "HH:mm"; //时分格式
        DataUtils.getDateAndWeek(mDate,"1537524997000",mTime);//时间戳转  如2018-06-8 (星期五) 16:40格式
        DataUtils.secondToTime("1537524997000");//时间戳转时间 如打车根据距离计算出时间有  day-hour-minute/hour-minute/minute

        //隐藏
        HideUtils.phoneHide("18366668888");  //手机号中间四位隐藏
        HideUtils.idCardHide("622111122223333444");//身份证中间八位隐藏
        HideUtils.nameHide("王大军"); //姓用*替代

        //屏幕
        ScreenUtils.darkenBackground(MainActivity.this, 0.6f);//设置弹出弹框背景色
        ScreenUtils.darkenBackground(MainActivity.this, 1f);//弹框消失恢复背景色(1f不透明)
        ScreenUtils.getScreenWidth(MainActivity.this);//获取屏幕宽
        ScreenUtils.getScreenHigh(MainActivity.this);//获取屏幕高

        //保留小数
        KeepUtils.doubuleKeepOne(0.0003);//保留一位小数
        KeepUtils.doubuleKeepSix(0.000000003);//地图保留6位小数

        //toast工具
        ToastUtils.showToast(MainActivity.this,"666");

        //随机数
        RandomUtils.getRandom(10);

        //18dp转成px
        DisplayUtils.dip2px(MainActivity.this, 18);
        //18px转成dp
        DisplayUtils.px2dip(MainActivity.this, 18);
        //18px转成sp
        DisplayUtils.px2sp(MainActivity.this, 18);
        //18sp转成px
        DisplayUtils.sp2px(MainActivity.this, 18);
    }

}
