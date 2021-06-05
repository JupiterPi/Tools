package jupiterpi.tools.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility methods.
 */
public class ToolsUtil {
    /**
     * Lines the items up after each other, separated with the separator.
     * @return The lined up items.
     */
    public static String appendWithSeparator(List<String> items, String separator) {
        String str = "";
        for (String item : items) {
            str += item + separator;
        }
        try {
            return trimSeparator(str, separator);
        } catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    /**
     * Lines the items up after each other, separated with the separator.
     * @return The lined up items.
     */
    public static String appendWithSeparator(String[] items, String separator) {
        return appendWithSeparator(Arrays.asList(items), separator);
    }

    /**
     * Removes a separator from the end if a string.
     * @return The result (the string without the substring at the end).
     */
    public static String trimSeparator(String from, String separator) {
        return from.substring(0, from.length()-separator.length());
    }

    public static <T> ArrayList<T> asArrayList(T[] arr) {
        ArrayList<T> list = new ArrayList<>();
        Collections.addAll(list, arr);
        return list;
    }
}