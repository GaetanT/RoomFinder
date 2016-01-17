package insa.roomfinder.requests;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import insa.roomfinder.data.Equipments;

/**
 * Created by pierre on 14/01/16.
 */
@Root(name="request")
public class SearchRequest {
    @Element(name = "name", required = false)
    private String name;

    @Element(name = "equipments",  required = false)
    private Equipments equipments;

    @Element(name = "day", required = false)
    private String day;

    @Element(name = "startSlot", required = false)
    private Integer startSlot;

    @Element(name = "endslot", required = false)
    private Integer endSlot;

    @Element(name = "size", required = false)
    private Integer size;

    @Element(name = "site", required = false)
    private String site;

    public SearchRequest(String name,Equipments equipments, Integer startSlot, Integer endSlot, Integer size, String site, String day) {
        if (name != null && !name.isEmpty())
            this.name=name;
        if (equipments != null)
            this.equipments=equipments;
        if (startSlot != null)
            this.startSlot=startSlot;
        if (endSlot != null)
            this.endSlot=endSlot;
        if (site != null)
            this.size=size;
        if (site != null)
            this.site=site;
        if (day != null)
            this.day=day;

    }
}
