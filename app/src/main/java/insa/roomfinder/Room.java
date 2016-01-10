package insa.roomfinder;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Gaetan on 09/01/2016.
 */
@Root(name="room")
public class Room {
    @Element(name="rfId")
    private double id;

    @Element(name="rfSite")
    private String Site;

    @Element(name="rfName")
    private String name;

    @Element(name = "rfSize")
    private int size;

    public Room(String name) {
        this.name = name;
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
