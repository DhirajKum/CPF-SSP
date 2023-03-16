package com.techm.fci.cpf.util;

/**
 * @author DHIRAJ
 * @version 1.0
 * @since 01/03/2021
 */
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

	/**
	 * yyyy-MM-dd HH:mm:ss format
	 */
	public static final String YyyyMmDdHmsFormat = "yyyy-MM-dd HH:mm:ss";
	public static final String DdmmyyyyHHmmssFormat = "dd/MM/yyyy HH:mm:ss";
	public static final String DdmmyyyyFormat = "dd/MM/yyyy";
	public static final String MmddyyyyFormat = "MM/dd/yyyy";
	public static final String YyyyMmDd = "yyyy-MM-dd";
	public static final String YYYYMmDd = "yyyy/MM/dd";
	public static final String ddMMYYYY = "dd.MM.yyyy";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMMddSlashed = "yyyy/MM/dd";
	public static final String dd_mm_yyyy = "dd-MM-yyyy";
	public static final String DdMonthYearFormat = "dd MMMM,yyyy";
	public static final String DdMonthYearHHmmFormat = "dd MMMM yyyy hh:mm a";
	public static final String Day_DdMonthYearFormat = "EEE, dd MMM yyyy";
	public static final String Day_DdMonthFormat = "EEE, dd MMM";
	public static final String HOUR_MINUTE_SEC = "HH:mm:ss";
	public static final String[] MONTHS_ARRAY = { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	public static final String YYYYMMDDPlain = "YYYYMMdd";
	public static final String YYYYMMDD24HHmmssPlain = "yyyyMMddHHmmss";
	public static final String dd_MMM_yy = "dd-MMM-yy";
	public static final String MMM_dd_yyyy = "MMM-dd-yyyy";

	public static String convertDateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static Date convertStringToDate(String date, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(date);
	}

	public static Timestamp convertStringToTimestamp(String timestamp, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return new Timestamp(formatter.parse(timestamp).getTime());
	}

	public static String getCurMySQLDate() {
		Date date = new Date(System.currentTimeMillis());
		return getMySQLDate(date);
	}

	public static String getMySQLDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.YyyyMmDd);
		return formatter.format(date);
	}

	public static String getCurMySQLDateWithTime() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.YyyyMmDdHmsFormat);
		return formatter.format(date);
	}

	public static String getDateInyyyyMMddFormat(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		return new StringBuilder().append(year).append(month).append(day).toString();
	}

	/**
	 * Return string representation of date in dd/MM/yyyy format
	 * 
	 * @param date
	 */
	public static String getDateInddMMyyyyFormat(Date date) {
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/YYYY");
		return outputDateFormat.format(date);
//
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		int year = calendar.get(Calendar.YEAR);
//		int month = calendar.get(Calendar.MONTH) + 1;
//		int day = calendar.get(Calendar.DATE);
//		return new StringBuilder().append(day).append("/").append(month).append("/").append(year).toString();
	}

	/**
	 * Get time in AM/PM format
	 */
	public static String getAmPmTime(int hourOfDay) {
		if (hourOfDay <= 12) {
			return hourOfDay + " AM";
		}
		hourOfDay = hourOfDay - 12;
		return hourOfDay + " PM";
	}

	/**
	 * Adds specified number of days in the date
	 * 
	 * @param date
	 * @param days
	 * @return modified date
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * Adds specified number of years in the date
	 * 
	 * @param date
	 * @param numberOfYears
	 * @return modified date
	 */
	public static Date addYears(Date date, int numberOfYears) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, numberOfYears);
		return calendar.getTime();
	}

	/**
	 * Adds specified number of months in the date
	 * 
	 * @param date
	 * @param numberOfMonths
	 * @return modified date
	 */
	public static Date addMonths(Date date, int numberOfMonths) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, numberOfMonths);
		return calendar.getTime();
	}

	/**
	 * Add minutes to time
	 */
	public static Date addMinutes(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	/**
	 * Get Day Start
	 * 
	 * @param date
	 * @return modified date
	 */
	public static Date dayStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Get Day Start
	 * 
	 * @param date
	 * @return modified date
	 */
	public static Date currentDayStart() {
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date currentDayEnd() {
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Get Day End
	 * 
	 * @param date
	 * @return modified date
	 */
	public static Date dayEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date threeYearsLater(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 3);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date threeMonthsEarlier() {
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -3);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Get Month End
	 * 
	 * @param date
	 * @return modified date
	 */
	public static Date dayMonthEnd(Integer month, Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Get Month Start
	 * 
	 * @param date
	 * @return modified date
	 */
	public static Date dayMonthStart(Integer month, Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Get Month Start
	 */
	public static Date dayMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Range of date from to both are inclusive.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static List<Date> getDateRange(Date from, Date to) {
		from = DateUtils.dayStart(from);
		to = DateUtils.dayStart(to);
		List<Date> dates = new ArrayList<Date>();
		if (from.after(to))
			return null;
		if (from.equals(to)) {
			dates.add(from);
			return dates;
		} else {
			Date _temp = from;
			dates.add(from);
			while (!_temp.equals(to)) {
				_temp = addDays(_temp, 1);
				dates.add(_temp);
			}
		}
		return dates;
	}

	/**
	 * Utility method to calculate age
	 */
	public static int getAge(Date dateOfBirth) {
		Calendar dob = Calendar.getInstance();
		dob.setTime(dateOfBirth);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
			age--;
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		return age;
	}

	public static String getCurYear() {
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return new StringBuilder().append(year).toString();
	}

	/**
	 * Get Year of the provided date
	 */
	public static Integer getYear(Date inputDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		return calendar.get(Calendar.YEAR);
	}

	public static Integer getMonth(Date inputDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		return calendar.get(Calendar.MONTH);
	}

	public static String getCurMonth() {
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new StringBuilder().append(month).toString();
	}

	public static String getCurDate() {
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		return new StringBuilder().append(day).toString();
	}

	public static Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date set12PMTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Long daysBetweenDates(Date fromDate, Date toDate) {
		Long noOfDays = ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
		return noOfDays;
	}

	public static Long dateDiffByDays(LocalDate toDate) {
		LocalDate fromDate = LocalDate.now();
		Long range = ChronoUnit.DAYS.between(fromDate, toDate);
		return range;
	}

	public static void main(String args[]) throws ParseException {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.set(2016, 6, 12);
		calendar2.set(2016, 6, 22);
		Date startDate = DateUtils.convertStringToDate("2015-03-01", DateUtils.YyyyMmDd);
		Date endDate = DateUtils.convertStringToDate("2023-03-15", DateUtils.YyyyMmDd);

		System.out.println("Days calculate between two dates :::: "+ dateDiffByDays(LocalDate.parse("2024-03-01")));

		System.out.println("Days between from date and to date======>>>> " + daysBetweenDates(startDate, endDate));

	}

}