package insa.roomfinder.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="sites")
public class Sites {
    /* Attributes */
    @ElementList(name="site", inline=true)
    private ArrayList<Site> mSites=new ArrayList<>();

    /* Constructors */
    public Sites(){}

    /* Methods */
    public ArrayList<String> getSitesName() {
        ArrayList<String> sitesName = new ArrayList<>();
        for (Site site : mSites) {
            sitesName.add(site.getName());
        }
        return sitesName;
    }
}


