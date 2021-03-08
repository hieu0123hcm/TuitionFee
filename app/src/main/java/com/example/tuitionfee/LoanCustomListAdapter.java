package com.example.tuitionfee;

import android.content.Context;
import android.content.Intent;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.loan_list_adapter, parent, false);
        }
        Loan currentloan = (Loan) getItem(position);
//        System.out.println(currentloan.getStudentId());

        TextView tvStudentId = (TextView) convertView.findViewById(R.id.loanName);
        TextView tvloanID = (TextView) convertView.findViewById(R.id.loanID);

        tvStudentId.setText(currentloan.getStudentId());
        tvloanID.setText("loan ID: " + currentloan.getLoanId());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoanItemActivity.class);
                intent.putExtra("loan_id", currentloan.getLoanId());
                intent.putExtra("loandate", currentloan.getLoanDate());
                intent.putExtra("studentid", currentloan.getStudentId());
                intent.putExtra("expireddate", currentloan.getExpiredDate());
                intent.putExtra("bundleid", currentloan.getBundleId());
                intent.putExtra("amountreturned", currentloan.getAmountReturned());
                intent.putExtra("amount",String.valueOf(currentloan.getAmount()));
                intent.putExtra("loanstatus", currentloan.getLoanStatus());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
