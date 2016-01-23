package insa.roomfinder.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by pierre on 10/01/16.
 */

@Root(name="rooms")
public class Rooms {

    /* attributes */
    @ElementList(name="room", inline = true, required = false)
    private ArrayList<Room> mRooms;

    /* Constructors */
    public Rooms() {
        mRooms = new ArrayList<>();
    }


    /* Methods */
    public void setmRooms(ArrayList<Room> mRooms) {
        this.mRooms = mRooms;
    }
    public ArrayList<Room> getmRooms() {
        return mRooms;
    }
    public void addRoom(Room room) {
        mRooms.add(room);
    }
    public ArrayList<String> getRoomsName() {
        System.out.println("Je suis dans le getRoomsName de Rooms");
        ArrayList<String> roomsName = new ArrayList<>();
        for (Room room : mRooms) {
            roomsName.add(room.getName());
        }
        return roomsName;
    }
}