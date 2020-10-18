package life.majiang.community.community.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateTimeUtil {
    public static String getTimeDuration(LocalDateTime start, LocalDateTime end) {

        Duration duration = Duration.between(start, end);
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数
        String result = days + "天：" + hours + " 小时：" + minutes + " 分钟：" + millis + " 毫秒：" + nanos + " 纳秒";


        return result;

    }
}
