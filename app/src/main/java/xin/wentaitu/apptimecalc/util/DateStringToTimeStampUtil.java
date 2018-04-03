package xin.wentaitu.apptimecalc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringToTimeStampUtil {

    public static void main(String[] args) {
//
        System.out.println(getTimeStamp("2018-03-28 09:10"));
    }

    // 将字符串转为时间戳 传入：2018-03-28 09:10
    public static String getTimeStamp(String timeStamp) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d;
        try {
            d = sdf.parse(timeStamp);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }


    // 将时间戳转为字符串    传入毫秒1522199403724
    public static String getTimeString(String timeString) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lcc_time = Long.valueOf(timeString);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }

}
