package com.example.tuitionfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Loan;

import java.util.List;

public class UnactiveLoanAdapter extends BaseAdapter {
    List<Loan> loanList;
    Context context;

    public UnactiveLoanAdapter(List<Loan> loanList, Context context) {
        this.loanList = loanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loanList.size();
    }

    @Override
    public Loan getItem(int position) {
        return loanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.loan_approve_item, parent,false);
        }
        TextView txtStudentID = convertView.findViewById(R.id.txtRequestStuID);
        TextView txtLoanRequestID = convertView.findViewById(R.id.txtLoanRequestID);

        Loan loan = loanList.get(position);

        txtStudentID.setText(loan.getStudentId());
        txtLoanRequestID.setText(String.valueOf(loan.getLoanId()));
        return convertView;
    }
}
