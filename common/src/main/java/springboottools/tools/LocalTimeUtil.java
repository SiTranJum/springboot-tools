package springboottools.tools;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * LocalTime时间工具
 */
public class LocalTimeUtil {

	public static final String HH = "HH";
	public static final String HH_MM = "HH:mm";
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String HH_MM_SS_S = "HH:mm:ss.S";

	private static final String BASE_TIME_FORMAT = "[HH][[:][.]mm][[:][.]ss][[:][.]SSS]";

	/**
	 * @param timeString
	 * @return
	 */
	public static LocalTime parse(String timeString) {
		return LocalTime.parse(timeString, getDateTimeFormatterByPattern(BASE_TIME_FORMAT));
	}

	/**
	 * 根据传进来的pattern返回LocalTime
	 *
	 * @param timeString
	 * @param pattern
	 * @return
	 */
	public static LocalTime parseByPattern(String timeString, String pattern) {
		return LocalTime.parse(timeString, getDateTimeFormatterByPattern(pattern));
	}

	private static DateTimeFormatter getDateTimeFormatterByPattern(String pattern) {
		return new DateTimeFormatterBuilder()
				.appendPattern(pattern)
				.parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDateTime.now().getYear())
				.parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDateTime.now().getMonthValue())
				.parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
				.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
				.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
				.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
				.parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
				.toFormatter();
	}

	/**
	 * 将date转为LocalTime
	 * @param date
	 * @return
	 */
	public static LocalTime parse(Date date) {
		LocalTime localTime = null;
		if(null != date) {
			Instant instant = date.toInstant();
			ZoneId zoneId = ZoneId.systemDefault();
			localTime = instant.atZone(zoneId).toLocalTime();
		}

		return localTime;
	}

	/**
	 * 将LocalTime转为字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(LocalTime date, String pattern) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		return date.format(dtf);
	}

}