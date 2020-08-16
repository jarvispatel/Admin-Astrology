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
import com.example.astrology.models.JyotishItemsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class JyotishAdapter extends RecyclerView.Adapter<JyotishAdapter.View_holder> {

    Context context;
    ArrayList<JyotishItemsModel> jyotishItemsModelArrayList;

    public JyotishAdapter(Context context, ArrayList<JyotishItemsModel> jyotishItemsModelArrayList) {
        this.context = context;
        this.jyotishItemsModelArrayList = jyotishItemsModelArrayList;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new View_holder(LayoutInflater.from(context).inflate(R.layout.jyotish_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder view_holder, int i) {
        JyotishItemsModel jyotishItemsModel = jyotishItemsModelArrayList.get(i);
        view_holder.item_content.setText(jyotishItemsModel.getJyotish_description());
        view_holder.item_title.setText(jyotishItemsModel.getJyotish_title());

        if (!TextUtils.isEmpty(jyotishItemsModel.getJyotish_title_image())) {
            Picasso.get().load(jyotishItemsModel.getJyotish_title_image()).into(view_holder.emoji_img);
        }
    }

    @Override
    public int getItemCount() {
        return jyotishItemsModelArrayList.size();
    }

    public class View_holder extends RecyclerView.ViewHolder {

        ImageView emoji_img;
        TextView item_title, item_content;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            emoji_img = itemView.findViewById(R.id.emoji_img);
            item_title = itemView.findViewById(R.id.item_title);
            item_content = itemView.findViewById(R.id.item_content);
        }
    }
}

