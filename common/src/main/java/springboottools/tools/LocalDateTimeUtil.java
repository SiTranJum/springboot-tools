package springboottools.tools;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * LocalDateTime时间工具
 */
public class LocalDateTimeUtil {

	public static final String YYYY = "yyyy";
	public static final String YYYYMM = "yyyyMM";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss.S'Z'";

	private static final String BASE_TIME_FORMAT = "[yyyyMMddHHmmss][yyyyMMddHHmm][yyyyMMddHH][yyyyMMdd][yyyyMM][yyyy][[-][/][.]MM][[-][/][.]dd][ ][HH][[:][.]mm][[:][.]ss][[:][.]SSS]";
	/**
	 * 【推荐】解析常用时间字符串，支持,并不局限于以下形式：
	 * [yyyy][yyyy-MM][yyyy-MM-dd][yyyy-MM-dd HH][yyyy-MM-dd HH:mm][yyyy-MM-dd HH:mm:ss][yyyy-MM-dd HH:mm:ss:SSS]
	 * [yyyy][yyyy/MM][yyyy/MM/dd][yyyy/MM/dd HH][yyyy/MM/dd HH:mm][yyyy/MM/dd HH:mm:ss][yyyy/MM/dd HH:mm:ss:SSS]
	 * [yyyy][yyyy.MM][yyyy.MM.dd][yyyy.MM.dd HH][yyyy.MM.dd HH.mm][yyyy.MM.dd HH.mm.ss][yyyy.MM.dd HH.mm.ss.SSS]
	 * [yyyy][yyyyMM][yyyyMMdd][yyyyMMddHH][yyyyMMddHHmm][yyyyMMddHHmmss]
	 * [MM-dd]
	 * 不支持yyyyMMddHHmmssSSS，因为本身DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")就不支持这个形式
	 *
	 * @param timeString
	 * @return
	 */
	public static LocalDateTime parse(String timeString) {
		return LocalDateTime.parse(timeString, getDateTimeFormatterByPattern(BASE_TIME_FORMAT));
	}

	/**
	 * 根据传进来的pattern返回LocalDateTime，自动补齐
	 *
	 * @param timeString
	 * @param pattern
	 * @return
	 */
	public static LocalDateTime parseByPattern(String timeString, String pattern) {
		return LocalDateTime.parse(timeString, getDateTimeFormatterByPattern(pattern));
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
	 * 将timestamp转为LocalDateTime
	 * @param timestamp
	 * @return
	 */
	public static LocalDateTime timestamToDatetime(long timestamp){
		Instant instant = Instant.ofEpochMilli(timestamp);
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

	/**
	 * 将LocalDataTime转为timestamp
	 * @param ldt
	 * @return
	 */
	public static long datatimeToTimestamp(LocalDateTime ldt){
		ZoneId zone = ZoneId.systemDefault();
		return ldt.atZone(zone).toInstant().toEpochMilli();
	}

	/**
	 * 将date转为LocalDateTime
	 * @param date
	 * @return
	 */
	public static LocalDateTime parse(Date date) {
		LocalDateTime localDateTime = null;
		if(null != date) {
			Instant instant = date.toInstant();
			ZoneId zoneId = ZoneId.systemDefault();
			localDateTime = instant.atZone(zoneId).toLocalDateTime();
		}

		return localDateTime;
	}

	/**
	 * 将LocalDateTime转为UTC时间字符串
	 * @param date
	 * @return
	 */
	public static String formatUTC(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD_T_HH_MM_SS_Z);
		return date.format(dtf);
	}

	/**
	 * 将LocalDateTime转为字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(LocalDateTime date, String pattern) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		return date.format(dtf);
	}

	/**
	 * 将LocalDateTime转为Date
	 * @param localDateTime
	 * @return
	 */
	public static Date parse2Date(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());
		return date;
	}
}