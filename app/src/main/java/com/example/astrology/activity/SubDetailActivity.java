package com.example.astrology.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.astrology.R;
import com.example.astrology.interfaces.CliclListener;
import com.example.astrology.models.DetailModel;
import com.example.astrology.models.ZodiacModel;
import com.example.astrology.utils.Dialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SubDetailActivity extends AppCompatActivity implements View.OnClickListener, CliclListener {

    //    RecyclerView item_list_sub_recycler;
    RelativeLayout back_btn, selectRashiSpinner;
    Dialog dialog;
    ImageView rashiImg;
    TextView rashiTxt;
    String key;
    ZodiacModel rashi;
    CardView progress_lay;
    DatabaseReference databaseReference;
    //    ArrayList<HomeRashiSubDataModel> homeRashiSubDataModelsList;
    String rashi_name;
    WebView notDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_detail);
        initView();

        dialog = new Dialog(this, this);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        if (intent.hasExtra("rashi_name")) {
            rashi_name = intent.getStringExtra("rashi_name");
            selectRashiSpinner.setVisibility(View.INVISIBLE);
            getData(rashi);
        } else {
            selectRashiSpinner.setVisibility(View.VISIBLE);
            rashi = (ZodiacModel) intent.getSerializableExtra("rashi");
            getData(rashi);
        }

        setClicks();
    }

    public void getData(ZodiacModel rashi) {
        if (rashi == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("home_rashifal_sub_data")
                    .child(key)
                    .child(rashi_name);
        } else {
            rashiImg.setImageDrawable(getDrawable(rashi.getZodiacImg()));
            rashiTxt.setText(rashi.getZodiacTxt());
            databaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("home_rashifal_sub_data")
                    .child(key)
                    .child(getString(rashi.getZodiacTxt()));
        }

        progress_lay.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    return;
                }
                DetailModel detailModel = dataSnapshot.getValue(DetailModel.class);
                notDetails.getSettings().setTextZoom(120);
                notDetails.loadData(detailModel.getLong_description(), "text/html; charset=utf-8", "UTF-8");
                progress_lay.setVisibility(View.GONE);

                /*homeRashiSubDataModelsList = new ArrayList<>();
                if (dataSnapshot.getChildrenCount() != 0) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HomeRashiSubDataModel homeRashiSubDataModel = snapshot.getValue(HomeRashiSubDataModel.class);
                        homeRashiSubDataModelsList.add(homeRashiSubDataModel);
                    }
                }
                setAdapters(homeRashiSubDataModelsList);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*private void setAdapters(ArrayList<HomeRashiSubDataModel> homeRashiSubDataModelsList) {
        progress_lay.setVisibility(View.GONE);
        item_list_sub_recycler.setAdapter(new SubListAdapter(this, homeRashiSubDataModelsList));
    }*/

    private void setClicks() {
        back_btn.setOnClickListener(this);
        selectRashiSpinner.setOnClickListener(this);
    }

    private void initView() {
//        item_list_sub_recycler = findViewById(R.id.item_list_sub_recycler);
        selectRashiSpinner = findViewById(R.id.selectRashiSpinner);
        back_btn = findViewById(R.id.back_btn);
        rashiImg = findViewById(R.id.rashiImg);
        rashiTxt = findViewById(R.id.rashiTxt);
        progress_lay = findViewById(R.id.progress_lay);
        notDetails = findViewById(R.id.notDetails);
        notDetails.setOnLongClickListener(view -> true);
//        item_list_sub_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                onBackPressed();
                break;
            case R.id.selectRashiSpinner:
                dialog.openZodiacSelector();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void clickedPosition(ZodiacModel zodiacModel) {
        getData(zodiacModel);
    }
}