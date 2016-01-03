package insa.roomfinder;


/**
 * Created by pierre on 03/01/16.
 */
public class MonthsUtil {

    public static String monthToString (int month) {
        String[] months = {"Jan.", "Feb.", "March", "April", "May", "June", "July", "Aug.", "Sept.", "Nov.", "Oct.", "Dec."};
        return months[month+1];
    }
}
