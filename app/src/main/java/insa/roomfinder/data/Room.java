package insa.roomfinder.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Gaetan on 09/01/2016.
 */
@Root(name="extendedRoom")
public class Room {
    @Element(name="id")
    private int id;

    @Element(name="name")
    private String name;

    @Element(name="site")
    private Site site;

    @Element(name = "size")
    private Integer size;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
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

}
