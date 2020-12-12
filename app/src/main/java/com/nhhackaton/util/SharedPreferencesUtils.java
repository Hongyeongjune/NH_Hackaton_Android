package com.nhhackaton.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.nhhackaton.data.SignIn.SignIn;

public class SharedPreferencesUtils {

    public static void writeMemberToInformation(Context context, SignIn signIn) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("NH_HACKATON", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("MEMBER_EMAIL", signIn.getEmail());
        editor.apply();
    }

    public static String readMemberFromEmail(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("NH_HACKATON", Context.MODE_PRIVATE);

        return sharedPreferences.getString("MEMBER_EMAIL", null);

    }

    public static void writeAutoSignInTo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NH_HACKATON", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("MEMBER_AUTO_SIGN_IN", true);
        editor.apply();
    }

    public static boolean readAutoSignInFromToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NH_HACKATON", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("MEMBER_AUTO_SIGN_IN", false);
    }
}
