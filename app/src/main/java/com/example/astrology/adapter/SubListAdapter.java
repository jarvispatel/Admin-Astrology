package com.example.astrology.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.models.HomeRashiSubDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubListAdapter extends RecyclerView.Adapter<SubListAdapter.View_holder> {
    Context context;
    ArrayList<HomeRashiSubDataModel> homeRashiSubDataModelsList;

    public SubListAdapter(Context context, ArrayList<HomeRashiSubDataModel> homeRashiSubDataModelsList) {
        this.context = context;
        this.homeRashiSubDataModelsList = homeRashiSubDataModelsList;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new View_holder(LayoutInflater.from(context).inflate(R.layout.sub_item_lay, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder view_holder, int i) {
        HomeRashiSubDataModel homeRashiSubDataModel = homeRashiSubDataModelsList.get(i);
        view_holder.item_content.setText(homeRashiSubDataModel.getDescription());
        view_holder.item_title.setText(homeRashiSubDataModel.getTitle());

        if (!TextUtils.isEmpty(homeRashiSubDataModel.getTitle_image())) {
            Picasso.get().load(homeRashiSubDataModel.getTitle_image()).into(view_holder.emoji_img);
        }
    }

    @Override
    public int getItemCount() {
        return homeRashiSubDataModelsList.size();
    }

    public class View_holder extends RecyclerView.ViewHolder {

        ImageView emoji_img;
        TextView item_title, item_content;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            item_content = itemView.findViewById(R.id.item_content);
            emoji_img = itemView.findViewById(R.id.emoji_img);
            item_title = itemView.findViewById(R.id.item_title);
        }
    }
}

