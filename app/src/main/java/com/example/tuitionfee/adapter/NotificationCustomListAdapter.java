package com.example.tuitionfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Notification;
import com.example.tuitionfee.model.Studying;

import java.util.List;

public class NotificationCustomListAdapter extends BaseAdapter {
    private Context context;
    private List<Notification> notificationList;

    public NotificationCustomListAdapter(Context context, List<Notification> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Notification getItem(int position) {
        return notificationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.notification_list_items, parent, false);
        }
        Notification currentNotification = (Notification) getItem(position);
        TextView txtSendID = convertView.findViewById(R.id.txtSendID);
        TextView txtMessage = convertView.findViewById(R.id.txtMessage);
        TextView txtDate = convertView.findViewById(R.id.txtNotiDate);

        txtDate.setText(currentNotification.getCreated_on());
        txtSendID.setText(currentNotification.getSendid());
        txtMessage.setText(currentNotification.getMessage());
        return convertView;
    }
}
