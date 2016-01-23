package insa.roomfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pierre on 14/01/16.
 */
@Root(name ="extendedRooms")
public class ExtendedRooms implements Parcelable {

    /* Attributes */
    @ElementList(name = "extendedRoom", inline = true)
    private ArrayList<ExtendedRoom> extendedRooms;


    /* Constructors */
    public ExtendedRooms(){extendedRooms = new ArrayList<>();}
    public ExtendedRooms(ArrayList<ExtendedRoom> e) {
        this.extendedRooms=e;
    }
    public ExtendedRooms(Parcel in) {
        this.extendedRooms = new ArrayList<>();
        in.readTypedList(extendedRooms, ExtendedRoom.CREATOR);
    }

    /* Methods */

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
    public void addExtendedRoom(int index, ExtendedRoom extendedRoom) {extendedRooms.add(index, extendedRoom);}
    public ExtendedRooms roomsToExtendedRooms(Rooms rooms) {
        ExtendedRooms extendedRooms = new ExtendedRooms();
        ExtendedRooms extendedRoomsData = Data.getInstance().getExtendedRooms();
        HashMap<Integer,Integer> idToIndexExtendedRooms = Data.getInstance().getIdToIndexExtendedRooms();
        for(Room room : rooms.getmRooms()) {
            System.out.print("index de la room " + room.getId() + " : ");
            System.out.println(idToIndexExtendedRooms.get(room.getId()));
            extendedRooms.getExtendedRooms().add(extendedRoomsData.getExtendedRooms().get(idToIndexExtendedRooms.get(room.getId())));
        }
        return extendedRooms;
    }
    public static final Creator<ExtendedRooms> CREATOR = new Creator<ExtendedRooms>() {
        @Override
        public ExtendedRooms createFromParcel(Parcel in) {
            return new ExtendedRooms(in);
        }

        @Override
        public ExtendedRooms[] newArray(int size) {
            return new ExtendedRooms[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.extendedRooms);
    }


}
