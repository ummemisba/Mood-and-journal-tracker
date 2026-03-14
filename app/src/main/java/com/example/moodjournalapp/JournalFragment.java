package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class JournalFragment extends Fragment {

    EditText journalInput;
    Spinner moodDropdown;
    Button saveButton, clearButton, backButton;
    TextView tipText;

    public JournalFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        applyTheme(view);

        journalInput = view.findViewById(R.id.journalInput);
        moodDropdown = view.findViewById(R.id.moodDropdown);
        saveButton = view.findViewById(R.id.saveJournalButton);
        clearButton = view.findViewById(R.id.clearJournalButton);
        backButton = view.findViewById(R.id.backButton);
        tipText = view.findViewById(R.id.tipText);

        backButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );

        clearButton.setOnClickListener(v ->
                journalInput.setText("")
        );

        saveButton.setOnClickListener(v -> saveEntry());

        setupMoodDropdown();

        return view;
    }

    private void setupMoodDropdown(){

        String[] moods = {
                "😊 Happy",
                "🤩 Excited",
                "😐 Neutral",
                "😌 Relaxed",
                "😔 Sad",
                "😡 Angry",
                "😟 Anxious",
                "😴 Tired",
                "🥰 Grateful"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        moods);

        moodDropdown.setAdapter(adapter);
    }

    private void saveEntry(){

        String journal = journalInput.getText().toString().trim();
        String mood = moodDropdown.getSelectedItem().toString();

        if(TextUtils.isEmpty(journal)){
            journalInput.setError("Write something first ✍️");
            return;
        }

        String date = new SimpleDateFormat(
                "dd MMM yyyy  HH:mm",
                Locale.getDefault()).format(new Date());

        String entry =
                "🗓 " + date + "\n"
                        + "Mood: " + mood + "\n"
                        + journal + "\n\n";

        SharedPreferences prefs =
                requireActivity().getSharedPreferences("JournalData",0);

        String oldEntries = prefs.getString("entries","");

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("entries", entry + oldEntries);
        editor.apply();

        journalInput.setText("");

        tipText.setText(generateTip(mood));
    }

    private String generateTip(String mood){

        String[] tips;

        if(mood.contains("Happy")){
            tips = new String[]{
                    "Keep spreading your positive energy ✨",
                    "Celebrate the little wins today 🌸",
                    "Write what made today special",
                    "Share your happiness with someone",
                    "Enjoy the moment fully"
            };
        }
        else if(mood.contains("Sad")){
            tips = new String[]{
                    "It's okay to feel sad sometimes 💙",
                    "Try writing everything on your mind",
                    "Take a short walk outside",
                    "Talk to someone you trust",
                    "Be gentle with yourself today"
            };
        }
        else{
            tips = new String[]{
                    "Reflect on how your day went",
                    "Write one thing you're grateful for",
                    "Notice the small moments today",
                    "Take a deep breath and reset",
                    "Check in with your emotions"
            };
        }

        Random random = new Random();
        return tips[random.nextInt(tips.length)];
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