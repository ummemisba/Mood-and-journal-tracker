package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MoodFragment extends Fragment {

    TextView selectedMoodText, moodTime, tipTitle, tipText;
    TextView recent1, recent2, recent3;

    Button moodHappy, moodExcited, moodNeutral, moodRelaxed;
    Button moodSad, moodAngry, moodAnxious, moodTired, moodGrateful;
    Button backButton;

    public MoodFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mood, container, false);

        applyTheme(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectedMoodText = view.findViewById(R.id.selectedMoodText);
        moodTime = view.findViewById(R.id.moodTime);
        tipTitle = view.findViewById(R.id.tipTitle);
        tipText = view.findViewById(R.id.tipText);

        recent1 = view.findViewById(R.id.recent1);
        recent2 = view.findViewById(R.id.recent2);
        recent3 = view.findViewById(R.id.recent3);

        moodHappy = view.findViewById(R.id.moodHappy);
        moodExcited = view.findViewById(R.id.moodExcited);
        moodNeutral = view.findViewById(R.id.moodNeutral);
        moodRelaxed = view.findViewById(R.id.moodRelaxed);
        moodSad = view.findViewById(R.id.moodSad);
        moodAngry = view.findViewById(R.id.moodAngry);
        moodAnxious = view.findViewById(R.id.moodAnxious);
        moodTired = view.findViewById(R.id.moodTired);
        moodGrateful = view.findViewById(R.id.moodGrateful);

        backButton = view.findViewById(R.id.backButton);

        backButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );

        moodHappy.setOnClickListener(v -> setMood("😊 Happy"));
        moodExcited.setOnClickListener(v -> setMood("🤩 Excited"));
        moodNeutral.setOnClickListener(v -> setMood("😐 Neutral"));
        moodRelaxed.setOnClickListener(v -> setMood("😌 Relaxed"));
        moodSad.setOnClickListener(v -> setMood("😔 Sad"));
        moodAngry.setOnClickListener(v -> setMood("😡 Angry"));
        moodAnxious.setOnClickListener(v -> setMood("😟 Anxious"));
        moodTired.setOnClickListener(v -> setMood("😴 Tired"));
        moodGrateful.setOnClickListener(v -> setMood("🥰 Grateful"));
    }

    private void setMood(String mood) {

        String[] tips;

        if (mood.contains("Happy")) {

            tips = new String[]{
                    "Keep spreading your positive energy!",
                    "Celebrate the little wins today.",
                    "Write what made you happy today.",
                    "Share your happiness with someone.",
                    "Enjoy the moment fully."
            };

        } else if (mood.contains("Sad")) {

            tips = new String[]{
                    "It's okay to feel sad.",
                    "Write your thoughts in your journal.",
                    "Take a walk outside.",
                    "Talk to someone you trust.",
                    "Give yourself compassion."
            };

        } else {

            tips = new String[]{
                    "Write something you're grateful for.",
                    "Appreciate the small blessings today.",
                    "Share gratitude with someone.",
                    "Reflect on positive moments.",
                    "Let gratitude guide your day."
            };
        }

        Random random = new Random();
        String randomTip = tips[random.nextInt(tips.length)];

        selectedMoodText.setText(mood);

        String time = new SimpleDateFormat(
                "EEE, dd MMM yyyy  HH:mm",
                Locale.getDefault()).format(new Date());

        moodTime.setText(time);

        tipTitle.setText("Tip for " + mood);
        tipText.setText(randomTip);

        updateRecent(mood);
    }

    private void updateRecent(String mood) {

        recent3.setText(recent2.getText());
        recent2.setText(recent1.getText());
        recent1.setText(mood);
    }

    private void applyTheme(View view){

        SharedPreferences prefs =
                requireActivity().getSharedPreferences("UserData",0);

        String themeColor = prefs.getString("themeColor","#FDE2FF");

        GradientDrawable gradient =
                new GradientDrawable(
                        GradientDrawable.Orientation.TL_BR,
                        new int[]{
                                Color.parseColor(themeColor),
                                Color.WHITE
                        });

        view.setBackground(gradient);
    }
}