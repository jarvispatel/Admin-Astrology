package com.example.astrology.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.astrology.R;
import com.example.astrology.adapter.HomeTabAdapter;
import com.example.astrology.fragment.JyotishFragment;
import com.example.astrology.fragment.RashifalFragment;
import com.example.astrology.utils.Preference;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.astrology.utils.Constants.LAST_SHOW_GREETING;
import static com.example.astrology.utils.Constants.USER_NAME;
import static com.example.astrology.utils.Constants.dateFormat;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    ImageView drawer_btn, tabTwoImg, tabOneImg, setting_btn, notificationImg;
    TextView tabTwoTxt, tabOneTxt;
    RelativeLayout tabOneRel, tabTwoRel;
    LinearLayout tabTwo, tabOne, notification_nav, setting_nav, share_nav, complaint_nav, facebook_nav, rating_nav, privacy_nav, html_editor, profile_nav;
    ViewPager2 tabViewPager;
    DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {
        });

        initViews();
        setClicks();
        setAdapters();
    }

    private void setAdapters() {
        //for home tabs
        HomeTabAdapter homeTabAdapter = new HomeTabAdapter(this);
        homeTabAdapter.setFragments(getFragments());
        tabViewPager.setAdapter(homeTabAdapter);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RashifalFragment());
        fragments.add(new JyotishFragment());
        return fragments;
    }

    private void setClicks() {
        drawer_btn.setOnClickListener(this);
        tabOne.setOnClickListener(this);
        tabTwo.setOnClickListener(this);
        setting_btn.setOnClickListener(this);
        notificationImg.setOnClickListener(this);
        notification_nav.setOnClickListener(this);
        setting_nav.setOnClickListener(this);
        share_nav.setOnClickListener(this);
        complaint_nav.setOnClickListener(this);
        facebook_nav.setOnClickListener(this);
        rating_nav.setOnClickListener(this);
        privacy_nav.setOnClickListener(this);
        html_editor.setOnClickListener(this);
        profile_nav.setOnClickListener(this);
    }

    private void initViews() {
        drawer_btn = findViewById(R.id.drawer_btn);
        setting_btn = findViewById(R.id.setting_btn);
        drawer_layout = findViewById(R.id.drawer_layout);
        tabViewPager = findViewById(R.id.tabViewPager);
        tabViewPager.setUserInputEnabled(false);
        tabTwoImg = findViewById(R.id.tabTwoImg);
        tabOneImg = findViewById(R.id.tabOneImg);
        tabTwoTxt = findViewById(R.id.tabTwoTxt);
        tabOneTxt = findViewById(R.id.tabOneTxt);
        tabOneRel = findViewById(R.id.tabOneRel);
        tabTwoRel = findViewById(R.id.tabTwoRel);
        complaint_nav = findViewById(R.id.complaint_nav);
        tabTwo = findViewById(R.id.tabTwo);
        tabOne = findViewById(R.id.tabOne);
        notificationImg = findViewById(R.id.notificationImg);
        notification_nav = findViewById(R.id.notification_nav);
        setting_nav = findViewById(R.id.setting_nav);
        share_nav = findViewById(R.id.share_nav);
        facebook_nav = findViewById(R.id.facebook_nav);
        rating_nav = findViewById(R.id.rating_nav);
        privacy_nav = findViewById(R.id.privacy_nav);
        html_editor = findViewById(R.id.html_editor);
        profile_nav = findViewById(R.id.profile_nav);

        if (Preference.contains(LAST_SHOW_GREETING, this)) {
            if (!Preference.getString(LAST_SHOW_GREETING, "", this).equalsIgnoreCase(dateFormat.format(new java.util.Date()).trim())) {
                show_course_dialog();
            }
        } else {
            show_course_dialog();
        }

        selectTab(0);
    }

    private void selectTab(int tabPosition) {
        tabOneRel.setBackgroundColor(Color.TRANSPARENT);
        tabTwoRel.setBackgroundColor(Color.TRANSPARENT);
        tabOneTxt.setTextColor(getResources().getColor(R.color.background_new));
        tabTwoTxt.setTextColor(getResources().getColor(R.color.background_new));
        tabOneImg.setColorFilter(ContextCompat.getColor(this, R.color.background_new));
        tabTwoImg.setColorFilter(ContextCompat.getColor(this, R.color.background_new));

        switch (tabPosition) {
            case 0:
                tabOneRel.setBackgroundColor(getResources().getColor(R.color.darkBlack));
                tabOneTxt.setTextColor(getResources().getColor(R.color.darkBlack));
                tabOneImg.setColorFilter(ContextCompat.getColor(this, R.color.darkBlack));
                tabViewPager.setCurrentItem(0, true);
                break;
            case 1:
                tabTwoRel.setBackgroundColor(getResources().getColor(R.color.darkBlack));
                tabTwoTxt.setTextColor(getResources().getColor(R.color.darkBlack));
                tabTwoImg.setColorFilter(ContextCompat.getColor(this, R.color.darkBlack));
                tabViewPager.setCurrentItem(1, true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawer_btn:
                if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.tabTwo:
                selectTab(1);
                break;
            case R.id.tabOne:
                selectTab(0);
                break;
            case R.id.setting_btn:
            case R.id.setting_nav:
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(this, Settings.class));
                break;
            case R.id.notificationImg:
            case R.id.notification_nav:
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.share_nav:
                drawer_layout.closeDrawer(Gravity.LEFT);
                Share_data();
                break;
            case R.id.complaint_nav:
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(this, ComplaintActivity.class));
                break;
            case R.id.facebook_nav:
                drawer_layout.closeDrawer(Gravity.LEFT);
                openURL();
                break;
            case R.id.rating_nav:
                drawer_layout.closeDrawer(Gravity.LEFT);
                ratingPage();
                break;
            case R.id.privacy_nav:
                drawer_layout.closeDrawer(Gravity.LEFT);
                openURL();
                break;
            case R.id.html_editor:
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.profile_nav:
                drawer_layout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
    }

    private void openURL() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }

    private void ratingPage() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.quote_app.quoteapp")));
    }

    public void Share_data() {
        String shareBody = "Download our app using this link https://play.google.com/store/apps/details?id=com.quote_app.quoteapp";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share Vie"));
    }

    public void show_course_dialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.greeting_dialog, null);
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);

        ImageView btn_close = view.findViewById(R.id.btn_close);
        TextView time_txt = view.findViewById(R.id.time_txt);
        TextView user_name_txt = view.findViewById(R.id.user_name_txt);
        user_name_txt.setText(Preference.getString(USER_NAME, "", this));
        btn_close.setOnClickListener(view1 -> dialog.dismiss());

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay < 12) {
            time_txt.setText(getText(R.string.morning));
        } else if (timeOfDay < 16) {
            time_txt.setText(getText(R.string.noon));
        } else if (timeOfDay < 21) {
            time_txt.setText(getText(R.string.evening));
        } else if (timeOfDay < 24) {
            time_txt.setText(getText(R.string.night));
        }

        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        Preference.setString(LAST_SHOW_GREETING, dateFormat.format(new java.util.Date()).trim(), this);
    }
}