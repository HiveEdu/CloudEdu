package com.myedu.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
public class TimeCalc {
    public String  value(String newTime,String oldTime) throws ParseException {
        // TODO Auto-generated method stub
        String dateline = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf1.parse(oldTime);
        Date d2 = sdf1.parse(newTime);
        dateline = String.valueOf(daysBetween(d1, d2))+"天前"; //获取两个时间的天数差
        if(daysBetween(d1, d2)==0){     //判定是否是同一天，不是一天的话进行下一步判断
            //判定一天内的间隔时间
            dateline = getTimeGap(oldTime.substring(oldTime.length()-8),newTime.substring(newTime.length()-8));
        }else if(daysBetween(d1, d2)==1){  //相差一天，就返回昨天
            dateline = "昨天";
        }
        return dateline;   //相差两天以上
    }

    public static String getTimeGap(String oldTime,String currentTime) {

        String hDes = "";
        String mDes = "";
        String[] newtime = currentTime.split(":");
        int newH = Integer.parseInt(newtime[0]);//记录小时
        int newM = Integer.parseInt(newtime[1]);//记录分钟
        int newS = Integer.parseInt(newtime[2]);//记录秒钟

        String[] oldtime = oldTime.split(":");
        int oldH = Integer.parseInt(oldtime[0]);//功能如上
        int oldM = Integer.parseInt(oldtime[1]);
        int oldS = Integer.parseInt(oldtime[2]);

        int h = newH - oldH;
        int m = newM - oldM;
        int i = 0;
        int k = 0;
        if (0 < h) {
            if (0 < m) {
                hDes = h + "小时";
                mDes = m + "分钟";
            } else if (0 > m) {
                i = 60 - oldM + newM;
                mDes = i + "分钟";
                if (1 < h) {
                    k = h - 1;
                    hDes = k + "小时";
                }
            } else if (0 == m) {
                hDes = h + "小时";
            }
        } else if (0 < m) {
            mDes = m + "分钟";
        }
        if((hDes + mDes).equals("")){
            return newS-oldS+"秒前";    //小时为0，则返回秒
        }
        if(Integer.valueOf(hDes.substring(0, hDes.indexOf("小时")))>1){   //如果小时不为0，则只返回小时
            return hDes.substring(0, hDes.indexOf("小时"))+"小时前";
        }
        return hDes + mDes + "前";
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

}
