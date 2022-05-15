package emsi.pfa.smart_wattering_v0.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import emsi.pfa.smart_wattering_v0.R;
import emsi.pfa.smart_wattering_v0.ui.beans.Ferme;

public class FermeAdapter extends ArrayAdapter<Ferme> {
    public FermeAdapter(Context context, ArrayList<Ferme> machines) {
        super(context, R.layout.itemferme, machines);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ferme ferme = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemferme, parent, false);
        }
        TextView idferme = convertView.findViewById(R.id.idferme);
        // ImageView fimage = convertView.findViewById(R.id.fimage);
        CircleImageView fimage = convertView.findViewById(R.id.fimages);
        TextView fnbrparcelle = convertView.findViewById(R.id.fnbrparcelle);
        idferme.setText(ferme.getId() + "");
        //  fimage.setImageResource(machine.getPrix()+"");
        fnbrparcelle.setText(ferme.getNumParcel() + "");


        return convertView;
    }
}
