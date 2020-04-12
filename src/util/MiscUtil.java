package util;

public class MiscUtil {
    public static boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatDurationToString(int d) {
        int hour = d / 60;
        int min = d % 60;
        return hour + ":" + min;
    }

    public static int formatDurationToInt(String d) {
        String[] strArr = d.split(":");
        int hours = Integer.valueOf(strArr[0]);
        int min = Integer.valueOf(strArr[1]);

        return hours * 60 + min;
    }
}
