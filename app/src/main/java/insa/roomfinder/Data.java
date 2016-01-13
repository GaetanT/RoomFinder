package insa.roomfinder;

import java.util.ArrayList;

/**
 * Created by pierre on 10/01/16.
 */
public class Data {

    /* Attributes */
    private static Rooms mRooms = new Rooms();
    private static Sites mSites = new Sites();
    private static Data ourInstance = new Data();

    /* Constructors */
    public static Data getInstance() {
        return ourInstance;
    }
    private Data() {
    }

    /* Methods */
    public Rooms getRooms() {return mRooms;}
    public ArrayList<String> getRoomsName() {return mRooms.getRoomsName();}
    public void setRooms(Rooms rooms) {
        if (rooms != null)
            mRooms = rooms;
    }

    public Sites getSites() {return mSites;}
    public ArrayList<String> getSitesName() {return mSites.getSitesName();}
    public void setSites(Sites sites) {
        if (sites != null)
            mSites = sites;
    }
}
