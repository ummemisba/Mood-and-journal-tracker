package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class UnlockFragment extends Fragment {

    public UnlockFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_unlock, container, false);

        EditText passwordInput = view.findViewById(R.id.passwordInput);
        Button unlockButton = view.findViewById(R.id.unlockButton);
        Button signinButton = view.findViewById(R.id.signinButton);

        // Unlock existing account
        unlockButton.setOnClickListener(v -> {

            String enteredPassword = passwordInput.getText().toString().trim();

            if (TextUtils.isEmpty(enteredPassword)) {
                passwordInput.setError("Enter password");
                return;
            }

            SharedPreferences prefs =
                    requireActivity().getSharedPreferences("UserData", 0);

            String savedPassword = prefs.getString("password", "");

            if (enteredPassword.equals(savedPassword)) {

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new HomeFragment())
                        .commit();

            } else {

                passwordInput.setError("Wrong password");
            }

        });

        // Create new account
        signinButton.setOnClickListener(v -> {

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new CreateAccountFragment())
                    .addToBackStack(null)
                    .commit();

        });

        return view;
    }
}