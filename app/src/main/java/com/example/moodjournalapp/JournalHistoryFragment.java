package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class JournalHistoryFragment extends Fragment {

    LinearLayout journalContainer;

    public JournalHistoryFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_journal_history, container, false);

        applyTheme(view);

        journalContainer = view.findViewById(R.id.journalHistoryContainer);

        Button backButton = view.findViewById(R.id.backButton);

        backButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );

        loadHistory();

        return view;
    }

    private void loadHistory(){

        SharedPreferences prefs =
                requireActivity().getSharedPreferences("JournalData",0);

        String history = prefs.getString("entries","No journal entries yet 📓");

        TextView historyText = new TextView(getContext());
        historyText.setText(history);
        historyText.setTextSize(16);
        historyText.setPadding(10,20,10,20);

        journalContainer.addView(historyText);
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