package insa.roomfinder;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        //getItem(position) va récupérer l'item [position] de la List<Room> salles
        holder.mV.setTag(holder);
        holder.mV.setClickable(true);
        holder.mV.setFocusable(true);
        Room room = mDataset.get(position);

        //il ne reste plus qu'à remplir notre vue
        holder.name.setText(room.getName());
        //holder.site.setText(String.valueOf(room.getSite()));
        holder.size.setText(String.valueOf(room.getSize()));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class SalleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView site;
        public TextView size;
        public ImageView porte;
        public ImageView position;
        public ImageView groupe;
        public View mV;

        public SalleViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            size = (TextView) v.findViewById(R.id.size);
            site = (TextView) v.findViewById(R.id.site);
            porte = (ImageView) v.findViewById(R.id.image_porte);
            position = (ImageView) v.findViewById(R.id.image_position);
            groupe = (ImageView) v.findViewById(R.id.image_groupe);
            mV = v;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Vue :" + String.valueOf(v.getId()), Toast.LENGTH_SHORT).show();
        }
    }
}

