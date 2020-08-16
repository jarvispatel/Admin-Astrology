package com.example.astrology.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.astrology.R;
import com.example.astrology.interfaces.CliclListener;
import com.example.astrology.models.ZodiacModel;
import com.example.astrology.utils.Dialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Settings extends AppCompatActivity implements CliclListener {

    RelativeLayout selectRashiSpinner, back_btn, add_ground;
    ImageView rashiImg;
    TextView rashiTxt;
    Dialog dialog;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dialog = new Dialog(this, this);

        initViews();
        setClicks();
    }

    private void setClicks() {
        selectRashiSpinner.setOnClickListener(view -> dialog.openZodiacSelector());
        back_btn.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void initViews() {
        selectRashiSpinner = findViewById(R.id.selectRashiSpinner);
        rashiImg = findViewById(R.id.rashiImg);
        rashiTxt = findViewById(R.id.rashiTxt);
        back_btn = findViewById(R.id.back_btn);
        adView = findViewById(R.id.adView);
        add_ground = findViewById(R.id.add_ground);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                add_ground.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void clickedPosition(ZodiacModel zodiacModel) {
        rashiImg.setImageDrawable(getDrawable(zodiacModel.getZodiacImg()));
        rashiTxt.setText(zodiacModel.getZodiacTxt());
    }
}