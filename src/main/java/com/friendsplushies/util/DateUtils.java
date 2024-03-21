package com.friendsplushies.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

  public static final String DDMMYYYY = "ddMMyyyy";
  public static final String YYYYMMDD = "yyyyMMdd";
  public static final String DD_MM_YYYY = "dd-MM-yyyy";
  public static final String HH_MM = "HH:mm";
  public static final String MM_YYYY = "MM/yyyy";
  public static final String DD_MM_YY_HH_MM_SS = "dd/MM/yy HH:mm:ss";
  public static final String YYYY_MM_DD = "yyyy-MM-dd";
  public static final String YYYY_MM = "yyyy-MM";
  public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssZ";
  static Map<String, String> timeZone = new HashMap<>();
  public static int WEEKEND_START_DAY = Calendar.FRIDAY;
  public static int WEEKEND_START_HOUR = 17;
  public static int WEEKEND_END_DAY = Calendar.MONDAY;
  public static int WEEKEND_END_HOUR = 8;


  public static Timestamp nowTimestamp() {
    return new Timestamp(Calendar.getInstance().getTimeInMillis());
  }

  public static Date nowDate() {
    return new Date();
  }

  /**
   * @param inputDate
   * @param timezone
   * @param datePattern
   * @return
   */
  public static String formatDate(Date inputDate, TimeZone timezone, String datePattern) {
    if (inputDate != null) {
      SimpleDateFormat df = new SimpleDateFormat(datePattern);
      if (timezone != null) {
        df.setTimeZone(timezone);
      } // else by server timezone
      return df.format(inputDate.getTime());
    } else {
      return null;
    }
  }


  public static String formatDateTime(Date inputDate, String pattern) {
    if (inputDate != null) {
      SimpleDateFormat df = new SimpleDateFormat(pattern);
      return df.format(inputDate.getTime());
    } else {
      return null;
    }
  }

  public static String formatDateTime(Long timestamp, String pattern) {
    if (timestamp != null) {
      SimpleDateFormat df = new SimpleDateFormat(pattern);
      return df.format(timestamp);
    } else {
      return null;
    }
  }

  public static Date parseDate(String dateValue, String format, TimeZone timezone) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
    if (timezone != null) {
      df.setTimeZone(timezone);
    } // else by server timezone
    return df.parse(dateValue);
  }

  public static Date parseDate(String dateValue, String datePattern) throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat(datePattern);
    return df.parse(dateValue);
  }

  public static Date parseDate(String dateValue) throws ParseException {
    return parseDate(dateValue, YYYY_MM, MM_YYYY, YYYY_MM_DD, DD_MM_YYYY, YYYY_MM_DD_HH_MM_SS, DD_MM_YY_HH_MM_SS, DATE_ISO_8601, DDMMYYYY, YYYYMMDD);
  }

  public static Date getDayBeginTime(Long timeInMillis) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(timeInMillis);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  public static Date getDayEndTime(Long timeInMillis) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(timeInMillis);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    return cal.getTime();
  }

  public static Date shiftDays(Long timeInMillis, int d) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(timeInMillis);
    cal.add(Calendar.DAY_OF_YEAR, d);
    return cal.getTime();
  }

  public static Date createDateInMilliseconds(int m) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MILLISECOND, m);
    return cal.getTime();
  }

  public static boolean isExpired(Date date) {
    Calendar cal = Calendar.getInstance();
    return cal.after(date);
  }

  public static Date getWeekStartCalendar(Date weekday) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(weekday);
    cal.setFirstDayOfWeek(Calendar.SUNDAY);
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  public static Date getWeekEndCalendar(Date weekday) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(weekday);
    cal.setFirstDayOfWeek(Calendar.SUNDAY);
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  public static Date getWeekStart(Date weekday) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(weekday);
    cal.setFirstDayOfWeek(Calendar.MONDAY);
    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    return cal.getTime();
  }

  public static Date getWeekEnd(Date weekday) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(weekday);
    cal.setFirstDayOfWeek(Calendar.MONDAY);
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  public static Date getLastTimeDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    return cal.getTime();
  }

  public static Date getCurrentDateWithoutTime() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  public static String getDuration(String start, String end) throws ParseException {
    Date startDate = DateUtils.parseDate(start);
    Date endDate = DateUtils.parseDate(end);
    long diff = endDate.getTime() - startDate.getTime();
    Date d = new Date(diff);
    Calendar c = Calendar.getInstance();
    c.setTime(d);
    return c.get(Calendar.YEAR) - 70 + " year(s) " + c.get(Calendar.MONTH) + " month(s)";
  }

  public static Integer getDurationInMinutes(Timestamp startTime, Timestamp endTime) {
    long milliseconds1 = startTime.getTime();
    long milliseconds2 = endTime.getTime();

    long diff = milliseconds2 - milliseconds1;
    long diffSeconds = diff / 1000;
    long diffMinutes = diff / (60 * 1000);
    long diffHours = diff / (60 * 60 * 1000);
    long diffDays = diff / (24 * 60 * 60 * 1000);

    return Math.toIntExact(diffMinutes);
  }

  public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
    List<Date> dates = new ArrayList<>();
    dates.add(startdate);
    while (startdate.before(enddate)) {
      startdate = DateUtils.addDays(startdate, 1);
      dates.add(startdate);
    }
    return dates;
  }

  public static Date getEndDateOfMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    return cal.getTime();
  }

  /**
   * From 17:00 Fri to 8:00 Mon
   */
  public static boolean isWeekend() {
    return isWeekend(WEEKEND_START_DAY, WEEKEND_START_HOUR, WEEKEND_END_DAY, WEEKEND_END_HOUR);
  }

  /**
   * Is today on weekend
   *
   * @param startDay Calendar.DAY_OF_WEEK
   * @param startHour Calendar.HOUR_OF_DAY
   * @param endDay Calendar.DAY_OF_WEEK
   * @param endHour Calendar.HOUR_OF_DAY
   */
  private static boolean isWeekend(int startDay, int startHour, int endDay, int endHour) {
    Calendar today = new GregorianCalendar();
    int day = today.get(Calendar.DAY_OF_WEEK);
    int hour = today.get(Calendar.HOUR_OF_DAY);
    return isWeekend(day, hour, startDay, startHour, endDay, endHour);
  }

  private static boolean isWeekend(int day, int hour, int startDay, int startHour, int endDay, int endHour) {
    endDay = (endDay < startDay ? endDay + Calendar.SATURDAY : endDay);
    day = (endDay > Calendar.SATURDAY && day < startDay ? day + Calendar.SATURDAY : day);
    return (startDay != endDay && day == startDay && hour >= startHour) ||
        (startDay != endDay && day == endDay && hour < endHour) ||
        (startDay == endDay && hour >= startHour && hour < endHour) ||
        (day > startDay && day < endDay);
  }

  /**
   * Create TimeZone object from timezone value of UTC
   */
  public static TimeZone getTimeZoneFromUTC(String timeZoneStr) {
    return TimeZone.getTimeZone(timeZone.getOrDefault(timeZoneStr, "GMT+7:00"));
  }

  public static String getGMTTimezone(TimeZone tz) {

    long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
    long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
        - TimeUnit.HOURS.toMinutes(hours);
    // avoid -4:-30 issue
    minutes = Math.abs(minutes);

    String result;
    if (hours > 0) {
      result = String.format("GMT+%d:%02d", hours, minutes);
    } else {
      result = String.format("GMT%d:%02d", hours, minutes);
    }

    return result;

  }

  public static String getDateNameByLocal(Timestamp date, Locale locale, String format) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    LocalDate localDate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

    String dateStr = localDate.format(DateTimeFormatter.ofPattern(format, locale));

    return dateStr;
  }

  public static String getDateNameByLocalAndTimezone(Timestamp date, Locale locale, String timeZone, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
    sdf.setTimeZone(getTimeZoneFromUTC(timeZone));

    String dateStr = sdf.format(date);

    return dateStr;
  }


}
