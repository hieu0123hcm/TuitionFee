package com.example.tuitionfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoanCustomListAdapterForStudent extends BaseAdapter {
    private Context context;
    private List<Loan> loanList;

    public LoanCustomListAdapterForStudent(Context context, List<Loan> loanList) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");

        Date expiredDate = new Date();
        Date loanDate = new Date();

        try {
            expiredDate  = sdf.parse(currentLoan.getExpiredDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedExpired = output.format(expiredDate);

        txtExpiredDate.setText(formattedExpired);
        txtLoanStatus.setText(currentLoan.getLoanStatus());

        return convertView;
    }
}
