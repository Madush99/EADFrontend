/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fuelappead.model.Fuel;
import com.example.fuelappead.model.Que;

import java.util.List;

public class QueAdapter extends ArrayAdapter<Que> {

    private Context context;

    public QueAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Que> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public QueAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Que> objects) {
        super(context, resource, objects);
        this.context = context;
        this.ques = objects;
    }

    private List<Que> ques;

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fuel_listuser, parent, false);

        TextView txtCountcar = rowView.findViewById(R.id.countcar1);
        TextView txtCountVan = rowView.findViewById(R.id.countvan1);
        TextView txtCountBus = rowView.findViewById(R.id.countbus1);
        TextView textTot = rowView.findViewById(R.id.countTot);

        txtCountcar.setText(String.format("Car Count: %s", ques.get(pos).getCar()));
        txtCountVan.setText(String.format("Van Count: %s", ques.get(pos).getVan()));
        txtCountBus.setText(String.format("Bus Count: %s", ques.get(pos).getBus()));
        textTot.setText(String.format("Total Vehicles: %s", ques.get(pos).getTotal()));

        return rowView;
    }
}
