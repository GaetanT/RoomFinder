package insa.roomfinder;


import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by pierre on 03/01/16.
 */
public class DateUtil {

    public static String monthToString (int month, Context context) {
        String[] months = context.getResources().getStringArray(R.array.Months_Array);
        return months[month-1];
    }

    public static String dateToYMD (String date, Context context) {
        String[] parts = date.split(" ");
        ArrayList<String> months = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.Months_Array)));
        HashMap<String, String> hm = new HashMap<>();
        Integer i = 1;
        String monthNumber="";
        for (String month : months) {
            if (i < 10)
                monthNumber = "0" + i.toString();
            hm.put(month,monthNumber);
        }


        String formattedDate = parts[2]+"-"+hm.get(parts[1])+"-"+parts[0];
        System.out.println("date : " + formattedDate);
        return formattedDate;
    }
}
