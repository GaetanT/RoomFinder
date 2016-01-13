package insa.roomfinder.Responses;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pierre on 12/01/16.
 */

@Root(name="connection")
public class ConnectionResponse {
    /* Attributes */
    @Element(name="connected")
    private Boolean mConnected;

    @Element(name="userId")
    private Integer mUserId;

    /* Constructors */
    public ConnectionResponse(){}
    public ConnectionResponse(Boolean connected, Integer id) {
        mConnected = connected;
        mUserId = id;
    }

    /* Methods */
    public Boolean getmConnected() {
        return mConnected;
    }
    public Integer getmUserId() {return mUserId;}
}
