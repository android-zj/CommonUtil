package com.zj.library;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 身份证号码检测类
 */
public class IdCardUtils {
    // 位权值数组
    private static byte[] Wi = new byte[17];
    // 身份证前部分字符数
    private static final byte fPart = 6;
    // 身份证算法求模关键值
    private static final byte fMod = 11;
    // 旧身份证长度
    private static final byte oldIDLen = 15;
    // 新身份证长度
    private static final byte newIDLen = 18;
    // 新身份证年份标志
    private static final String yearFlag = "19";
    // 校验码串
    private static final String CheckCode = "10X98765432";
    // 最小的行政区划码
    private static final int minCode = 150000;
    // 最大的行政区划码
    private static final int maxCode = 700000;

    // 旧身份证号码
    // private String oldIDCard="";
    // 新身份证号码
    // private String newIDCard="";
    // 地区及编码

    // private String Area[][2] =
    private static void setWiBuffer() {
        for (int i = 0; i < Wi.length; i++) {
            int k = (int) Math.pow(2, (Wi.length - i));
            Wi[i] = (byte) (k % fMod);
        }
    }

    // 获取新身份证的最后一位:检验位
    private static String getCheckFlag(String idCard) {
        int sum = 0;
        // 进行加权求和
        for (int i = 0; i < 17; i++) {
            sum += Integer.parseInt(idCard.substring(i, i + 1)) * Wi[i];
        }
        // 取模运算，得到模值
        byte iCode = (byte) (sum % fMod);
        return CheckCode.substring(iCode, iCode + 1);
    }

    // 判断串长度的合法性
    private static boolean checkLength(final String idCard) {
        return (idCard.length() == oldIDLen) || (idCard.length() == newIDLen);
    }

    // 获取时间串
    private static String getIDDate(final String idCard, boolean newIDFlag) {
        String dateStr;
        if (newIDFlag)
            dateStr = idCard.substring(fPart, fPart + 8);
        else
            dateStr = yearFlag + idCard.substring(fPart, fPart + 6);
        return dateStr;
    }

    // 判断时间合法性
    @SuppressLint("SimpleDateFormat")
    private static boolean checkDate(final String dateSource) {
        String dateStr = dateSource.substring(0, 4) + "-" + dateSource.substring(4, 6) + "-" +
                dateSource.substring(6, 8);
        System.out.println(dateStr);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            Date date = df.parse(dateStr);
            return (date != null);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 旧身份证转换成新身份证号码
     */
    public static String getNewIDCard(final String oldIDCard) {
        // 初始化方法
        IdCardUtils.setWiBuffer();
        if (!checkIDCard(oldIDCard)) {
            return oldIDCard;
        }
        String newIDCard = oldIDCard.substring(0, fPart);
        newIDCard += yearFlag;
        newIDCard += oldIDCard.substring(fPart, oldIDCard.length());
        String ch = getCheckFlag(newIDCard);
        newIDCard += ch;
        return newIDCard;
    }

    /**
     * 新身份证转换成旧身份证号码
     */
    public static String getOldIDCard(final String newIDCard) {
        // 初始化方法
        IdCardUtils.setWiBuffer();
        if (!checkIDCard(newIDCard)) {
            return newIDCard;
        }
        return newIDCard.substring(0, fPart) +
                newIDCard.substring(fPart + yearFlag.length(), newIDCard.length() - 1);
    }

    /**
     * 判断身份证号码的合法性
     */
    public static boolean checkIDCard(final String idCard) {
        // 初始化方法
        IdCardUtils.setWiBuffer();
        // String message = "";
        if (!checkLength(idCard)) {
            // message = "ID长度异常";
            Log.v("", "ID长度异常");
            return false;
        }
        String idDate = getIDDate(idCard, true);
        if (!checkDate(idDate)) {
            // message = "ID时间异常";
            Log.v("", "ID时间异常");
            return false;
        }
        String checkFlag = getCheckFlag(idCard);
        String theFlag = idCard.substring(idCard.length() - 1, idCard.length());
        if (!checkFlag.equals(theFlag)) {
            // message = "新身份证校验位异常";
            Log.v("", "新身份证校验位异常");
            return false;
        }
        return true;
    }

    /**
     * 获取一个随机的"伪"身份证号码
     */
    public static String getRandomIDCard(final boolean idNewID) {
        // 初始化方法
        IdCardUtils.setWiBuffer();
        Random ran = new Random();
        String idCard = getAddressCode(ran) + getRandomDate(ran, idNewID) + getIDOrder(ran);
        if (idNewID) {
            String ch = getCheckFlag(idCard);
            idCard += ch;
        }
        return idCard;
    }

    // 产生随机的地区编码
    private static String getAddressCode(Random ran) {
        if (ran == null) {
            return "";
        } else {
            int addrCode = minCode + ran.nextInt(maxCode - minCode);
            return Integer.toString(addrCode);
        }
    }

    // 产生随机的出生日期
    private static String getRandomDate(Random ran, boolean idNewID) {
        if (ran == null) {
            return "";
        }
        int year;
        if (idNewID) {
            year = 1900 + ran.nextInt(2007 - 1900);
        } else {
            year = 1 + ran.nextInt(99);
        }
        int month = 1 + ran.nextInt(12);
        int day;
        if (month == 2) {
            day = 1 + ran.nextInt(28);
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 ||
                month == 12) {
            day = 1 + ran.nextInt(31);
        } else {
            day = 1 + ran.nextInt(30);
        }
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMaximumIntegerDigits(2);
        nf.setMinimumIntegerDigits(2);
        return Integer.toString(year) + nf.format(month) + nf.format(day);
    }

    // 产生随机的序列号
    private static String getIDOrder(Random ran) {
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMaximumIntegerDigits(3);
        nf.setMinimumIntegerDigits(3);
        if (ran == null) {
            return "";
        } else {
            int order = 1 + ran.nextInt(999);
            return nf.format(order);
        }
    }

    public IdCardUtils() {
        setWiBuffer();
    }
}
