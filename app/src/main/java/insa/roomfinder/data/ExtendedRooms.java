package insa.roomfinder.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by pierre on 14/01/16.
 */
@Root(name ="extendedRooms")
public class ExtendedRooms {

    @ElementList(name = "extendedRoom", inline = true)
    ArrayList<ExtendedRoom> extendedRooms;

    public ExtendedRooms(){extendedRooms = new ArrayList<>();}
    public ExtendedRooms(ArrayList<ExtendedRoom> e) {
        this.extendedRooms=e;
    }

    public ArrayList<ExtendedRoom> getExtendedRooms() {
        return extendedRooms;
    }

    public ArrayList<String> getRoomsName() {
        ArrayList<String> roomsName = new ArrayList<>();
        for (ExtendedRoom extendedRoom : extendedRooms) {
            roomsName.add(extendedRoom.getRoom().getName());
        }
        return roomsName;
    }

    public void addExtendedRoom(int index, ExtendedRoom extendedRoom) {
        extendedRooms.add(index, extendedRoom);
    }


    public ExtendedRooms roomsToExtendedRooms(Rooms rooms) {
        ExtendedRooms extendedRooms = new ExtendedRooms();
        ExtendedRooms extendedRoomsData = Data.getInstance().getExtendedRooms();
        for(Room room : rooms.getmRooms()) {
            extendedRooms.extendedRooms.add(extendedRoomsData.getExtendedRooms().get(room.getId())); // The index corresponds with the id.
        }
        return extendedRooms;
    }
}
