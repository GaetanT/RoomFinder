package insa.roomfinder.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by pierre on 14/01/16.
 */
@Root(name = "extendedRoom")
public class ExtendedRoom {
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

        /* Methods */

    public Equipments getEquipments() {
        return equipments;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<String> getEquipmentsName() {
        return equipments.getEquipmentsName();
    }
}
