package com.example.moodjournalapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class ThemeHelper {

    public static void applyTheme(Context context, View view) {

        SharedPreferences prefs =
                context.getSharedPreferences("UserData", Context.MODE_PRIVATE);

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