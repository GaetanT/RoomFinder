package insa.roomfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by pierre on 14/01/16.
 */
@Root(name ="extendedRooms")
public class ExtendedRooms implements Parcelable {

    @ElementList(name = "extendedRoom", inline = true)
    ArrayList<ExtendedRoom> extendedRooms;

    public ExtendedRooms(){extendedRooms = new ArrayList<>();}
    public ExtendedRooms(ArrayList<ExtendedRoom> e) {
        this.extendedRooms=e;
    }
    public ExtendedRooms(Parcel in) {
        this.extendedRooms = new ArrayList<>();
        in.readTypedList(extendedRooms, ExtendedRoom.CREATOR);
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


    public static ExtendedRooms roomsToExtendedRooms(Rooms rooms) {
        ExtendedRooms extendedRooms = new ExtendedRooms();
        ExtendedRooms extendedRoomsData = Data.getInstance().getExtendedRooms();
        for(Room room : rooms.getmRooms()) {
            extendedRooms.getExtendedRooms().add(extendedRoomsData.getExtendedRooms().get(room.getId()-1)); // The index corresponds with the id. //Has to be changed .. :(
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
