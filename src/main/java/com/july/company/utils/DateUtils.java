package com.july.company.utils;

import com.july.company.exception.BnException;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * @author zengxueqi
 * @program springboot-company
 * @since 2020-05-16 01:27
 **/
public class DateUtils {

    /**
     * 默认时期时间格式:yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式:yyyy-MM-dd
     */
    public static final String DEFAULT_DATE = "yyyy-MM-dd";

    /**
     * 默认时间格式:HH:mm:ss
     */
    public static final String DEFAULT_TIME = "HH:mm:ss";

    /**
     * 获取当前时间的指定格式的字符串:比如 HH:mm:ss
     * @param pattern 指定时间输出格式
     * @return 当前日期的字符传时间
     */
    public static String nowTime(String pattern) {
        LocalTime tt = LocalTime.now();
        return tt.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 当前时间增加小时数
     * @param pattern    模式
     * @param hoursToAdd 增加小时
     * @return 时间的字符传表示
     */
    public static String nowTimePlusHours(String pattern, int hoursToAdd) {
        LocalTime tt = LocalTime.now();
        return tt.plusHours(hoursToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 当前时间增加小时数
     * @param pattern      模式
     * @param minutesToAdd 增加分钟
     * @return 时间的字符传表示
     */
    public static String nowTimePlusMinutes(String pattern, int minutesToAdd) {
        LocalTime tt = LocalTime.now();
        return tt.plusMinutes(minutesToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 当前时间增加小时数
     * @param pattern      模式
     * @param secondstoAdd 增加秒
     * @return 时间的字符传表示
     */
    public static String nowTimePlusSeconds(String pattern, int secondstoAdd) {
        LocalTime tt = LocalTime.now();
        return tt.plusSeconds(secondstoAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的字符表示,默认格式: HH:mm:ss
     * @return 当前时间字符传
     */
    public static String nowTime() {
        return nowTime(DEFAULT_TIME);
    }

    /**
     * 获取当前日期指定格式的字符串,默认格式:比如 yyyy-MM-dd
     * @param pattern 指定日期输出格式
     * @return 字符传日期
     */
    public static String nowDate(String pattern) {
        LocalDate dd = LocalDate.now();
        return dd.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前日期字符串,默认格式: yyyy-MM-dd
     * @return 字符传日期
     */
    public static String nowDate() {
        return nowDate(DEFAULT_DATE);
    }

    /**
     * 获取当前日期+时间的指定格式的字符串:比如 yyyy-MM-dd HH:mm:ss
     * @param pattern 指定时间输出格式
     * @return 字符传时间日期
     */
    public static String nowDateTime(String pattern) {
        LocalDateTime dt = LocalDateTime.now();
        return dt.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前日期+时间的字符表示,默认格式: yyyy-MM-dd HH:mm:ss
     * @return 字符传日期时间
     */
    public static String nowDateTime() {
        return nowDateTime(DEFAULT_DATETIME);
    }

    /**
     * 获取本周开始时间
     * @return 时间
     */
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTime();
    }

    /**
     * 获取本周结束时间
     * @return 时间
     */
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        return cal.getTime();
    }

    /**
     * 获取一天中开始时间
     * @param date 时间对象
     * @return 时间开始时间 00:00:00
     */
    public static Date getStartDate(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取一天中结束时间
     * @param date 时间对象
     * @return 时间结束时间 23:59:59
     */
    public static Date getEndDate(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 时间转换为字符串
     * @param date   时间
     * @param format 转换格式
     * @return 时间字符传
     */
    public static String timeToStr(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_DATE;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 时间相加(按天)
     * @param date 日期
     * @param num  添加天数
     * @return 日期
     */
    public static Date dateAdd(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    /**
     * 01. java.util.Date -- java.time.LocalDateTime
     * @param date 传统日期
     * @return jdk8 日期时间
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        assert date != null;
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 日期类型转换
     * 秒表示的时间转换为LocalDateTime表示的时间
     * @param seconds 秒
     * @return LocalDateTime表示的时间
     */
    public static LocalDateTime toLocalDateTime(Long seconds) {
        return toLocalDateTime(new Date(seconds * 1000));
    }

    /**
     * 日期类型转换
     * 02. java.util.Date -- java.time.LocalDate
     * @param date 传统日期
     * @return jdk8 日期
     */
    public static LocalDate toLocalDate(Date date) {
        assert date != null;
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    /**
     * 日期类型转换
     * 02. Long -- java.time.LocalDate
     * @param seconds 日期的描述
     * @return jdk8 日期
     */
    public static LocalDate toLocalDate(Long seconds) {
        return toLocalDate(new Date(seconds * 1000));
    }

    /**
     * 日期类型转换
     * 03. java.util.Date -- java.time.LocalTime
     * @param date 传统日期
     * @return jdk8 时间
     */
    public static LocalTime toLocalTime(Date date) {
        assert date != null;
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.toLocalTime();
    }

    /**
     * 日期类型转换秒转换为时间
     * Long -- java.time.LocalTime
     * @param seconds 传统日期
     * @return jdk8 时间
     */
    public static LocalTime toLocalTime(Long seconds) {
        return toLocalTime(new Date(seconds));
    }

    /**
     * 日期类型转换
     * 04. java.time.LocalDateTime -- java.util.Date
     * @param localDateTime 日期时间
     * @return 传统日期对象
     */
    public static Date toDate(LocalDateTime localDateTime) {
        assert localDateTime != null;
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 长整数秒转换为日期
     * @param seconds 秒
     * @return 日期对象
     */
    public static Date toDate(Long seconds) {
        return new Date(seconds * 1000);
    }

    /**
     * 日期类型转换
     * 05. java.time.LocalDate -- java.util.Date
     * @param localDate 本地日期
     * @return 传统日期对象
     */
    public static Date toDate(LocalDate localDate) {
        assert localDate != null;
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 日期类型转换
     * 06. (LocalDate,LocalTime) -- java.util.Date
     * @param localDate 日期
     * @param localTime 时间
     * @return 返回老版本的Date
     */
    public static Date toDate(LocalDate localDate, LocalTime localTime) {
        assert localDate != null && localTime != null;
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 把日期时间类型转换为长整形的(秒)
     * localDateTime---Long
     * @param localDateTime 日期时间
     * @return 长整形的日期
     */
    public static Long toLong(LocalDateTime localDateTime) {
        assert localDateTime != null;
        return toDate(localDateTime).getTime() / 1000;
    }

    /**
     * 把日期时间类型转换为长整形的(秒)
     * localDate--Long
     * @param localDate 日期时间
     * @return 长整形的日期
     */
    public static Long toLong(LocalDate localDate) {
        assert localDate != null;
        return toDate(localDate).getTime() / 1000;
    }

    /**
     * 把日期时间类型转换为长整形的(秒)
     * @param date 日期时间
     * @return 长整形的日期
     */
    public static Long toLong(Date date) {
        assert date != null;
        return date.getTime() / 1000;
    }

    /**
     * 把日期+时间类型转换为长整形的(秒)
     * localDate+localTime --Long
     * @param localDate 日期
     * @param localTime 时间
     * @return 长整形的日期
     */
    public static Long toLong(LocalDate localDate, LocalTime localTime) {
        assert localDate != null && localTime != null;
        return toDate(localDate, localTime).getTime() / 1000;
    }

    /**
     * 把指定格式的字符串表示的日期转换为秒表示的长整形
     * @param source  字符串表示的日期，如果为空表示当前时间
     * @param pattern 字符串日期的格式, 如果为空表示yyyy-MM-dd HH:mm:ss格式
     * @param style   0: 原样转换, 1: 只保留年月日后面的都变为0, 2: 时分秒变为23:59:59
     * @return 秒表示的日期
     */
    public static Long format(String source, String pattern, int style) {
        try {
            if (!StringUtils.hasText(pattern)) {
                pattern = DEFAULT_DATETIME;
            }
            Date d1 = new Date(); //当前时间
            if (source != null) {
                DateFormat df = new SimpleDateFormat(pattern);
                d1 = df.parse(source);
                BnException.of(source.length() != pattern.length(), "格式和字符串不匹配");
            }
            switch (style) {
                case 1:
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(d1);
                    calendar1.set(Calendar.HOUR_OF_DAY, 0);
                    calendar1.set(Calendar.MINUTE, 0);
                    calendar1.set(Calendar.SECOND, 0);
                    calendar1.set(Calendar.MILLISECOND, 0);
                    return calendar1.getTime().getTime() / 1000;
                case 2:
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(d1);
                    calendar2.set(Calendar.HOUR_OF_DAY, 23);
                    calendar2.set(Calendar.MINUTE, 59);
                    calendar2.set(Calendar.SECOND, 59);
                    calendar2.set(Calendar.MILLISECOND, 0);
                    return calendar2.getTime().getTime() / 1000;
                default:
                    return d1.getTime() / 1000;
            }
        } catch (Exception e) {
            throw BnException.on("非格式化日期字符串");
        }
    }

    /**
     * 把指定格式的字符串表示的日期转换为秒表示的长整形
     * @param source  字符串表示的日期，如果为空表示当前时间
     * @param pattern 字符串日期的格式, 如果为空表示yyyy-MM-dd HH:mm:ss格式
     * @return 秒表示的日期
     */
    public static Long format(String source, String pattern) {
        return format(source, pattern, 0);
    }

    /**
     * 把指定格式的字符串(yyyy-MM-dd HH:mm:ss)表示的日期转换为秒表示的长整形
     * @param source 字符串表示的日期，如果为空表示当前时间
     * @return 秒表示的日期
     */
    public static Long format(String source) {
        return format(source, DEFAULT_DATETIME, 0);
    }

    /**
     * 把秒表示的时间转换为指定格式字符串
     * @param seconds 秒表示的时间，不指定为当前时间
     * @param pattern 转换后字符串的格式。不指定默认为yyyy-MM-dd HH:mm:ss格式
     * @param style   0: 原样转换, 1: 只保留年月日后面的都变为0, 2: 时分秒变为23:59:59
     * @return 字符串表示的日期
     */
    public static String format(Long seconds, String pattern, int style) {
        try {
            if (!StringUtils.hasText(pattern)) {
                pattern = DEFAULT_DATETIME;
            }
            Date d1 = new Date();
            if (seconds != null) {
                d1 = toDate(seconds);
            }
            switch (style) {
                case 1:
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(d1);
                    calendar1.set(Calendar.HOUR_OF_DAY, 0);
                    calendar1.set(Calendar.MINUTE, 0);
                    calendar1.set(Calendar.SECOND, 0);
                    calendar1.set(Calendar.MILLISECOND, 0);
                    d1 = calendar1.getTime();
                    break;
                case 2:
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(d1);
                    calendar2.set(Calendar.HOUR_OF_DAY, 23);
                    calendar2.set(Calendar.MINUTE, 59);
                    calendar2.set(Calendar.SECOND, 59);
                    calendar2.set(Calendar.MILLISECOND, 0);
                    d1 = calendar2.getTime();
                    break;
            }
            return new SimpleDateFormat(pattern).format(d1);
        } catch (Exception e) {
            throw BnException.on("非格式化日期字符串");
        }
    }

    /**
     * 把秒表示的时间转换为指定格式字符串
     * @param seconds 秒表示的时间，不指定为当前时间
     * @param pattern 转换后字符串的格式。不指定默认为yyyy-MM-dd HH:mm:ss格式
     * @return 字符串表示的日期原始日期
     */
    public static String format(Long seconds, String pattern) {
        return format(seconds, pattern, 0);
    }

    /**
     * 把秒表示的时间转换为指定格式字符串(yyyy-MM-dd HH:mm:ss)
     * @param seconds 秒表示的时间，默认为当前时间
     * @return 返回格式化的字符串日期
     */
    public static String format(Long seconds) {
        return format(seconds, DEFAULT_DATETIME, 0);
    }

    /**
     * 转为秒精确度
     * @param time 毫秒时间戳
     * @return 秒时间戳
     * @author ganlt
     * @date 2019/8/7
     */
    public static Long secondPrecise(Long time) {
        return time / 1000;
    }

    /**
     * 转毫秒精确度
     * @param time 毫秒时间戳
     * @return 秒精确度时间搓
     * @author ganlt
     * @date 2019/8/7
     */
    public static Long millisecondPrecise(Long time) {
        return time * 1000;
    }

    /**
     * 预处理时间精确度
     * @param time 时间戳
     * @return 返回java 毫秒时间戳
     * @author ganlt
     * @date 2019/8/7
     */
    public static Long preparePrecise(Long time) {
        if (time == null) {
            return null;
        }
        if (time.toString().length() == 10) {
            return millisecondPrecise(time);
        }

        return time;
    }

    /**
     * 将时间戳转为对应的日期格式
     * @param timeStamp
     * @return
     */
    public static String timeStamp2Date(Long timeStamp) {
        if (timeStamp == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE);
        return sdf.format(new Date(Long.valueOf(timeStamp * 1000)));
    }

}

