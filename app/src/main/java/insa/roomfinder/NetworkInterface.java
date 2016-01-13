package insa.roomfinder;

import insa.roomfinder.Responses.ConnectionResponse;
import insa.roomfinder.requests.ConnectionRequest;
import insa.roomfinder.requests.Request;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
/**
 * Created by pierre on 09/01/16.
 */
public interface NetworkInterface {
    String ENDPOINT = "http://192.168.0.101:8080/Sopra_Server/webresources/";
    
    @Headers("Content-Type: application/xml; charset=utf-8")
    @POST("search")
    Call<Void> sendXMLRequest(@Body Request req);

    @Headers("Content-Type: application/xml; charset=utf-8")
    @GET("room")
    Call<Rooms> getRooms();

    @Headers("Content-Type: application/xml; charset=utf-8")
    @GET("site")
    Call<Sites> getSites();

    @Headers("Content-Type: application/xml; charset=utf-8")
    @POST("connect")
    Call<ConnectionResponse> attemptConnection(@Body ConnectionRequest xml);

}
