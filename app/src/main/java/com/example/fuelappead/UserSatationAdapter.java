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

import java.util.List;

public class UserSatationAdapter  extends ArrayAdapter<Fuel> {

    private Context context;

    public UserSatationAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Fuel> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public UserSatationAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Fuel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.fuels = objects;
    }

    private List<Fuel> fuels;

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fuel_listuser, parent, false);

        TextView txtShedId = rowView.findViewById(R.id.txtShedId);
        TextView txtShedPhone = rowView.findViewById(R.id.txtShedPhone);
        TextView txtShedName = rowView.findViewById(R.id.txtShedname);
        TextView txtFuelType = rowView.findViewById(R.id.txtFuelType);
        TextView txtFuelStatus = rowView.findViewById(R.id.txtFuelStatus);

        txtShedId.setText((String.format("Shed ID: %s", fuels.get(pos).getId())));
        txtShedPhone.setText(String.format("Shed Phone: %s", fuels.get(pos).getShedPhoneNo()));
        txtShedName.setText(String.format("Shed NAme: %s", fuels.get(pos).getShedName()));
        txtFuelType.setText(String.format("fuel Type: %s", fuels.get(pos).getFuelType()));
        txtFuelStatus.setText(String.format("Fuel Status: %s", fuels.get(pos).getFuelStatus()));

        return rowView;
    }
}
