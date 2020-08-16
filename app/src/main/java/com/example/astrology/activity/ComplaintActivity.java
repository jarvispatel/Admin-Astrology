package com.example.astrology.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.astrology.R;
import com.example.astrology.models.ComplaintModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class ComplaintActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout back_btn, add_ground;
    AdView adView;
    EditText quote_title, quote_description;
    CardView submit_quote_card, progress_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        initViews();
        setClicks();
    }

    private void setClicks() {
        back_btn.setOnClickListener(this);
        submit_quote_card.setOnClickListener(this);
    }

    private void initViews() {
        back_btn = findViewById(R.id.back_btn);
        add_ground = findViewById(R.id.add_ground);
        adView = findViewById(R.id.adView);
        quote_title = findViewById(R.id.quote_title);
        quote_description = findViewById(R.id.quote_description);
        submit_quote_card = findViewById(R.id.submit_quote_card);
        progress_lay = findViewById(R.id.progress_lay);

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
            case R.id.back_btn:
                onBackPressed();
                break;
            case R.id.submit_quote_card:
                sendComplaint();
                break;
        }
    }

    private void sendComplaint() {
        String title = quote_title.getText().toString();
        String details = quote_description.getText().toString();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "कृपया शिकायत का शीर्षक लिखें", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(details)) {
            Toast.makeText(this, "कृपया शिकायत का विवरण लिखें", Toast.LENGTH_SHORT).show();
        } else {
            progress_lay.setVisibility(View.VISIBLE);
            SimpleDateFormat aaa = new SimpleDateFormat("dd-MMMM-yyyy");
            ComplaintModel complaintModel = new ComplaintModel(title, details, aaa.format(new java.util.Date()));
            FirebaseDatabase.getInstance().getReference().child("complaint_list")
                    .child(FirebaseDatabase.getInstance().getReference().push().getKey())
                    .setValue(complaintModel)
                    .addOnCompleteListener(task -> {
                        progress_lay.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            quote_title.setText("");
                            quote_description.setText("");
                            Toast.makeText(this, "आपकी शिकायत सफलतापूर्वक दर्ज की गई है", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "क्षमा करें हम आपकी शिकायत दर्ज करने में असमर्थ हैं", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}