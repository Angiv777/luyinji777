package com.maple.recordwav;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 *
 */
public class DateUtils {
    /**
     * 将日期按照指定格式输出
     *
     * @param fmt 指定格式，例如 "yyyy-MM-dd HH:mm:ss"
     * @return 格式化后的日期字符串
     */
    public static String date2Str(String fmt) {
        return date2Str(new Date(), fmt);
    }

    /**
     * 将日期按照指定格式输出
     *
     * @param date 日期对象
     * @param fmt  指定格式，例如 "yyyy-MM-dd HH:mm:ss"
     * @return 格式化后的日期字符串
     */
    public static String date2Str(Date date, String fmt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(fmt, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 将字符串转换为日期对象
     *
     * @param strDate 字符串日期
     * @param fmt     日期格式，例如 "yyyy-MM-dd HH:mm:ss"
     * @return 转换后的日期对象，如果转换失败则返回 null
     */
    public static Date str2Date(String strDate, String fmt) {
        SimpleDateFormat df = new SimpleDateFormat(fmt, Locale.getDefault());
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
