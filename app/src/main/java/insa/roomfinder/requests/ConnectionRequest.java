package insa.roomfinder.requests;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by pierre on 12/01/16.
 */
@Root(name="credentials")
public class ConnectionRequest {
    @Element(name="email")
    private String mEmail;

    @Element(name="password")
    private String mPassword;

    public ConnectionRequest(String email, String password) {
        mEmail = email;
        mPassword = password;
    }
}
