package jupiterpi.tools.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TimeUtils {
    public static long now() {
        return new Date().getTime();
    }

    /* format dates */

    // display

    // full
    public static final SimpleDateFormat DF_DISPLAY_FULL = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    public static final SimpleDateFormat DF_DISPLAY_FULL_SECONDS = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    // date
    public static final SimpleDateFormat DF_DISPLAY_DATE = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat DF_DISPLAY_DATE_SHORT = new SimpleDateFormat("dd.MM.yy");
    public static final SimpleDateFormat DF_DISPLAY_DATE_SHORTER = new SimpleDateFormat("dd.MM.");

    // time
    public static final SimpleDateFormat DF_DISPLAY_TIME = new SimpleDateFormat("HH:mm:ss.SSS");
    public static final SimpleDateFormat DF_DISPLAY_TIME_SHORT = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DF_DISPLAY_TIME_SHORTER = new SimpleDateFormat("HH:mm");


    // oneline

    public static final SimpleDateFormat DF_ONELINE_FULL = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");

    /* format durations */

    private static final List<String> durationComponents = Arrays.asList("d:86400", "h:360", "m:60", "s:1");

    public static String formatDuration(int s) {
        List<String> components = new ArrayList<>();
        for (String component : durationComponents) {
            String[] parts = component.split(":");
            String c = parts[0];
            int seconds = Integer.parseInt(parts[1]);

            int amount = (int) (s / ((float)seconds));
            if (amount > 0) {
                components.add(amount + c);
            }

            s -= amount * seconds;
        }
        return ToolsUtil.appendWithSeparator(components, " ");
    }
}