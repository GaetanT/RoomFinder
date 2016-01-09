package insa.roomfinder;

import retrofit.Callback;
import retrofit.http.*;
/**
 * Created by pierre on 09/01/16.
 */
public interface NetworkInterface {
    String ENDPOINT = "http://192.168.0.101:8080/Sopra_Online_Server/webresources";

    @POST("/search")
    void sendXMLRequest(@Query("xml") String xml, Callback<Void> callback);
}
