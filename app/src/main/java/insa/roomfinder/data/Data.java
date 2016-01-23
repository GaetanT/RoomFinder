package insa.roomfinder.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pierre on 10/01/16.
 */
public class Data {

    /* Attributes */
    private static ExtendedRooms mExtendedRooms = new ExtendedRooms();
    private static Sites mSites = new Sites();
    private static Data ourInstance = new Data();
    private static Equipments mEquipmentsList = new Equipments();
    private static HashMap<Integer,Integer> idToIndexExtendedRooms = new HashMap<>();

    /* Constructors */
    public static Data getInstance() {
        return ourInstance;
    }
    private Data() {
    }

    /* Methods */
    public ExtendedRooms getExtendedRooms() {return mExtendedRooms;}
    public ArrayList<String> getExtendedRoomsName() {return mExtendedRooms.getRoomsName();}
    public void setExtendedRooms(ExtendedRooms extendedRooms) {
        if (extendedRooms != null)
            mExtendedRooms = extendedRooms;
    }

    public Sites getSites() {return mSites;}
    public ArrayList<String> getSitesName() {return mSites.getSitesName();}
    public void setSites(Sites sites) {
        if (sites != null)
            mSites = sites;
    }

    public Equipments getEquipmentsList(){return mEquipmentsList;}
    public ArrayList<String> getEquipmentsName(){return mEquipmentsList.getEquipmentsName();}
    public void setEquipmentsList(Equipments equipmentsList) {
        if (equipmentsList != null)
            mEquipmentsList = equipmentsList;
    }

    public void setIdToIndexExtendedRooms(HashMap<Integer,Integer> hm) {
        idToIndexExtendedRooms = hm;
    }

    public HashMap<Integer, Integer> getIdToIndexExtendedRooms() {
        return idToIndexExtendedRooms;
    }
}
