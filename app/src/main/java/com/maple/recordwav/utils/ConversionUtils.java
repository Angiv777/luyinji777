package com.maple.recordwav.utils;

/**
 * 度量衡换算工具类
 * <p>
 */
public class ConversionUtils {
    private static final String[] unitList = new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};

    public static final ConversionUtils instance = null;

    public static String convertB(Long B) {
        return convertSize(B != null ? (double) B : null, 0);
    }

    public static String convertB(Double B) {
        return convertSize(B, 0);
    }

    public static String convertKB(Double KB) {
        return convertSize(KB, 1);
    }

    public static String convertMB(Double MB) {
        return convertSize(MB, 2);
    }

    /**
     * 内存大小单位换算
     *
     * @param size 大小
     * @param unit 单位
     */
    private static String convertSize(Double size, int unit) {
        if (size == null)
            return "--";
        int curUnit = unit;
        double curSize = size;
        while (curSize > 1024) {
            curUnit++;
            curSize /= 1024;
        }
        return String.format("%.2f", curSize) + " " + unitList[curUnit];
    }
}