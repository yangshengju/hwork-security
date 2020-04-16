import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TestEntry {
    public static void main(String[] args) {
            long milliSecond = 1587075457l;
            Date date = new Date();
            date.setTime(milliSecond);
            log.info(new SimpleDateFormat().format(date));

    }
}
