package com.example.pdms;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class FeesList extends ArrayAdapter<Fees> {
    private Activity context;
    private List<Fees> feesList;

    public FeesList(Activity context, List<Fees> feesList) {
        super(context, R.layout.list_layout, feesList);
        this.context = context;
        this.feesList = feesList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewitem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewLevel = (TextView) listViewitem.findViewById(R.id.textViewLevel);
        TextView textViewBill = (TextView) listViewitem.findViewById(R.id.textViewBill);

        Fees fees = feesList.get(position);
        textViewLevel.setText(fees.getLevel());
        textViewBill.setText(fees.getBill());

        return listViewitem;
    }
}
