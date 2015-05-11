package sandkev.server.util;

/**
 * User: sandkev
 * Date: 2011-09-16
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class FormatUtil
{
    public static final String PATTERN_YYYYMMDD_NO_DELIM = "yyyyMMdd";
    public static final String PATTERN_YYYYMMDD_DASH_DELIM = "yyyy-MM-dd";
    public static final String PATTERN_HHMMSS_COLON_DELIM = "HH:mm:ss";
    public static final String PATTERN_MILLIS_SUFFIX = ".SSS";
    public static final String PATTERN_SQL_DATETIME = PATTERN_YYYYMMDD_DASH_DELIM + " " + PATTERN_HHMMSS_COLON_DELIM + PATTERN_MILLIS_SUFFIX;

    private static class DateFormatSupplier {
        private static final ConcurrentHashMap<String, ThreadLocal<DateFormat>> localFormatsByPattern = new ConcurrentHashMap<String, ThreadLocal<DateFormat>>();
        public static DateFormat getFormat(final String pattern) {
            return getFormat(pattern, TimeZone.getDefault());
        }
        public static DateFormat getFormat(final String pattern, final TimeZone timeZone) {
            String key = String.format("%s [%s]", pattern, timeZone.getID());
            ThreadLocal<DateFormat> localFormat;
            localFormat = localFormatsByPattern.get(key);
            if (localFormat == null) {
                localFormat = new ThreadLocal<DateFormat>() {
                    @Override
                    protected DateFormat initialValue() {
                        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        simpleDateFormat.setTimeZone(timeZone);
                        simpleDateFormat.setLenient(false);
                        return simpleDateFormat;
                    }
                };
                localFormatsByPattern.putIfAbsent(key, localFormat);
            }
            return localFormat.get();
        }
    }


    public FormatUtil()
    {
    }

    public static String formatSqlDateTime(Date date)
    {
        return formatSqlDateTime(date, TimeZone.getDefault());
    }
    public static String formatSqlDateTime(Date date, TimeZone timeZone)
    {
       return DateFormatSupplier.getFormat(PATTERN_SQL_DATETIME, timeZone).format(date);
    }

    public static Date yyyymmddToDate(String date) throws ParseException {
        return yyyymmddToDate(date, TimeZone.getDefault());
    }
    public static Date yyyymmddToDate(String date, TimeZone timeZone) throws ParseException {
        return DateFormatSupplier.getFormat(PATTERN_YYYYMMDD_NO_DELIM, timeZone).parse(date);
    }

    public static String yyyymmdd(Date date) {
        return yyyymmdd(date, TimeZone.getDefault());
    }
    public static String yyyymmdd(Date date, TimeZone timeZone) {
        return DateFormatSupplier.getFormat(PATTERN_YYYYMMDD_NO_DELIM, timeZone).format(date);
    }

    public static String formatCustomPattern(Date date, String pattern)
    {
        return formatCustomPattern(date, pattern, TimeZone.getDefault());
    }
    public static String formatCustomPattern(Date date, String pattern, TimeZone timeZone)
    {
        return DateFormatSupplier.getFormat(pattern, timeZone).format(date);
    }

    public static Date parseCustomPattern(String date, String pattern) throws ParseException {
        return parseCustomPattern(date, pattern, TimeZone.getDefault());
    }
    public static Date parseCustomPattern(String date, String pattern, TimeZone timeZone) throws ParseException {
        return DateFormatSupplier.getFormat(pattern, timeZone).parse(date);
    }

    public static int date2int(Date date){
        return Integer.parseInt(FormatUtil.yyyymmdd(date));
    }

}
