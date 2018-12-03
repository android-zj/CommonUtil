package com.zj.library;

/**
 * 保留小数位数
 */
public class KeepUtils {
    public static String doubuleKeepOne(double one) {//保留一位小数
        java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.0");
        String str = myformat.format(one);
        return str;
    }

    public static Double doubuleKeepSix(double six) {//保留6位小数
        java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.000000");
        String str = myformat.format(six);
        Double temp = Double.valueOf(str);
        return temp;
    }
}
