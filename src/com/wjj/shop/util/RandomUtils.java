package com.wjj.shop.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class RandomUtils {

    public static String getTime(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
    }
    public static String getActiveCode(){
        return getTime()+Integer.toHexString(new Random().nextInt(900)+100);
    }
}
