package sandkev.server.util;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by kevin on 11/05/2015.
 */
public class FormatUtilTest {

    private final static long time=0L;
    private final static Date DATE_1_JAN_1970 = new Date(time);

    private static final long fixedPointInTime = 1365615231565L;

    @Before
    public void setUp(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void formatSqlDateTime(){
        assertEquals("1970-01-01 00:00:00.000", FormatUtil.formatSqlDateTime(DATE_1_JAN_1970));
        assertEquals("1970-01-01 01:00:00.000", FormatUtil.formatSqlDateTime(DATE_1_JAN_1970, TimeZone.getTimeZone("Europe/London")));
        assertEquals("1970-01-01 09:00:00.000", FormatUtil.formatSqlDateTime(DATE_1_JAN_1970, TimeZone.getTimeZone("Asia/Tokyo")));
        assertEquals("1969-12-31 19:00:00.000", FormatUtil.formatSqlDateTime(DATE_1_JAN_1970, TimeZone.getTimeZone("America/New_York")));
        assertEquals("1970-01-01 08:00:00.000", FormatUtil.formatSqlDateTime(DATE_1_JAN_1970, TimeZone.getTimeZone("Asia/Hong_Kong")));
    }

    @Test
    public void yyyymmddToDate() throws ParseException {
        assertEquals(DATE_1_JAN_1970, FormatUtil.yyyymmddToDate("19700101"));
        assertEquals(DATE_1_JAN_1970, FormatUtil.yyyymmddToDate("19700101", TimeZone.getTimeZone("GMT")));
        assertEquals(+5*60*60*1000, FormatUtil.yyyymmddToDate("19700101", TimeZone.getTimeZone("America/New_York")).getTime());
        assertEquals(-9*60*60*1000, FormatUtil.yyyymmddToDate("19700101", TimeZone.getTimeZone("Asia/Tokyo")).getTime());
    }

    @Test
    public void yyyymmdd() throws ParseException {
        assertEquals("19700101", FormatUtil.yyyymmdd(DATE_1_JAN_1970));
        assertEquals("19691231", FormatUtil.yyyymmdd(DATE_1_JAN_1970, TimeZone.getTimeZone("America/New_York")));
    }

    @Test
    public void formatCustomPattern(){
        assertEquals("19700101", FormatUtil.formatCustomPattern(DATE_1_JAN_1970, "yyyyMMdd"));
        assertEquals("19700101_0100", FormatUtil.formatCustomPattern(DATE_1_JAN_1970, "yyyyMMdd_HHmm", TimeZone.getTimeZone("Europe/London")));
        assertEquals("1970-01-01 08:00:00.000 HKT", FormatUtil.formatCustomPattern(DATE_1_JAN_1970, "yyyy-MM-dd HH:mm:ss.SSS z", TimeZone.getTimeZone("Asia/Hong_Kong")));
    }

}