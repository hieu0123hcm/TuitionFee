package com.example.tuitionfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.LoanRemind;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoanRemindAdapter extends BaseAdapter {
    List<LoanRemind> loanRemindList;
    Context context;

    public LoanRemindAdapter(List<LoanRemind> loanRemindList, Context context) {
        this.loanRemindList = loanRemindList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loanRemindList.size();
    }

    @Override
    public LoanRemind getItem(int position) {
        return loanRemindList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_remind_item, parent, false);
        }
        TextView txtLoanIDRe = convertView.findViewById(R.id.txtLoanIDRe);
        TextView txtSendDate = convertView.findViewById(R.id.txtSendDate);
        TextView txtAmountRe = convertView.findViewById(R.id.txtAmountRe);

        LoanRemind currentLoanRemind = loanRemindList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

        Date transDate = new Date();
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try {
            transDate  = sdf.parse(currentLoanRemind.getSendDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTransDate = output.format(transDate);

        txtLoanIDRe.setText("Loan ID: " + currentLoanRemind.getLoanId());
        txtAmountRe.setText("Amount: " + formatter.format(currentLoanRemind.getAmount()));
        txtSendDate.setText("Send Date: " + formattedTransDate);

        return convertView;
    }
}
