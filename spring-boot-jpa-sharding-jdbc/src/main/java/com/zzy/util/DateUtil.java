package com.zzy.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * <p>java8时间处理</p>
 * Created by @author zhezhiyong@163.com on 2018/12/27.
 */
public class DateUtil {

    public static void main(String[] args) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 获取当前时间
        LocalTime currentTime = LocalTime.now();
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 格式化时间
        LocalDate localDate = LocalDate.parse("1987/12/12", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("格式化日期 = " + localDate);
        // 获取本月最后一天
        LocalDate lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDayOfMonth = " + lastDayOfMonth);
        // 获取2018年最后一个星期五
        LocalDate lastMonthLastFriday = LocalDate.parse("2018-12-01").with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
        System.out.println("lastMonthLastFriday = " + lastMonthLastFriday);
        // 获取昨天的日期
        LocalDate tomorrow = LocalDate.now().plusDays(-1);
        System.out.println("tomorrow = " + tomorrow);
        // 获取明年的今天
        LocalDate nextYear = LocalDate.now().plusYears(1);
        System.out.println("nextYear = " + nextYear);

    }

}
