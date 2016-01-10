package insa.roomfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListeSalles extends Activity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_salle);
        mListView = (ListView) findViewById(R.id.listView);

        List<Salle> salles = genererSalles();

        SalleAdapter adapter = new SalleAdapter(ListeSalles.this, salles);
        mListView.setAdapter(adapter);
    }

    private List<Salle> genererSalles(){
        List<Salle> salles = new ArrayList<Salle>();
        salles.add(new Salle(Boolean.TRUE, "Salle1", "50 personnes"));
        salles.add(new Salle(Boolean.FALSE, "Salle2", "4000 personnes"));
        salles.add(new Salle(Boolean.TRUE, "Salle3", "BOOM"));
        salles.add(new Salle(Boolean.FALSE, "Salle4", "Il est quelle heure ??"));
        salles.add(new Salle(Boolean.TRUE, "Salle5", "Il y a plein de salles"));
        return salles;
    }
}
