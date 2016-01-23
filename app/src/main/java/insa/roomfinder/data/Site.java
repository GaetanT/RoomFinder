package insa.roomfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pierre on 10/01/16.
 */

@Root(name="site")
public class Site implements Parcelable {

    /* Attributes */
    @Element(name="name")
    private String name;

    /* Constructor */
    public Site(){}
    public Site (String name) {this.name = name;}
    protected Site(Parcel in) {name = in.readString();}

    /* Methods */
    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public static final Creator<Site> CREATOR = new Creator<Site>() {
        @Override
        public Site createFromParcel(Parcel in) {
            return new Site(in);
        }

        @Override
        public Site[] newArray(int size) {
            return new Site[size];
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

