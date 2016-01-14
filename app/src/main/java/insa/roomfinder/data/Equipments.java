package insa.roomfinder.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by pierre on 13/01/16.
 */
@Root(name="equipments")
public class Equipments {
    /* Attributes */
    @ElementList(name="equipment", inline = true, required = false)
    private ArrayList<Equipment> equipments;

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
}
