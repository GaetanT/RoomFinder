package insa.roomfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Gaetan on 09/01/2016.
 */
@Root(name="room")
public class Room implements Parcelable {
    @Element(name="id")
    private int id;

    @Element(name="name")
    private String name;

    @Element(name="site")
    private Site site;

    @Element(name = "size")
    private int size;

    @Element(name="equipments", required = false)
    private Equipments equipments;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(int id, String name, Site site, int size) {
        this.id = id;
        this.name = name;
        this.site = site;
        this.size = size;
        this.equipments = new Equipments();
    }

    protected Room(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.site = (Site) in.readValue(Site.class.getClassLoader());
        this.size = in.readInt();
        this.equipments = (Equipments) in.readValue(Equipments.class.getClassLoader());
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public Site getSite() {
        return site;
    }
    public int getId() {return id;}
    public Equipments getEquipments() {
        return equipments;
    }
    public ArrayList<String> getEquipmentsName() {
        return equipments.getEquipmentsName();
    }



    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeValue(site);
        dest.writeInt(size);
        dest.writeValue(equipments);
    }
}
