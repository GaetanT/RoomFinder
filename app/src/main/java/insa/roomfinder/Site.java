package insa.roomfinder;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pierre on 10/01/16.
 */
@Root(name="rdSite")
public class Site {

    @Element(name="rfName")
    private String name;
}
