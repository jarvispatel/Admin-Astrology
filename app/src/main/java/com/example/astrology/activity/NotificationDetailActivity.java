package com.example.astrology.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.astrology.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class NotificationDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView openMenu, back;
    RelativeLayout add_ground;
    AdView adView;
    String notification_long_description;
    WebView notDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        initViews();
        setClicks();

        Intent intent = getIntent();
        notification_long_description = intent.getStringExtra("notification_long_description");
        notDetails.getSettings().setTextZoom(120);
        notDetails.loadData(notification_long_description, "text/html; charset=utf-8", "UTF-8");
    }

    private void setClicks() {
        openMenu.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initViews() {
        openMenu = findViewById(R.id.openMenu);
        adView = findViewById(R.id.adView);
        add_ground = findViewById(R.id.add_ground);
        back = findViewById(R.id.back);
        notDetails = findViewById(R.id.notDetails);
        notDetails.setOnLongClickListener(view -> true);

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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.openMenu:
                Show_bottom_chooser();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void Show_bottom_chooser() {
        View view = getLayoutInflater().inflate(R.layout.choose_action_type, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.TransparentDialog);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        CardView close, share;
        close = view.findViewById(R.id.close);
        share = view.findViewById(R.id.share);
        share.setOnClickListener(v -> dialog.dismiss());
        close.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
    }
}