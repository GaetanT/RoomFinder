package insa.roomfinder;

import retrofit.http.*;
import retrofit.Call;
/**
 * Created by pierre on 09/01/16.
 */
public interface NetworkInterface {
    String ENDPOINT = "http://192.168.0.101:8080/Sopra_Online_Server/webresources/";


    @Headers("Content-type: application/xml; charset=utf-8")
    @POST("search")
    Call<Void> sendXMLRequest(@Body String xml);

    @Headers("Content-type: application/xml; charset=utf-8")
    @GET("room")
    Call<Rooms> getRooms();

}
