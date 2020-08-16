package com.example.astrology.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.activity.NotificationDetailActivity;
import com.example.astrology.models.NotificationModel;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.View_holder> {

    Context context;
    ArrayList<NotificationModel> notificationModelArrayList;

    public NotificationAdapter(Context context, ArrayList<NotificationModel> notificationModelArrayList) {
        this.context = context;
        this.notificationModelArrayList = notificationModelArrayList;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new View_holder(LayoutInflater.from(context).inflate(R.layout.notification_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder view_holder, int i) {
        NotificationModel notificationModel = notificationModelArrayList.get(i);
        view_holder.itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context, NotificationDetailActivity.class).putExtra("notification_long_description", notificationModel.getNotification_long_description()));
        });
        view_holder.title.setText(notificationModel.getNotification_title());
        view_holder.description.setText(notificationModel.getNotification_description());
        view_holder.date_time.setText(notificationModel.getNotification_time());
    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }

    public class View_holder extends RecyclerView.ViewHolder {

        TextView title, date_time, description;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date_time = itemView.findViewById(R.id.date_time);
            description = itemView.findViewById(R.id.description);
        }
    }
}
