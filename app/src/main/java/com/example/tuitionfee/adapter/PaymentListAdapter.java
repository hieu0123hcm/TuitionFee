package com.example.tuitionfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Payment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentListAdapter extends BaseAdapter {
    private List<Payment> paymentList;
    private Context context;

    public PaymentListAdapter(List<Payment> paymentList, Context context) {
        this.paymentList = paymentList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return paymentList.size();
    }

    @Override
    public Payment getItem(int position) {
        return paymentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.payment_items, parent,false);
        }
            Payment currentPayment = getItem(position);

            TextView txtPayID = convertView.findViewById(R.id.txtPayID);
            TextView txtStudentIDPay = convertView.findViewById(R.id.txtStudentIDPay);
            TextView txtAmmountPay = convertView.findViewById(R.id.txtAmmountPay);
            TextView txtPayDate = convertView.findViewById(R.id.txtPayDate);

            DecimalFormat formatter = new DecimalFormat("###,###,###");

            txtPayID.setText("Payment ID: " + String.valueOf(currentPayment.getPayment_id()));
            txtAmmountPay.setText("Amount: " + formatter.format(currentPayment.getAmount()));
            txtStudentIDPay.setText("Student ID: " +currentPayment.getStudentID());
            txtPayDate.setText("Date: " +currentPayment.getCreated_on());

        return convertView;
    }
}
