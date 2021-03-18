package com.example.tuitionfee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuitionfee.R;
import com.example.tuitionfee.model.Notification;
import com.example.tuitionfee.model.Student;
import com.example.tuitionfee.model.Studying;
import com.example.tuitionfee.remote.APIUtils;
import com.example.tuitionfee.remote.StudentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationCustomListAdapter extends BaseAdapter {
    private Context context;
    private List<Notification> notificationList;
    private StudentService studentService = APIUtils.getStudentService();


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

        Call<Student> call = studentService.getStudentByAccID(currentNotification.getSendid());
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if(response.isSuccessful()){
                    txtSendID.setText(response.body().getStudent_id());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

            }
        });

        txtDate.setText(currentNotification.getCreated_on());
        txtMessage.setText(currentNotification.getMessage());
        return convertView;
    }


}
