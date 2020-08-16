package com.example.astrology.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.astrology.R;
import com.example.astrology.models.ProfileModel;
import com.example.astrology.utils.Preference;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.astrology.utils.Constants.USER_NAME;

public class ProfileActivity extends AppCompatActivity {

    ImageView back;
    EditText username, phone_number;
    RelativeLayout save_btn, add_ground;
    AdView adView;
    CardView progress_lay;
    String android_id;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user_list").child(android_id);

        initView();
        setEvents();
    }

    private void setEvents() {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                add_ground.setVisibility(View.VISIBLE);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    return;
                }
                ProfileModel profileModel = dataSnapshot.getValue(ProfileModel.class);
                username.setText(profileModel.getUser_name());
                phone_number.setText(profileModel.getPhone_number());

                Preference.setString(USER_NAME, profileModel.getUser_name(), ProfileActivity.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back.setOnClickListener(view -> {
            finish();
        });

        save_btn.setOnClickListener(view -> {
            String phone_num = phone_number.getText().toString();
            String name = username.getText().toString();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "कृपया अपना पूरा नाम लिखें", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(phone_num)) {
                Toast.makeText(this, "कृपया अपना फोन नंबर दर्ज करें", Toast.LENGTH_SHORT).show();
                return;
            }

            sendProfile(phone_num, name);
        });
    }


    private void initView() {
        back = findViewById(R.id.back);
        phone_number = findViewById(R.id.phone_number);
        username = findViewById(R.id.username);
        save_btn = findViewById(R.id.save_btn);
        add_ground = findViewById(R.id.add_ground);
        adView = findViewById(R.id.adView);
        progress_lay = findViewById(R.id.progress_lay);
    }

    private void sendProfile(String phone_num, String name) {
        progress_lay.setVisibility(View.VISIBLE);
        ProfileModel profileModel = new ProfileModel(name, phone_num);
        databaseReference.setValue(profileModel)
                .addOnCompleteListener(task -> {
                    progress_lay.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "आपकी प्रोफ़ाइल सफलतापूर्वक अपडेट हो गई है", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "क्षमा करें हम आपकी प्रोफ़ाइल अपडेट करने में असमर्थ हैं", Toast.LENGTH_LONG).show();
                    }
                });
    }
}