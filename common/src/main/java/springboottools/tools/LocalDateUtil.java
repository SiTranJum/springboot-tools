package springboottools.tools;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * LocalDate时间工具
 */
public class LocalDateUtil {

	public static final String YYYY = "yyyy";
	public static final String YYYYMM = "yyyyMM";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	private static final String BASE_TIME_FORMAT = "[yyyyMMdd][yyyyMM][yyyy][[-][/][.]MM][[-][/][.]dd]";
	
	/**
	 * 【推荐】解析常用时间字符串，支持,并不局限于以下形式：
	 * [yyyy][yyyy-MM][yyyy-MM-dd]
	 * [yyyy/MM][yyyy/MM/dd]
	 * [yyyy.MM][yyyy.MM.dd]
	 * [yyyyMM][yyyyMMdd][yyyyMMdd]
	 * [MM-dd]
	 * @param timeString
	 * @return
	 */
	public static LocalDate parse(String timeString) {
		return LocalDate.parse(timeString, getDateTimeFormatterByPattern(BASE_TIME_FORMAT));
	}

	/**
	 * 根据传进来的pattern返回LocalDate
	 *
	 * @param timeString
	 * @param pattern
	 * @return
	 */
	public static LocalDate parseByPattern(String timeString, String pattern) {
		return LocalDate.parse(timeString, getDateTimeFormatterByPattern(pattern));
	}

	private static DateTimeFormatter getDateTimeFormatterByPattern(String pattern) {
		return new DateTimeFormatterBuilder()
				.appendPattern(pattern)
				.parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear())
				.parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDate.now().getMonthValue())
				.parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
				.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
				.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
				.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
				.parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
				.toFormatter();
	}

	/**
	 * 将timestamp转为LocalDate
	 * @param timestamp
	 * @return
	 */
	public static LocalDate timestampToLocalDate(long timestamp){
		Instant instant = Instant.ofEpochMilli(timestamp);
		return instant.atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * 将date转为LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate parse(Date date) {
		LocalDate localDate = null;
		if(null != date) {
			Instant instant = date.toInstant();
			ZoneId zoneId = ZoneId.systemDefault();
			localDate = instant.atZone(zoneId).toLocalDate();
		}

		return localDate;
	}

	/**
	 * 将LocalDate转为字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(LocalDate date, String pattern) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		return date.format(dtf);
	}

	/**
	 * 将LocalDate转为Date
	 * @param localDate
	 * @return
	 */
	public static Date parse2Date(LocalDate localDate) {
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
}