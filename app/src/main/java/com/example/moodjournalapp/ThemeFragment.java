package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThemeFragment extends Fragment {

    public ThemeFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_theme, container, false);

        Button purple = view.findViewById(R.id.themePurple);
        Button blue = view.findViewById(R.id.themeBlue);
        Button green = view.findViewById(R.id.themeGreen);
        Button pink = view.findViewById(R.id.themePink);

        purple.setOnClickListener(v -> saveTheme("#CBB6FF"));
        blue.setOnClickListener(v -> saveTheme("#64B5F6"));
        green.setOnClickListener(v -> saveTheme("#81C784"));
        pink.setOnClickListener(v -> saveTheme("#F48FB1"));

        return view;
    }

    private void saveTheme(String color) {

        SharedPreferences prefs =
                requireActivity().getSharedPreferences("UserData", 0);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("themeColor", color);
        editor.apply();

        requireActivity().recreate();
    }
}