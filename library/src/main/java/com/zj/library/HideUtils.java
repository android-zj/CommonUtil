package com.zj.library;

import android.text.TextUtils;

public class HideUtils {

    /**
     *  手机号中间四位隐藏
     * @param phone  手机号
     * @return
     */
    public static String phoneHide(String phone){
        String phoneHide = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return    phoneHide;
    }

    /**
     * 身份证中间八位隐藏
     * 隐藏出生年月
     * @param idCard  身份证号码
     * @return
     */
    public static String idCardHide(String idCard){
        String replaceAll = idCard.replaceAll("(\\d{6})\\d{8}(\\w{4})", "$1*****$2");
        return replaceAll;
    }

    /**
     * 名字姓用*替代
     */
    public static String nameHide(String name){
        String s = "";
        if(!TextUtils.isEmpty(name)&&name.length()>0){
             s = "*" + name.substring(1,name.length());//substring含头不含尾(0开始,这里取1和小于name的下标,如字符长度为3取 下标1和2，就是长度 2和3 这样不会下标越界)
        }
        return  s;
    }

}
