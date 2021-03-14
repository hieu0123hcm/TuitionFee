package com.example.tuitionfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionCustomListAdapter extends BaseAdapter {
    Context context;
    List<Transaction> transactionList = new ArrayList<>();

    public TransactionCustomListAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @Override
    public int getCount() {
        return transactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return transactionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView== null){
            convertView = LayoutInflater.from(context).inflate(R.layout.transaction_item_list, parent,false);
        }else{
            TextView txtAmount = convertView.findViewById(R.id.transAmount);
            TextView txtDate = convertView.findViewById(R.id.transDate);

            Transaction currentTrans = transactionList.get(position);

            txtAmount.setText(String.valueOf(currentTrans.getAmount()));
            txtDate.setText(currentTrans.getDate());
        }
        return convertView;
    }
}
