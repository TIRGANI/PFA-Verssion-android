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
import emsi.pfa.smart_wattering_v0.ui.beans.Parcelle;

public class ParcelleAdapter extends ArrayAdapter<Parcelle> {
    public ParcelleAdapter(Context context, ArrayList<Parcelle> parcelles) {
        super(context, R.layout.itemparcelle, parcelles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Parcelle parcelle = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemparcelle, parent, false);
        }
        TextView idparcelle = convertView.findViewById(R.id.idparcelle);
        // ImageView fimage = convertView.findViewById(R.id.fimage);
        CircleImageView fimage = convertView.findViewById(R.id.fimages);
        TextView psurface = convertView.findViewById(R.id.psurface);
        idparcelle.setText(parcelle.getId() + "");
        //  fimage.setImageResource(machine.getPrix()+"");
        psurface.setText(parcelle.getSurface() + "");


        return convertView;
    }
}
