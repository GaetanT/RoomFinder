package insa.roomfinder;

import org.simpleframework.xml.Element;

/**
 * Created by pierre on 10/01/16.
 */

@Element(name="site")
public class Site {

    /* Attributes */
    @Element(name="name")
    private String name;

    /* Constructor */
    public Site(){}
    public Site (String name) {
        this.name = name;
    }
    /* Methods */
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
}
