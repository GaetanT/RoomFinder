package insa.roomfinder;

import insa.roomfinder.data.Equipments;
import insa.roomfinder.data.ExtendedRooms;
import insa.roomfinder.data.Rooms;
import insa.roomfinder.data.Sites;
import insa.roomfinder.requests.ConnectionRequest;
import insa.roomfinder.requests.SearchRequest;
import insa.roomfinder.responses.ConnectionResponse;
import insa.roomfinder.responses.ProfileResponse;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;


public interface NetworkInterface {
    String ENDPOINT = "http://192.168.0.101:8080/Sopra_Server/webresources/";

    @Headers("Content-Type: application/xml; charset=utf-8")
    @GET("room/extended")
    Call<ExtendedRooms> getExtendedRooms();

    @Headers("Content-Type: application/xml; charset=utf-8")
    @GET("site")
    Call<Sites> getSites();

    @Headers("Content-Type: application/xml; charset=utf-8")
    @POST("connect")
    Call<ConnectionResponse> attemptConnection(@Body ConnectionRequest xml);

    @Headers("Content-Type: application/xml; charset=utf-8")
    @GET("employee/{id}")
    Call<ProfileResponse> getProfile(@Path("id") String id);

    @Headers("Content-Type: application/xml; charset=utf-8")
    @GET("equipment")
    Call<Equipments> getEquipmentsList();

    @Headers("Content-Type: application/xml; charset=utf-8")
    @POST("search")
    Call<Rooms> searchRooms(@Body SearchRequest xml);




}
