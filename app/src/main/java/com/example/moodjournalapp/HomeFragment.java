package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Apply theme color
        applyTheme(view);

        // Views
        TextView greeting = view.findViewById(R.id.greetingText);
        TextView avatarView = view.findViewById(R.id.avatarView);
        ImageButton menuButton = view.findViewById(R.id.menuButton);

        Button journalButton = view.findViewById(R.id.journalButton);
        Button moodButton = view.findViewById(R.id.moodButton);
        Button historyButton = view.findViewById(R.id.historyButton);
        Button journalHistoryButton = view.findViewById(R.id.journalHistoryButton);

        // SharedPreferences
        SharedPreferences prefs =
                requireActivity().getSharedPreferences("UserData", 0);

        String name = prefs.getString("name", "there");
        String avatar = prefs.getString("avatar", "🙂");

        // Greeting
        avatarView.setText(avatar);
        greeting.setText("Hi " + name + " 👋");

        // Open Drawer
        menuButton.setOnClickListener(v -> {

            DrawerLayout drawer =
                    requireActivity().findViewById(R.id.drawerLayout);

            if (drawer != null) {
                drawer.openDrawer(Gravity.START);
            }
        });

        // Write Journal
        journalButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new JournalFragment())
                        .addToBackStack(null)
                        .commit()
        );

        // Track Mood
        moodButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new MoodFragment())
                        .addToBackStack(null)
                        .commit()
        );

        // Mood History
        historyButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new MoodHistoryFragment())
                        .addToBackStack(null)
                        .commit()
        );

        // Journal History
        journalHistoryButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new JournalHistoryFragment())
                        .addToBackStack(null)
                        .commit()
        );
    }

    // Theme function
    private void applyTheme(View view) {

        SharedPreferences prefs =
                requireActivity().getSharedPreferences("UserData", 0);

        String themeColor = prefs.getString("themeColor", "#FDE2FF");

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