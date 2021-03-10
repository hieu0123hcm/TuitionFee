package com.example.tuitionfee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.model.Loan;

import java.util.List;

public class LoanCustomListAdapter extends BaseAdapter {
    private Context context;
    private List<Loan> loanList;

    public LoanCustomListAdapter(Context context, List<Loan> loanList) {
        this.context = context;
        this.loanList = loanList;
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
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.loan_list_items, parent, false);
        }
        Loan currentLoan = (Loan) getItem(position);
        TextView txtLoanID = (TextView) convertView.findViewById(R.id.txtLoanID);
        TextView txtLoanStatus = (TextView) convertView.findViewById(R.id.txtLoanStatus);
        TextView txtExpiredDate = (TextView) convertView.findViewById(R.id.txtExpiredDate);

        txtLoanStatus.setTextColor(0xFF00FF00);
        txtLoanStatus.setAllCaps(true);
        txtLoanID.setText(String.valueOf(currentLoan.getLoanId()));
        txtExpiredDate.setText(String.valueOf(currentLoan.getExpiredDate()));
        txtLoanStatus.setText(currentLoan.getLoanStatus());

        return convertView;
    }
}
