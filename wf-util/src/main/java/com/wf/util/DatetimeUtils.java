package com.wf.util;

import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author wangfeng
 * @version 1.0
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 8:51
 */
public class DatetimeUtils {

    public static Date dateFromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date dateFromLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public static Date dateFromFileTime(FileTime fileTime) {
        return Date.from(fileTime.toInstant());
    }

    public static FileTime dateToFileTime(Date date) {
        return FileTime.from(date.toInstant());
    }

    public static LocalDate localDateFromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime localDateTimeFromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static FileTime fileTimeFromDate(Date date) {
        return FileTime.from(date.toInstant());
    }

    public static FileTime fileTimeFromLocalDate(LocalDate localDate) {
        return FileTime.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static FileTime fileTimeFromLocalDatetime(LocalDateTime localDateTime) {
        return FileTime.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
