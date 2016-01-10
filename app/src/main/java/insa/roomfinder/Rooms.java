package insa.roomfinder;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by pierre on 10/01/16.
 */

@Root(name="rooms")
public class Rooms {

    @ElementList(inline = true)
    private ArrayList<Room> mRooms;

    public ArrayList<Room> getmRooms() {
        return mRooms;
    }

    public void addRoom(Room room) {
        mRooms.add(room);
    }
}
