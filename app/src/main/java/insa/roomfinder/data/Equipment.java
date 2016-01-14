package insa.roomfinder.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pierre on 13/01/16.
 */
@Root(name="equipment")
public class Equipment {
    /* Attributes */
    @Element(name="name")
    private String name;

    /* Constructors */
    public Equipment(){}
    public Equipment(String name) {
        this.name=name;
    }

    /* Methods */
    public String getName() {return name;}
}
