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
    public class SalleAdapter extends ArrayAdapter<Salle> {

        //salles est la liste des models à afficher
        public SalleAdapter(Context context, List<Salle> salles) {
            super(context, 0, salles);
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
                viewHolder.nom = (TextView) convertView.findViewById(R.id.nom);
                viewHolder.info = (TextView) convertView.findViewById(R.id.info);
                viewHolder.dispo = (ImageView) convertView.findViewById(R.id.dispo);
                convertView.setTag(viewHolder);
            }

            //getItem(position) va récupérer l'item [position] de la List<Salle> salles
            Salle salle = getItem(position);

            //il ne reste plus qu'à remplir notre vue
            viewHolder.nom.setText(salle.getNom());
            viewHolder.info.setText(salle.getInfo());
            if(salle.isDispo()){
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

