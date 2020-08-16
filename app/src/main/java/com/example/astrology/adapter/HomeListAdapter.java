package com.example.astrology.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.activity.SubDetailActivity;
import com.example.astrology.models.HomeItemsModel;
import com.example.astrology.models.ZodiacModel;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.View_holder> {

    Context context;
    ArrayList<HomeItemsModel> homeItemsModelList;
    ZodiacModel currentSelectedRashi;

    public HomeListAdapter(Context context, ArrayList<HomeItemsModel> homeItemsModel) {
        this.context = context;
        this.homeItemsModelList = homeItemsModel;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new View_holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder view_holder, int i) {
        HomeItemsModel homeItemsModel = homeItemsModelList.get(i);
        view_holder.text.setText(homeItemsModel.getHome_item_label());
        view_holder.itemView.setOnClickListener(view -> {
            switch (homeItemsModel.getItem_type()) {
                case "selection":
                    selectRashiDialog(homeItemsModel.getItem_key());
                    break;
                case "list":
                    Intent intent = new Intent(context, SubDetailActivity.class);
                    intent.putExtra("key", homeItemsModel.getItem_key());
                    intent.putExtra("rashi_name", homeItemsModel.getHome_item_label().trim());
                    context.startActivity(intent);
                    break;
            }
        });

        /*if (!TextUtils.isEmpty(homeItemsModel.getHome_item_back_image())) {
            Picasso.get().load(homeItemsModel.getHome_item_back_image()).into(view_holder.sign);
        }*/
        view_holder.sign.setImageDrawable(context.getDrawable(R.drawable.pisces));
    }


    public void selectRashiDialog(String item_key) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_rashi_lay, null);
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        CardView okay_btn;
        ImageView back_btn;
        okay_btn = dialog.findViewById(R.id.okay_btn);
        back_btn = dialog.findViewById(R.id.back_btn);

        okay_btn.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, SubDetailActivity.class);
            intent.putExtra("rashi", currentSelectedRashi);
            intent.putExtra("key", item_key);
            context.startActivity(intent);
            dialog.dismiss();
        });

        back_btn.setOnClickListener(view12 -> dialog.dismiss());

        RecyclerView rashi_items_recycler;
        rashi_items_recycler = dialog.findViewById(R.id.rashi_items_recycler);
        rashi_items_recycler.setLayoutManager(new GridLayoutManager(context, 3));
        rashi_items_recycler.setAdapter(new RashiListAdapter(context, getZodiacList(), zodiacModel -> currentSelectedRashi = zodiacModel));

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return homeItemsModelList.size();
    }

    public class View_holder extends RecyclerView.ViewHolder {

        ImageView sign;
        TextView text;

        public View_holder(@NonNull View itemView) {
            super(itemView);
            sign = itemView.findViewById(R.id.sign);
            text = itemView.findViewById(R.id.text);
        }
    }

    public ArrayList<ZodiacModel> getZodiacList() {
        ArrayList<ZodiacModel> zodiacList = new ArrayList<>();
        zodiacList.add(new ZodiacModel(R.drawable.mesh, R.string.mesh));
        zodiacList.add(new ZodiacModel(R.drawable.vrushabh, R.string.vrushabh));
        zodiacList.add(new ZodiacModel(R.drawable.mithun, R.string.mithun));
        zodiacList.add(new ZodiacModel(R.drawable.kark, R.string.kark));
        zodiacList.add(new ZodiacModel(R.drawable.singh, R.string.sinh));
        zodiacList.add(new ZodiacModel(R.drawable.kanya, R.string.kanya));
        zodiacList.add(new ZodiacModel(R.drawable.tula, R.string.tula));
        zodiacList.add(new ZodiacModel(R.drawable.vrushvik, R.string.vrushchik));
        zodiacList.add(new ZodiacModel(R.drawable.dhanu, R.string.dhanu));
        zodiacList.add(new ZodiacModel(R.drawable.makar, R.string.makar));
        zodiacList.add(new ZodiacModel(R.drawable.kumbh, R.string.kumbha));
        zodiacList.add(new ZodiacModel(R.drawable.min, R.string.meen));
        return zodiacList;
    }
}
