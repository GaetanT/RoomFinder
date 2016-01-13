package insa.roomfinder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gaetan on 09/01/2016.
 */
    public class RoomAdapter extends ArrayAdapter<Room> {
        //rooms est la liste des models à afficher
        public RoomAdapter(Context context, List<Room> rooms) {
            super(context, 0, rooms);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int couleur;

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.salle,parent, false);
            }

            SalleViewHolder viewHolder = (SalleViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new SalleViewHolder();
                viewHolder.nom = (TextView) convertView.findViewById(R.id.name);
                viewHolder.info = (TextView) convertView.findViewById(R.id.info);
                viewHolder.dispo = (ImageView) convertView.findViewById(R.id.dispo);
                convertView.setTag(viewHolder);
            }

            //getItem(position) va récupérer l'item [position] de la List<Room> salles
            Room room = getItem(position);

            //il ne reste plus qu'à remplir notre vue
            viewHolder.nom.setText(room.getName());
            //viewHolder.info.setText(room.getInfo());

           // if(room.isDispo()){
            if (true) {
                couleur= Color.GREEN;
            }else{
                couleur=Color.RED;
            }

            viewHolder.dispo.setImageDrawable(new ColorDrawable(couleur));

            return convertView;

        }

        private class SalleViewHolder{
            public TextView nom;
            public TextView info;
            public ImageView dispo;
        }
    }

