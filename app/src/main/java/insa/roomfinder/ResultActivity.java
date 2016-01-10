package insa.roomfinder;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import org.simpleframework.xml.Root;

public class ResultActivity extends Activity {


    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_salle);
        mListView = (ListView) findViewById(R.id.listView);
        List<Room> rooms = genererSalles();

        RoomAdapter adapter = new RoomAdapter(ResultActivity.this, rooms);
        mListView.setAdapter(adapter);
    }

    private List<Room> genererSalles(){
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Salle1"));
        rooms.add(new Room("Salle2"));
        rooms.add(new Room("Salle3"));
        rooms.add(new Room("Salle4"));
        rooms.add(new Room("Salle5"));
        return rooms;
    }
}