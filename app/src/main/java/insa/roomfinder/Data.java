package insa.roomfinder;

import java.util.ArrayList;

/**
 * Created by pierre on 10/01/16.
 */
public class Data {

    private static Rooms mRooms = new Rooms();

    private static Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    private Data() {
    }

    public Rooms getmRooms() {
        return mRooms;
    }

    public void setmRooms(Rooms rooms) {
        if (rooms != null)
            this.mRooms = rooms;
    }

    public ArrayList<String> getRoomsName() {
        return mRooms.getRoomsName();
    }
}
