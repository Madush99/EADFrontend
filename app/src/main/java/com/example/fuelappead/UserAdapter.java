/**
 * IT19123950 Madusanka G.A.P
 * IT19214580 S.M Bulner
 * 26/10/2022
 */
package com.example.fuelappead;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fuelappead.model.Fuel;
import com.example.fuelappead.model.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;

    public UserAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<User> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    private List<User> users;


    public UserAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    @NonNull
    @Override
    public View getView(int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_users, parent, false);

        TextView txtsehdId = rowView.findViewById(R.id.txtuserId);
        TextView txtShedPhone = rowView.findViewById(R.id.txtShedUserPhone);
        TextView txtUsername = rowView.findViewById(R.id.txtuserName);
        TextView txtUserPhone = rowView.findViewById(R.id.txtuserPhone);
        TextView txtVehType = rowView.findViewById(R.id.txtvehType);

        txtShedPhone.setText(String.format("Gas Station Phone Number: %s", users.get(pos).getShedPhoneNo()));
        txtUsername.setText(String.format("User name: %s", users.get(pos).getUserName()));
        txtUserPhone.setText(String.format("User Phone: %s", users.get(pos).getUserPhoneNo()));
        txtVehType.setText(String.format("Vehicle Type: %s", users.get(pos).getVehicleType()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CheckInQ.class);

                intent.putExtra("user_id", String.valueOf(users.get(pos).getId()));
                intent.putExtra("shed_user_phone", String.valueOf(users.get(pos).getShedPhoneNo()));
                intent.putExtra("user_name", String.valueOf(users.get(pos).getUserName()));
                intent.putExtra("user_phone", String.valueOf(users.get(pos).getUserPhoneNo()));
                intent.putExtra("veh_type", String.valueOf(users.get(pos).getVehicleType()));
                context.startActivity(intent);
            }
        });
        return rowView;

    }
}
