package insa.roomfinder.Responses;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import insa.roomfinder.Room;
import insa.roomfinder.Site;

/**
 * Created by pierre on 13/01/16.
 */

@Root(name="employee")
public class ProfileResponse {
    /* Attributes */
    @Element(name="id")
    private Integer id;

    @Element(name="name")
    private String name;

    @Element(name="mail")
    private String mail;

    @Element(name="phone")
    private String phone;

    @Element(name="favRoom")
    private Room favRoom;

    @Element(name="favSite")
    private Site favSite;

    /* Constructors */
    public ProfileResponse(){}
    public ProfileResponse(Integer id, String name, String mail, String phone, Room favRoom, Site favSite) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.favRoom = favRoom;
        this.favSite = favSite;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public Room getFavRoom() {
        return favRoom;
    }

    public Site getFavSite() {
        return favSite;
    }

    public Integer getId() {
        return id;
    }
}

