import com.framework.util.Md5Encoder;
import com.framework.util.StringUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MD5Test {
    @Test
    public void test() throws ParseException {
        String username = "admin";
        String password = "123456";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2016-08-31 00:00:00");
        String createTime = "2016-08-31 00:00:00";
        String MD5PASSWORD = Md5Encoder.getMd5Msg(username+password+ StringUtil.getSimpleDateFormat(date));
        System.out.println(MD5PASSWORD);
    }
}
