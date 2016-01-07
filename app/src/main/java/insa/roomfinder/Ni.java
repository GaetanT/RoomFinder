package insa.roomfinder;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pierre on 03/01/16.
 * That's the Network Interface
 */
public class Ni {

    //Singleton pattern
    private static Ni INSTANCE = new Ni();
    private Ni() {}
    public static Ni getInstance() {return INSTANCE;}

    public void sendAFilterSearch (String xml) throws IOException {
        try {
            URL url = new URL("http://serveurHenri.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("POST");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

            writer.write(xml);
            writer.flush();
            writer.close();

            //Si la requeste s'est bien passé
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

            } else {
                System.out.println("Error :'(");
            }
        } catch (MalformedURLException e) {
            System.out.println("Snif");
        } catch (IOException e) {
            System.out.println("Snifette :(");
        }


    }
}
