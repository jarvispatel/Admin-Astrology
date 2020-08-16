package com.example.astrology.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astrology.R;
import com.example.astrology.adapter.HomeListAdapter;
import com.example.astrology.models.DayPanchangModel;
import com.example.astrology.models.DetailModel;
import com.example.astrology.models.HomeItemsModel;
import com.example.astrology.models.ZodiacModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.example.astrology.utils.Constants.dateFormat;

public class RashifalFragment extends Fragment {

    RecyclerView home_items_recycler;
    Context context;
    DatabaseReference databaseReference;
    ArrayList<HomeItemsModel> homeItemsModelArrayList;
    CardView progress_lay;
    TextView current_date_txt, current_day_txt, current_paksha_txt, current_rashtriya_txt, amant_label, purnimant_label, vikram_savant_amant, vikram_savant_purnimant, pramathi_amant, pramathi_purnimant, month_amant, month_purnimant;
//    ArrayList<ZodiacModel> zodiacList;

    public RashifalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("home_rashifal_list");
    }

    private void setAdapters(ArrayList<HomeItemsModel> homeItemsModel) {
        progress_lay.setVisibility(View.GONE);
        home_items_recycler.setLayoutManager(new GridLayoutManager(context, 3));
        home_items_recycler.setAdapter(new HomeListAdapter(context, homeItemsModel));
    }

    private void initViews(View view) {
        home_items_recycler = view.findViewById(R.id.home_items_recycler);
        progress_lay = view.findViewById(R.id.progress_lay);

        current_date_txt = view.findViewById(R.id.current_date_txt);
        current_day_txt = view.findViewById(R.id.current_day_txt);

        current_paksha_txt = view.findViewById(R.id.current_paksha_txt);
        current_rashtriya_txt = view.findViewById(R.id.current_rashtriya_txt);
        amant_label = view.findViewById(R.id.amant_label);
        purnimant_label = view.findViewById(R.id.purnimant_label);
        vikram_savant_amant = view.findViewById(R.id.vikram_savant_amant);
        vikram_savant_purnimant = view.findViewById(R.id.vikram_savant_purnimant);
        pramathi_amant = view.findViewById(R.id.pramathi_amant);
        pramathi_purnimant = view.findViewById(R.id.pramathi_purnimant);
        month_amant = view.findViewById(R.id.month_amant);
        month_purnimant = view.findViewById(R.id.month_purnimant);
    }

    private void setCurrentDateData() {
        SimpleDateFormat dateF = new SimpleDateFormat("dd MMMM yyyy", new Locale("hi", "IN"));
        current_date_txt.setText(dateF.format(new java.util.Date()));
        SimpleDateFormat dayF = new SimpleDateFormat("EEEE", new Locale("hi", "IN"));
        current_day_txt.setText(dayF.format(new java.util.Date()));

//        DatabaseReference panchangReference = FirebaseDatabase.getInstance().getReference().child("home_panchang_data").child(dateFormat.format(new java.util.Date()).trim());
        DatabaseReference panchangReference = FirebaseDatabase.getInstance().getReference().child("home_panchang_data").child("12-August-2020");
        panchangReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    return;
                }
                DayPanchangModel dayPanchangModel = dataSnapshot.getValue(DayPanchangModel.class);
                current_paksha_txt.setText(dayPanchangModel.getPaksha());
                current_rashtriya_txt.setText(dayPanchangModel.getRashtriya());
                amant_label.setText(dayPanchangModel.getAmant_label());
                purnimant_label.setText(dayPanchangModel.getPurnimant_label());
                vikram_savant_amant.setText(dayPanchangModel.getVikram_savant_amant());
                vikram_savant_purnimant.setText(dayPanchangModel.getVikram_savant_purnimant());
                pramathi_amant.setText(dayPanchangModel.getPramathi_amant());
                pramathi_purnimant.setText(dayPanchangModel.getPramathi_purnimant());
                month_amant.setText(dayPanchangModel.getMonth_amant());
                month_purnimant.setText(dayPanchangModel.getMonth_purnimant());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rashifal, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        setCurrentDateData();
    }

    private void getData() {
        progress_lay.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    return;
                }
                homeItemsModelArrayList = new ArrayList<>();
//                getZodiacList();
                if (dataSnapshot.getChildrenCount() != 0) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HomeItemsModel homeItemsModel = snapshot.getValue(HomeItemsModel.class);
                        homeItemsModel.setItem_key(snapshot.getKey());
                        homeItemsModelArrayList.add(homeItemsModel);

                        /*if (homeItemsModel.getItem_type().equalsIgnoreCase("list")) {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                                    .child("home_rashifal_sub_data")
                                    .child(snapshot.getKey())
                                    .child(homeItemsModel.getHome_item_label());
                            databaseReference.setValue(new DetailModel("<h2 style=text-align: center;><span style=color: rgb(198, 144, 144);>होली \uD83D\uDD25</span></h2><span style=color: rgb(41, 41, 41);>होली का उत्सव अपने साथ सकारात्मक ऊर्जा लेकर आता है और आसमान में बिखरे गुलाल की तरह ऊर्जा को चारों ओर बिखेर देता है। इस पर्व की ख़ास तैयारी में भी लोगों के अंदर बहुत अधिक उत्साह को देखा जा सकता है।</span><br style=><br style=><span style=color: rgb(112, 156, 153);>होली की तैयारी \uD83E\uDD73</span><br style=><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>\uD83D\uDCA5 होली की विशेष तैयारी में एक दिन से ज्यादा का समय लगता है। इस पर्व पर सबके घरों में अनेक पकवान बनाएं जाते हैं जिसमें गुजिया, दही भल्ले, गुलाब जामुन प्रमुख हैं लोग महिनों पहले से अपने घर के छतों पर विभिन्न तरह के पापड़ और चिप्स आदि को सुखाने में लग जाते हैं। मध्यमवर्गीय परिवार भी इस त्योहार पर अपने बच्चों के लिए अवश्य ही कपड़े खरीदता है।</span></blockquote><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);><br></span></blockquote><br style=><span style=color: rgb(112, 156, 153);>होली कैसे मनाई जाती है</span><br style=><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>होली पर सभी बहुत अधिक उत्साहित होते हैं। बड़े भी बच्चे बन जाते हैं हम उम्र का चेहरा रंगों से ऐसे रंगते हैं की पहचानना मुश्किल हो जाता है वहीं बड़ों को गुलाल लगा उनका आशिर्वाद लेते हैं। अमीर-गरीब, ऊँच- नीच का भेद भुलाकर सभी आनंद के साथ होली में झूमते नज़र आते हैं। झूमने का एक अन्य कारण भांग और ठंडाई भी है यह होली पर विशेष कर पीया जाता है। घर की महिलाएं सारे पकवान बना कर जहां दोपहर से होली खेलना प्रारंभ करती है वहीं बच्चे सुबह उठने के साथ ही तहलके (उत्साह) के साथ मैदान में आ जाते हैं।</span></blockquote><br style=><span style=color: rgb(112, 156, 153);>होली के एक दिन पहले होलिका दहन</span><br style=><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>होली के एक दिन पहले गावं व शहरों के खुले क्षेत्र में होलिका दहन की परंपरा निभाई जाती है। यह भगवान की असीम शक्ति का प्रमाण तथा बुराई पर अच्छाई की जीत का ज्ञान कराती है।</span></blockquote><br style=><span style=color: rgb(203, 184, 209);>निष्कर्ष</span><br style=><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>होली आनंद से भरा रंगों का त्योहार है यह भारत भूमि पर प्राचीन समय से मनाया जाता है। त्योहारों की ख़ास बात यह है की इसके मस्ती में लोग आपसी बैर तक भूल जाते हैं एवं होली त्योहारों में विशेष स्थान रखता है।</span></blockquote><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>पुराने समय में होली के अवसर पर जहां मंदिरों में कृष्ण और राम के भजन गूंजते थे, वहीं नगरों में लोगों द्वारा ढोलक मंजिरों के ताल पर लोकगीत गाए जाते थे। पर बदलते समय के साथ इस त्योहार का स्वरूप भी बदलता नज़र आ रहा है।</span></blockquote><br style=><span style=color: rgb(135, 207, 170);>कार्यस्थलों तथा विभिन्न संस्थानों पर होली</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर सभी संस्थान, संस्था व कार्यस्थल में छुट्टी दी जाती है पर छुट्टी से पहले स्कूलों में बच्चे तथा कार्यस्थल पर सभी कार्मचारी एक दूसरे को गुलाल लगाकर होली की शुभकामनाएं देते हैं।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>होली की संध्या में मित्रों से मेल-मिलाप</span><br style=><br style=><span style=color: rgb(41, 41, 41);>दिन भर रंगों से खेलने व नाच गाने के पश्चात सभी संध्या में नये वस्त्र पहनते हैं और अपने पड़ोसी व मित्रों के घरों में उनसे मिलने और होली की शुभकामना देने जाते हैं।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>होली की हलचल का सभी टीवी चैनलों पर प्रसारण</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर सभी टीवी चैनलों में होली के गीत, अनेक विशेष कार्यक्रम तथा न्यूज चैनलों के माध्यम से विभिन्न स्थानों की होली प्रसारित की जाती है।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>बाजारों की रौनक में, होली की परंपरागत रीति कहीं खो न जाए</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर सभी छोटे-बड़े दुकानदार अपने दुकानों के आगे स्टैंड आदि लगा कर विभिन्न प्रकार के चटकीले रंग, गुलाल, पिचकारी व होली के अन्य आकर्षक सामग्री जैसे रंग बिरंगे विग (wig) से अपने स्टॉल को भर देते हैं। राशन तथा कपड़ों की दुकानों पर खरीदारी के लिए विशेष भीड़ देखने को मिलती है। पर समय बितने के साथ ज्यादातर लोग अब स्वयं से कोई पकवान नहीं बनाते वे हर प्रकार की मिठाइयां बाजार से ही खरीद लेते हैं। इससे त्योहार की धूम का बाजारीकरण में खो जाने का भय है।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>समय के साथ होली का बदलता स्वरूप</span><br style=><br style=><span style=color: rgb(41, 41, 41);>परंपरागत विधि से आज इस त्योहार का स्वरूप बहुत अधिक बदल गया है। पहले लोग होली की मस्ती में अपनी मर्यादा को नहीं भूलते थे। लेकिन आज के समय में त्योहार के नाम पर लोग अनैतिक कार्य कर रहें हैं। जैसे एक-दूसरे के कपड़े-फाड़ देना, जबरदस्ती किसी पर रंग डालना आदि।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>होली पर हुड़दंग</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर वह भी रंगों से भीग जाते हैं जो अपने घरों से नहीं निकलना चाहते और जैसे की भिगोने वालों का तकिया कलाम बन चुका होता है “बुरा ना मानो होली है”। कुछ लोग त्योहार का गलत फायदा उठा कर बहुत अधिक मादक पदार्थों का सेवन करते हैं और सड़क पर चल रहीं महिलाओं को परेशान करते हैं। यह सरासर गलत व्यवहार है।</span><br style=><br style=><span style=color: rgb(166, 135, 200);>निष्कर्ष</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर सभी मस्ती में डूबे नज़र आते हैं। जहां सामान्य व्यक्ति अनेकों प्रकार के स्वादिष्ट भोजन तथा ठंडाई का सेवन करते हैं। वहीं मनचलों को नशे में धुत्त होकर अपनी मनमानी करने का एक अवसर प्राप्त हो जाता है। होली रंगों का त्योहार है इसे प्रेम पूर्वक खेलना चाहिए।</span><br>"));
                        } else {
                            for (int i = 0; i < zodiacList.size(); i++) {
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                                        .child("home_rashifal_sub_data")
                                        .child(snapshot.getKey())
                                        .child(getString(zodiacList.get(i).getZodiacTxt()));
                                databaseReference.setValue(new DetailModel("<h2 style=text-align: center;><span style=color: rgb(198, 144, 144);>होली \uD83D\uDD25</span></h2><span style=color: rgb(41, 41, 41);>होली का उत्सव अपने साथ सकारात्मक ऊर्जा लेकर आता है और आसमान में बिखरे गुलाल की तरह ऊर्जा को चारों ओर बिखेर देता है। इस पर्व की ख़ास तैयारी में भी लोगों के अंदर बहुत अधिक उत्साह को देखा जा सकता है।</span><br style=><br style=><span style=color: rgb(112, 156, 153);>होली की तैयारी \uD83E\uDD73</span><br style=><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>\uD83D\uDCA5 होली की विशेष तैयारी में एक दिन से ज्यादा का समय लगता है। इस पर्व पर सबके घरों में अनेक पकवान बनाएं जाते हैं जिसमें गुजिया, दही भल्ले, गुलाब जामुन प्रमुख हैं लोग महिनों पहले से अपने घर के छतों पर विभिन्न तरह के पापड़ और चिप्स आदि को सुखाने में लग जाते हैं। मध्यमवर्गीय परिवार भी इस त्योहार पर अपने बच्चों के लिए अवश्य ही कपड़े खरीदता है।</span></blockquote><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);><br></span></blockquote><br style=><span style=color: rgb(112, 156, 153);>होली कैसे मनाई जाती है</span><br style=><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>होली पर सभी बहुत अधिक उत्साहित होते हैं। बड़े भी बच्चे बन जाते हैं हम उम्र का चेहरा रंगों से ऐसे रंगते हैं की पहचानना मुश्किल हो जाता है वहीं बड़ों को गुलाल लगा उनका आशिर्वाद लेते हैं। अमीर-गरीब, ऊँच- नीच का भेद भुलाकर सभी आनंद के साथ होली में झूमते नज़र आते हैं। झूमने का एक अन्य कारण भांग और ठंडाई भी है यह होली पर विशेष कर पीया जाता है। घर की महिलाएं सारे पकवान बना कर जहां दोपहर से होली खेलना प्रारंभ करती है वहीं बच्चे सुबह उठने के साथ ही तहलके (उत्साह) के साथ मैदान में आ जाते हैं।</span></blockquote><br style=><span style=color: rgb(112, 156, 153);>होली के एक दिन पहले होलिका दहन</span><br style=><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>होली के एक दिन पहले गावं व शहरों के खुले क्षेत्र में होलिका दहन की परंपरा निभाई जाती है। यह भगवान की असीम शक्ति का प्रमाण तथा बुराई पर अच्छाई की जीत का ज्ञान कराती है।</span></blockquote><br style=><span style=color: rgb(203, 184, 209);>निष्कर्ष</span><br style=><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>होली आनंद से भरा रंगों का त्योहार है यह भारत भूमि पर प्राचीन समय से मनाया जाता है। त्योहारों की ख़ास बात यह है की इसके मस्ती में लोग आपसी बैर तक भूल जाते हैं एवं होली त्योहारों में विशेष स्थान रखता है।</span></blockquote><br style=><blockquote style=margin: 0 0 0 40px; border: none; padding: 0px;><span style=color: rgb(41, 41, 41);>पुराने समय में होली के अवसर पर जहां मंदिरों में कृष्ण और राम के भजन गूंजते थे, वहीं नगरों में लोगों द्वारा ढोलक मंजिरों के ताल पर लोकगीत गाए जाते थे। पर बदलते समय के साथ इस त्योहार का स्वरूप भी बदलता नज़र आ रहा है।</span></blockquote><br style=><span style=color: rgb(135, 207, 170);>कार्यस्थलों तथा विभिन्न संस्थानों पर होली</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर सभी संस्थान, संस्था व कार्यस्थल में छुट्टी दी जाती है पर छुट्टी से पहले स्कूलों में बच्चे तथा कार्यस्थल पर सभी कार्मचारी एक दूसरे को गुलाल लगाकर होली की शुभकामनाएं देते हैं।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>होली की संध्या में मित्रों से मेल-मिलाप</span><br style=><br style=><span style=color: rgb(41, 41, 41);>दिन भर रंगों से खेलने व नाच गाने के पश्चात सभी संध्या में नये वस्त्र पहनते हैं और अपने पड़ोसी व मित्रों के घरों में उनसे मिलने और होली की शुभकामना देने जाते हैं।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>होली की हलचल का सभी टीवी चैनलों पर प्रसारण</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर सभी टीवी चैनलों में होली के गीत, अनेक विशेष कार्यक्रम तथा न्यूज चैनलों के माध्यम से विभिन्न स्थानों की होली प्रसारित की जाती है।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>बाजारों की रौनक में, होली की परंपरागत रीति कहीं खो न जाए</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर सभी छोटे-बड़े दुकानदार अपने दुकानों के आगे स्टैंड आदि लगा कर विभिन्न प्रकार के चटकीले रंग, गुलाल, पिचकारी व होली के अन्य आकर्षक सामग्री जैसे रंग बिरंगे विग (wig) से अपने स्टॉल को भर देते हैं। राशन तथा कपड़ों की दुकानों पर खरीदारी के लिए विशेष भीड़ देखने को मिलती है। पर समय बितने के साथ ज्यादातर लोग अब स्वयं से कोई पकवान नहीं बनाते वे हर प्रकार की मिठाइयां बाजार से ही खरीद लेते हैं। इससे त्योहार की धूम का बाजारीकरण में खो जाने का भय है।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>समय के साथ होली का बदलता स्वरूप</span><br style=><br style=><span style=color: rgb(41, 41, 41);>परंपरागत विधि से आज इस त्योहार का स्वरूप बहुत अधिक बदल गया है। पहले लोग होली की मस्ती में अपनी मर्यादा को नहीं भूलते थे। लेकिन आज के समय में त्योहार के नाम पर लोग अनैतिक कार्य कर रहें हैं। जैसे एक-दूसरे के कपड़े-फाड़ देना, जबरदस्ती किसी पर रंग डालना आदि।</span><br style=><br style=><span style=color: rgb(135, 207, 170);>होली पर हुड़दंग</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर वह भी रंगों से भीग जाते हैं जो अपने घरों से नहीं निकलना चाहते और जैसे की भिगोने वालों का तकिया कलाम बन चुका होता है “बुरा ना मानो होली है”। कुछ लोग त्योहार का गलत फायदा उठा कर बहुत अधिक मादक पदार्थों का सेवन करते हैं और सड़क पर चल रहीं महिलाओं को परेशान करते हैं। यह सरासर गलत व्यवहार है।</span><br style=><br style=><span style=color: rgb(166, 135, 200);>निष्कर्ष</span><br style=><br style=><span style=color: rgb(41, 41, 41);>होली पर सभी मस्ती में डूबे नज़र आते हैं। जहां सामान्य व्यक्ति अनेकों प्रकार के स्वादिष्ट भोजन तथा ठंडाई का सेवन करते हैं। वहीं मनचलों को नशे में धुत्त होकर अपनी मनमानी करने का एक अवसर प्राप्त हो जाता है। होली रंगों का त्योहार है इसे प्रेम पूर्वक खेलना चाहिए।</span><br>"));
                            }
                        }*/
                    }
                }

                setAdapters(homeItemsModelArrayList);
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

    /*public ArrayList<ZodiacModel> getZodiacList() {
        zodiacList = new ArrayList<>();
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
    }*/

}