package com.example.astrology.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.adapter.NotificationAdapter;
import com.example.astrology.models.NotificationModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView notificationRecy;
    RelativeLayout back_btn, add_ground;
    AdView adView;
    CardView progress_lay;
    DatabaseReference databaseReference;
    ArrayList<NotificationModel> notificationModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("notification_list");
        /*SimpleDateFormat DetailModel = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a", new Locale("hi", "IN"));
        NotificationModel notificationModel = new NotificationModel("एप्लिकेशन लॉन्च", "हम अपना नया ज्योतिष ऐप लॉन्च करने जा रहे हैं", DetailModel.format(new java.util.Date()).toUpperCase());
        databaseReference.child(databaseReference.push().getKey()).setValue(notificationModel);*/
        initViews();
        setClicks();
        getData();
    }

    private void getData() {
        progress_lay.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    return;
                }
                notificationModelArrayList = new ArrayList<>();
                if (dataSnapshot.getChildrenCount() != 0) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        NotificationModel notificationModel = snapshot.getValue(NotificationModel.class);
                        notificationModel.setItem_key(snapshot.getKey());
                        notificationModelArrayList.add(notificationModel);
                    }
                }

                setAdapters(notificationModelArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setAdapters(ArrayList<NotificationModel> notificationModelArrayList) {
        progress_lay.setVisibility(View.GONE);
        notificationRecy.setAdapter(new NotificationAdapter(this, notificationModelArrayList));
    }

    private void setClicks() {
        back_btn.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void initViews() {
        notificationRecy = findViewById(R.id.notificationRecy);
        back_btn = findViewById(R.id.back_btn);
        adView = findViewById(R.id.adView);
        add_ground = findViewById(R.id.add_ground);
        progress_lay = findViewById(R.id.progress_lay);
        notificationRecy.setLayoutManager(new LinearLayoutManager(this));

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                add_ground.setVisibility(View.VISIBLE);
            }
        });
    }
}