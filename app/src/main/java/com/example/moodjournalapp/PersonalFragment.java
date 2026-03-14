package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PersonalFragment extends Fragment {

    public PersonalFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        applyTheme(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText nameInput = view.findViewById(R.id.editName);
        EditText usernameInput = view.findViewById(R.id.editUsername);
        EditText passwordInput = view.findViewById(R.id.editPassword);

        Button saveButton = view.findViewById(R.id.saveProfileButton);
        Button backButton = view.findViewById(R.id.backButton);

        SharedPreferences prefs =
                requireActivity().getSharedPreferences("UserData",0);

        // LOAD SAVED DATA
        String name = prefs.getString("name","");
        String username = prefs.getString("username","");
        String password = prefs.getString("password","");

        nameInput.setHint(name);
        usernameInput.setHint(username);
        passwordInput.setHint(password);

        // SAVE CHANGES
        saveButton.setOnClickListener(v -> {

            String newName = nameInput.getText().toString().trim();
            String newUsername = usernameInput.getText().toString().trim();
            String newPassword = passwordInput.getText().toString().trim();

            if(TextUtils.isEmpty(newName)){
                newName = name;
            }

            if(TextUtils.isEmpty(newUsername)){
                newUsername = username;
            }

            if(TextUtils.isEmpty(newPassword)){
                newPassword = password;
            }

            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("name", newName);
            editor.putString("username", newUsername);
            editor.putString("password", newPassword);

            editor.apply();

            // REFRESH HOME + PROFILE DRAWER
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment())
                    .commit();
        });

        // BACK BUTTON
        backButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );
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