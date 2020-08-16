package com.example.astrology.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.interfaces.CliclListener;
import com.example.astrology.models.ZodiacModel;

import java.util.ArrayList;

public class ZodiacSignAdapter extends RecyclerView.Adapter<ZodiacSignAdapter.View_holder> {
    Context context;
    ArrayList<ZodiacModel> zodiacList;
    CliclListener cliclListener;
    AlertDialog dialog;

    public ZodiacSignAdapter(Context context, ArrayList<ZodiacModel> zodiacList, CliclListener cliclListener, AlertDialog dialog) {
        this.context = context;
        this.zodiacList = zodiacList;
        this.cliclListener = cliclListener;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new View_holder(LayoutInflater.from(context).inflate(R.layout.zodiac_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder view_holder, final int position) {
        final ZodiacModel zodiacModel = zodiacList.get(position);
        view_holder.signImg.setImageDrawable(context.getDrawable(zodiacModel.getZodiacImg()));
        view_holder.signNameTxt.setText(zodiacModel.getZodiacTxt());
        view_holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cliclListener.clickedPosition(zodiacModel);
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return zodiacList.size();
    }

    public class View_holder extends RecyclerView.ViewHolder {

        ImageView signImg;
        TextView signNameTxt;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            signImg = itemView.findViewById(R.id.signImg);
            signNameTxt = itemView.findViewById(R.id.signNameTxt);
        }
    }
}

