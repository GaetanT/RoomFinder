package insa.roomfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by pierre on 13/01/16.
 */
@Root(name="equipments")
public class Equipments implements Parcelable{
    /* Attributes */
    @ElementList(name="equipment", inline = true, required = false)
    private ArrayList<Equipment> equipments = new ArrayList<>();

    /* Constructors */
    public Equipments(){}
    public Equipments(ArrayList<Equipment> e){
        equipments = e;
    }


    /* Methods */
    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    public ArrayList<String> getEquipmentsName() {
        ArrayList<String> equipmentsName = new ArrayList<>();
        for (Equipment equipment : equipments) {
            equipmentsName.add(equipment.getName());
        }
        return equipmentsName;
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }



    protected Equipments(Parcel in) {
        this.equipments = new ArrayList<>();
        in.readTypedList(equipments, Equipment.CREATOR);
    }

    public static final Creator<Equipments> CREATOR = new Creator<Equipments>() {
        @Override
        public Equipments createFromParcel(Parcel in) {
            return new Equipments(in);
        }

        @Override
        public Equipments[] newArray(int size) {
            return new Equipments[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(equipments);
    }

    @Override
    public String toString() {
        String equimentsNames = "";
        for (Equipment equipment : equipments) {
            equimentsNames = equimentsNames + ", " + equipment.getName();
        }
        if (!equimentsNames.isEmpty())
            equimentsNames = equimentsNames.substring(2);
        return equimentsNames;
    }
}
