package insa.roomfinder;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit.http.Query;

/**
 * Created by pierre on 03/01/16.
 * That's the Network Interface
 */
public class Ni implements NetworkInterface{

    //Singleton pattern
    private static Ni INSTANCE = new Ni();
    private Ni() {}
    public static Ni getInstance() {return INSTANCE;}

    @Override
    public void sendXMLRequest(@Query("xml") String xml, retrofit.Callback<Void> callback) {

    }

    /*
       try {
            URL url = new URL("http://192.168.0.101:8080/Sopra_Online_Server/webresources/search");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
            //connection.setRequestProperty("Expect", "100-continue");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

            writer.write(xml);
            writer.flush();
            writer.close();

            //Si la requeste s'est bien passé
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("Ca marche :) //////////////////:");
            } else {
                System.out.println(connection.getResponseCode());
                System.out.println("Error :'(");
            }
        } catch (MalformedURLException e) {
            System.out.println("Snif");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Snifette :(");
        }
     */
}
