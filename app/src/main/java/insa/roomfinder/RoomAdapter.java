package insa.roomfinder;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gaetan on 09/01/2016.
 */
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.SalleViewHolder> {
    //rooms est la liste des models à afficher
    private ArrayList<Room> mDataset;

    public RoomAdapter(ArrayList<Room> rooms) {
            mDataset = rooms;
        }

    @Override
    public SalleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.salle, parent, false);

        SalleViewHolder vh = new SalleViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SalleViewHolder holder, int position) {

        int couleur;

        //getItem(position) va récupérer l'item [position] de la List<Room> salles
        holder.mV.setTag(holder);
        Room room = mDataset.get(position);

        //il ne reste plus qu'à remplir notre vue
        holder.nom.setText(room.getName());
        holder.size.setText(String.valueOf(room.getSize()));

        // if(room.isDispo()){
        if (true) {
            couleur= Color.GREEN;
        }else{
            couleur=Color.RED;
        }

        holder.dispo.setImageDrawable(new ColorDrawable(couleur));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class SalleViewHolder extends RecyclerView.ViewHolder  {
        public TextView nom;
        public TextView size;
        public ImageView dispo;
        public View mV;

        public SalleViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.name);
            size = (TextView) v.findViewById(R.id.size);
            dispo = (ImageView) v.findViewById(R.id.dispo);
            mV = v;
        }
    }
}

