package com.example.astrology.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.adapter.JyotishAdapter;
import com.example.astrology.models.JyotishItemsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JyotishFragment extends Fragment {

    Context context;
    RecyclerView dailyRashifalRecy;
    CardView progress_lay;
    DatabaseReference databaseReference;
    ArrayList<JyotishItemsModel> jyotishItemsModelArrayList;

    public JyotishFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("home_jyotish_list");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jyotish, container, false);
        initViews(view);
        return view;
    }

    private void setAdapters(ArrayList<JyotishItemsModel> jyotishItemsModelArrayList) {
        progress_lay.setVisibility(View.GONE);
        dailyRashifalRecy.setLayoutManager(new LinearLayoutManager(context));
        dailyRashifalRecy.setAdapter(new JyotishAdapter(context, jyotishItemsModelArrayList));
    }

    private void initViews(View view) {
        dailyRashifalRecy = view.findViewById(R.id.dailyRashifalRecy);
        progress_lay = view.findViewById(R.id.progress_lay);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                jyotishItemsModelArrayList = new ArrayList<>();
                if (dataSnapshot.getChildrenCount() != 0) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        JyotishItemsModel jyotishItemsModel = snapshot.getValue(JyotishItemsModel.class);
                        jyotishItemsModel.setItem_key(snapshot.getKey());
                        jyotishItemsModelArrayList.add(jyotishItemsModel);
                    }
                }

                setAdapters(jyotishItemsModelArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}