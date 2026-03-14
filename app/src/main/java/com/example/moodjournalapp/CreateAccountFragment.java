package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreateAccountFragment extends Fragment {

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText nameInput = view.findViewById(R.id.nameInput);
        EditText usernameInput = view.findViewById(R.id.usernameInput);
        EditText passwordInput = view.findViewById(R.id.passwordInput);
        EditText confirmPasswordInput = view.findViewById(R.id.confirmPasswordInput);

        RadioGroup avatarGroup = view.findViewById(R.id.avatarGroup);

        Button continueButton = view.findViewById(R.id.continueButton);

        continueButton.setOnClickListener(v -> {

            String name = nameInput.getText().toString().trim();
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            int selectedAvatarId = avatarGroup.getCheckedRadioButtonId();

            if (TextUtils.isEmpty(name)) {
                nameInput.setError("Enter your name");
                return;
            }

            if (TextUtils.isEmpty(username)) {
                usernameInput.setError("Enter username");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                passwordInput.setError("Enter password");
                return;
            }

            if (!password.equals(confirmPassword)) {
                confirmPasswordInput.setError("Passwords do not match");
                return;
            }

            if (selectedAvatarId == -1) {
                return;
            }

            RadioButton selectedAvatar = view.findViewById(selectedAvatarId);
            String avatar = selectedAvatar.getText().toString();

            SharedPreferences prefs =
                    requireActivity().getSharedPreferences("UserData", 0);

            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("name", name);
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("avatar", avatar);
            editor.putBoolean("accountCreated", true);

            editor.apply();

            // Restart activity so drawer avatar updates
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment())
                    .commit();
        });
    }
}