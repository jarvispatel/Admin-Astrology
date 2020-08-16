package com.example.astrology.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.interfaces.SelectRashiPosition;
import com.example.astrology.models.ZodiacModel;

import java.util.ArrayList;

public class RashiListAdapter extends RecyclerView.Adapter<RashiListAdapter.View_holder> {

    Context context;
    ArrayList<ZodiacModel> zodiacList;
    Integer current_selection;
    SelectRashiPosition selectRashiPosition;

    public RashiListAdapter(Context context, ArrayList<ZodiacModel> zodiacList, SelectRashiPosition selectRashiPosition) {
        this.context = context;
        this.zodiacList = zodiacList;
        this.selectRashiPosition = selectRashiPosition;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new View_holder(LayoutInflater.from(context).inflate(R.layout.rashi_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder view_holder, int i) {
        ZodiacModel zodiacModel = zodiacList.get(i);
        view_holder.sign.setImageDrawable(context.getDrawable(zodiacModel.getZodiacImg()));
        view_holder.text.setText(zodiacModel.getZodiacTxt());

        if (current_selection != null) {
            if (i == current_selection) {
                view_holder.card_back_selection.setBackground(context.getDrawable(R.drawable.thick_big_corner_border_dark_black));
            } else {
                view_holder.card_back_selection.setBackground(null);
            }
        }

        view_holder.itemView.setOnClickListener(view -> {
            current_selection = i;
            selectRashiPosition.clickedRashiPosition(zodiacModel);
            notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return zodiacList.size();
    }

    public class View_holder extends RecyclerView.ViewHolder {

        ImageView sign;
        TextView text;
        ConstraintLayout card_back_selection;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            sign = itemView.findViewById(R.id.sign);
            text = itemView.findViewById(R.id.text);
            card_back_selection = itemView.findViewById(R.id.card_back_selection);
        }
    }
}
