package com.example.moodjournalapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);

        // DARK MODE CHECK
        boolean darkMode = prefs.getBoolean("darkMode", false);

        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        // APPLY THEME
        applyThemeColor();

        boolean accountExists = prefs.getBoolean("accountCreated", false);

        // PROFILE INFO
        String name = prefs.getString("name", "User");
        String avatar = prefs.getString("avatar", "🙂");

        TextView profileName = findViewById(R.id.profileName);
        TextView profileAvatar = findViewById(R.id.profileAvatar);

        if (profileName != null) profileName.setText(name);
        if (profileAvatar != null) profileAvatar.setText(avatar);

        // MENU ITEMS
        TextView menuPersonal = findViewById(R.id.menuPersonal);
        TextView menuTheme = findViewById(R.id.menuTheme);
        TextView menuDarkMode = findViewById(R.id.menuDarkMode);
        TextView menuPrivacy = findViewById(R.id.menuPrivacy);
        TextView menuTerms = findViewById(R.id.menuTerms);
        TextView menuHelp = findViewById(R.id.menuHelp);
        TextView menuAbout = findViewById(R.id.menuAbout);
        TextView menuLogout = findViewById(R.id.menuLogout);
        TextView menuDelete = findViewById(R.id.menuDelete);

        // PERSONAL PAGE
        menuPersonal.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new PersonalFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // THEME PAGE
        menuTheme.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new ThemeFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // PRIVACY PAGE
        menuPrivacy.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new PrivacyFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // TERMS PAGE
        menuTerms.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new TermsFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // HELP PAGE
        menuHelp.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new HelpFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // ABOUT PAGE
        menuAbout.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new AboutFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // DARK MODE
        menuDarkMode.setOnClickListener(v -> {

            boolean enabled = prefs.getBoolean("darkMode", false);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("darkMode", !enabled);
            editor.apply();

            recreate();
        });

        // LOGOUT
        menuLogout.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new UnlockFragment())
                    .commit();
        });

        // DELETE ACCOUNT
        menuDelete.setOnClickListener(v -> {

            new android.app.AlertDialog.Builder(this)
                    .setTitle("Delete Account")
                    .setMessage("Are you sure you want to delete your account?")
                    .setPositiveButton("Delete", (dialog, which) -> {

                        SharedPreferences.Editor editor = prefs.edit();
                        editor.clear();
                        editor.apply();

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, new CreateAccountFragment())
                                .commit();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        // START SCREEN
        if (savedInstanceState == null) {

            if (accountExists) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new UnlockFragment())
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new CreateAccountFragment())
                        .commit();
            }
        }
    }

    // THEME FUNCTION (must be outside onCreate)
    private void applyThemeColor() {

        SharedPreferences prefs =
                getSharedPreferences("UserData", MODE_PRIVATE);

        String themeColor = prefs.getString("themeColor", "#FDE2FF");

        View rootView = findViewById(android.R.id.content);

        GradientDrawable gradient =
                new GradientDrawable(
                        GradientDrawable.Orientation.TL_BR,
                        new int[]{
                                Color.parseColor(themeColor),
                                Color.WHITE
                        });

        rootView.setBackground(gradient);
    }
}