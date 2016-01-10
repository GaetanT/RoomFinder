package insa.roomfinder;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pierre on 10/01/16.
 */
@Root(name="rfSite")
public class Site {

    @Element(name="rfName")
    private String name;
}
