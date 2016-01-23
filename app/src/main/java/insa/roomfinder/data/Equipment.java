package insa.roomfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pierre on 13/01/16.
 */
@Root(name="equipment")
public class Equipment implements Parcelable{
    /* Attributes */
    @Element(name="name")
    private String name;

    /* Constructors */
    public Equipment(){}
    public Equipment(String name) {
        this.name=name;
    }
    protected Equipment(Parcel in) {
        name = in.readString();
    }

    /* Methods */
    public String getName() {return name;}

    public static final Creator<Equipment> CREATOR = new Creator<Equipment>() {
        @Override
        public Equipment createFromParcel(Parcel in) {
            return new Equipment(in);
        }

        @Override
        public Equipment[] newArray(int size) {
            return new Equipment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
