package insa.roomfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by pierre on 14/01/16.
 */
@Root(name = "extendedRoom")
public class ExtendedRoom implements Parcelable {
        /*Attributes */
        @Element(name = "room")
        private Room room;
        @Element(name="equipments", required = false)
        private Equipments equipments = new Equipments();

        /* Constructors */
        public ExtendedRoom(){}
        public ExtendedRoom(Room room, Equipments equipments) {
            this.equipments=equipments;
            this.room=room;
        }
    protected ExtendedRoom(Parcel in) {
        this.room = (Room) in.readValue(Room.class.getClassLoader());
        this.equipments = (Equipments) in.readValue(Equipments.class.getClassLoader());
    }

        /* Methods */
    public Equipments getEquipments() {
        return equipments;
    }
    public Room getRoom() {
        return room;
    }
    public ArrayList<String> getEquipmentsName() {return equipments.getEquipmentsName();}

    public static final Creator<ExtendedRoom> CREATOR = new Creator<ExtendedRoom>() {
        @Override
        public ExtendedRoom createFromParcel(Parcel in) {
            return new ExtendedRoom(in);
        }

        @Override
        public ExtendedRoom[] newArray(int size) {
            return new ExtendedRoom[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(room);
        dest.writeValue(equipments);
    }
}
