package insa.roomfinder;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Gaetan on 09/01/2016.
 */
@Root(name="room")
public class Room {
    @Element(name="id")
    private double id;

    @Element(name="name")
    private String name;

    @Element(name="site")
    private Site site;

    @Element(name = "size")
    private int size;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(double id, String name, Site site, int size) {
        this.id = id;
        this.name = name;
        this.site = site;
        this.size = size;
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

    public void setSize(int size) {
        this.size = size;
    }
}
