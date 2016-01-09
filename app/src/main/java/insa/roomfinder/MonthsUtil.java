package insa.roomfinder;


import android.content.Context;

/**
 * Created by pierre on 03/01/16.
 */
public class MonthsUtil {

    public static String monthToString (int month, Context context) {
        String[] months = context.getResources().getStringArray(R.array.Months_Array);
        return months[month-1];
    }
}
