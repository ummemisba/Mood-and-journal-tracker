package com.example.moodjournalapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class WelcomeFragment extends Fragment {

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tagline = view.findViewById(R.id.tagline);
        Button startButton = view.findViewById(R.id.getStartedButton);

        String[] romanticLines = {
                "Let’s romanticize your emotional growth 💭",
                "Main character energy activated ✨",
                "Your feelings deserve a spotlight 🎭",
                "Healing looks good on you 💫",
                "Track it. Feel it. Own it. 🌸",
                "Soft life. Real emotions. 🫶",
                "One mood at a time 🌈"
        };

        Random random = new Random();
        int randomIndex = random.nextInt(romanticLines.length);
        tagline.setText(romanticLines[randomIndex]);

        // BUTTON NAVIGATION
        startButton.setOnClickListener(v -> {

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new CreateAccountFragment())
                    .addToBackStack(null)
                    .commit();

        });
    }
}