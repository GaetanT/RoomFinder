package insa.roomfinder.Responses;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pierre on 12/01/16.
 */

@Root(name="connection")
public class ConnectionResponse {
    @Element(name="connected")
    private Boolean mConnected;

    public ConnectionResponse(Boolean connected) {
        mConnected = connected;
    }

    public ConnectionResponse(){}

    public Boolean getmConnected() {
        return mConnected;
    }
}
