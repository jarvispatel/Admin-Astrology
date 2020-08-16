package com.example.astrology.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.adapter.ZodiacSignAdapter;
import com.example.astrology.interfaces.CliclListener;
import com.example.astrology.models.ZodiacModel;

import java.util.ArrayList;

public class Dialog {

    Context context;
    ArrayList<ZodiacModel> zodiacList;
    CliclListener cliclListener;

    public Dialog(Context context, CliclListener cliclListener) {
        this.context = context;
        this.cliclListener = cliclListener;
        zodiacList = getZodiacList();
    }

    public Dialog(Context context) {
        this.context = context;
    }

    public void openZodiacSelector() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogContent = inflater.inflate(R.layout.select_zodiac_sign_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogContent);
        AlertDialog dialog = builder.create();

        RecyclerView zodiacSignRecy = dialogContent.findViewById(R.id.zodiacSignRecy);
        zodiacSignRecy.setLayoutManager(new LinearLayoutManager(context));
        zodiacSignRecy.setAdapter(new ZodiacSignAdapter(context, zodiacList, cliclListener, dialog));
        dialog.show();
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
