package com.hong.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

/**
 * 日期操作工具类，基于Java8新的日期/时间api
 */
public class DateUtils {

    public static final ZoneId ZONE_ID = ZoneId.systemDefault();

    /**
     * LocalDateTime转化为Date
     *
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
    }

    /**
     * LocalDate转化为Date
     *
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZONE_ID).toInstant());
    }

    /**
     * Date转化为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZONE_ID);
    }

    /**
     * 日期时间对象转换为日期对象
     *
     * @param localDateTime 日期时间对象
     * @return 日期对象
     */
    public static LocalDate dateTime2Date(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();

    }

    /**
     * 日期对象转换为日期对象
     *
     * @param localDate 日期对象
     * @return 日期时间对象
     */
    public static LocalDateTime date2DateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    /**
     * Date转化为LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalDate();
    }

    /**
     * Date转化为字符串
     *
     * @param date
     * @param formatter
     * @return
     */
    public static String format(Date date, DateFormatter formatter) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZONE_ID);
        return formatter.getDateTimeFormatter().format(localDateTime);
    }

    /**
     * 字符串转化为Date
     *
     * @param text
     * @param formatter
     * @return
     */
    public static Date parse(String text, DateFormatter formatter) {
        return formatter.parse(text);
    }

    public static enum DateFormatter {

        /**
         * 格式yyyy
         *
         * @author Val Song Dec 17, 2017 7:21:12 PM
         *
         */
        YEAR_FORMATTER(DateTimeFormatter.ofPattern("yyyy", Locale.CHINA)) {
            @Override
            public Date parse(String text) {
                Year year = Year.parse(text, dateTimeFormatter);
                return Date.from(year.atDay(1).atStartOfDay(ZONE_ID).toInstant());
            }
        },

        /**
         *
         * 格式yyyy-MM
         *
         * @author Val Song Dec 17, 2017 7:21:30 PM
         *
         */
        YEAR_MONTH_FORMATTER(DateTimeFormatter.ofPattern("yyyy-MM", Locale.CHINA)) {
            @Override
            public Date parse(String text) {
                YearMonth yearMonth = YearMonth.parse(text, dateTimeFormatter);
                return Date.from(yearMonth.atDay(1).atStartOfDay(ZONE_ID).toInstant());
            }
        },

        /**
         *
         * 格式yyyy-MM-dd
         *
         * @author Val Song Dec 17, 2017 7:26:25 PM
         *
         */
        DATE_FORMATTER(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA)) {
            @Override
            public Date parse(String text) {
                LocalDate localDate = LocalDate.parse(text, dateTimeFormatter);
                return Date.from(localDate.atStartOfDay(ZONE_ID).toInstant());
            }
        },

        /**
         * 格式yyyy-MM-dd HH:mm:ss
         *
         * @author Val Song Dec 17, 2017 7:26:39 PM
         *
         */
        DATE_TIME_FORMATTER(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA)) {
            @Override
            public Date parse(String text) {
                LocalDateTime localDateTime = LocalDateTime.parse(text, dateTimeFormatter);
                return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
            }
        },

        /**
         * 格式yyyyMMdd_HHmmss
         *
         * @author Val Song Dec 17, 2017 7:26:59 PM
         *
         */
        YYYYMMDD_HHMMSS_FORMATTER(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss", Locale.CHINA)) {
            @Override
            public Date parse(String text) {
                LocalDateTime localDateTime = LocalDateTime.parse(text, dateTimeFormatter);
                return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
            }
        };

        protected DateTimeFormatter dateTimeFormatter;

        private DateFormatter(DateTimeFormatter dateTimeFormatter) {
            this.dateTimeFormatter = dateTimeFormatter;
        }

        public DateTimeFormatter getDateTimeFormatter() {
            return dateTimeFormatter;
        }

        public abstract Date parse(String text);
    }

    /**
     * 字符串转换为日期
     *
     * @param strDate 字符串日期
     * @return 日期对象 yyyy-mm-dd
     */
    public static LocalDate str2Date(String strDate) {
        return LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
    }

    /**
     * 日期对象转换为字符串
     *
     * @param localDate 日期对象
     * @return 日期字符串 yyyy-mm-dd
     */
    public static String date2Str(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ISO_DATE);
    }


    /**
     * 日期时间对象转换为字符串
     *
     * @param localDateTime     日期时间对象
     * @param dateTimeFormatter 格式化字符串
     * @return 日期字符串
     */
    public static String dateTime2Str(LocalDateTime localDateTime, String dateTimeFormatter) {
        return localDateTime.format(DateTimeFormatter.ofPattern(dateTimeFormatter));
    }

    /**
     * 日期时间转字符串函数
     * 返回ISO标准的日期字符串
     *
     * @param localDateTime 日期时间对象
     * @return 日期字符串
     */
    public static String dateTime2Str(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     * @return
     */
    public static int daysBetween(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date1, date2);
        return period.getDays();
    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     * @return
     */
    public static int monthsBetween(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date1, date2);
        return period.getMonths();
    }

    /**
     * 计算两个日期之间相差的年数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     * @return
     */
    public static int yearsBetween(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date1, date2);
        return period.getYears();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {
        Instant instantDate1 = date1.toInstant();
        Instant instantDate2 = date2.toInstant();
        LocalDate localDate1 = instantDate1.atZone(ZONE_ID).toLocalDate();
        LocalDate localDate2 = instantDate2.atZone(ZONE_ID).toLocalDate();
        instantDate1.atZone(ZONE_ID);
        Period period = Period.between(localDate1, localDate2);
        return period.getDays();
    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     * @return
     */
    public static int monthsBetween(Date date1, Date date2) {
        Instant instantDate1 = date1.toInstant();
        Instant instantDate2 = date2.toInstant();
        LocalDate localDate1 = instantDate1.atZone(ZONE_ID).toLocalDate();
        LocalDate localDate2 = instantDate2.atZone(ZONE_ID).toLocalDate();
        instantDate1.atZone(ZONE_ID);
        Period period = Period.between(localDate1, localDate2);
        return period.getMonths();
    }

    /**
     * 计算两个日期之间相差的年数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     * @return
     */
    public static int yearsBetween(Date date1, Date date2) {
        Instant instantDate1 = date1.toInstant();
        Instant instantDate2 = date2.toInstant();
        LocalDate localDate1 = instantDate1.atZone(ZONE_ID).toLocalDate();
        LocalDate localDate2 = instantDate2.atZone(ZONE_ID).toLocalDate();
        instantDate1.atZone(ZONE_ID);
        Period period = Period.between(localDate1, localDate2);
        return period.getYears();
    }

    /**
     * 获取指定日期对象当前月的起始日
     *
     * @param localDate 指定日期
     * @return
     */
    public static int getFirstDayInMonth(LocalDate localDate) {
        LocalDate result = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return result.getDayOfMonth();

    }

    /**
     * 获取指定日期对象的当前月的结束日
     *
     * @param localDate 指定日期
     * @return
     */
    public static int getLastDayInMonth(LocalDate localDate) {
        LocalDate result = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return result.getDayOfMonth();
    }


    /**
     * 获取指定日期对象本月的某周某天的日期
     *
     * @param localDate  日期对象
     * @param weekNumber 周
     * @param dayNumber  日
     * @return
     */
    public static LocalDate getLocalDateBydayAndWeek(LocalDate localDate, int weekNumber, int dayNumber) {
        return localDate.with(TemporalAdjusters.dayOfWeekInMonth(weekNumber, DayOfWeek.of(dayNumber)));
    }

    public static void main(String[] args) {
        String s = dateTime2Str(LocalDateTime.now(), "yyyyMMdd");
        System.out.println(s);
    }

}
