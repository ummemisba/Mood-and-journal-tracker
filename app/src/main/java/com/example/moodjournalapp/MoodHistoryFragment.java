package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoodHistoryFragment extends Fragment {

    ProgressBar happyBar, neutralBar, sadBar;

    public MoodHistoryFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mood_history, container, false);

        applyTheme(view);

        Button backButton = view.findViewById(R.id.backButton);

        backButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );

        happyBar = view.findViewById(R.id.happyBar);
        neutralBar = view.findViewById(R.id.neutralBar);
        sadBar = view.findViewById(R.id.sadBar);

        loadStatistics();

        return view;
    }

    private void loadStatistics(){

        SharedPreferences prefs =
                requireActivity().getSharedPreferences("MoodData", 0);

        int happy = prefs.getInt("happy",0);
        int neutral = prefs.getInt("neutral",0);
        int sad = prefs.getInt("sad",0);

        int total = happy + neutral + sad;

        if(total == 0) return;

        int happyPercent = (happy * 100) / total;
        int neutralPercent = (neutral * 100) / total;
        int sadPercent = (sad * 100) / total;

        happyBar.setProgress(happyPercent);
        neutralBar.setProgress(neutralPercent);
        sadBar.setProgress(sadPercent);
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