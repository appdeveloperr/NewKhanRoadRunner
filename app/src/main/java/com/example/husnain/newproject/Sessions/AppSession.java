package com.example.husnain.newproject.Sessions;

import android.content.SharedPreferences;

import com.example.husnain.newproject.Utils.Constants;


public class AppSession
{
    public static final String BLANK_STRING_KEY = "blank_string_key";
    private static final String SHARED_PREFERENCE_NAME = "focial-session";
    public static final String WRONG_PAIR = "Key-Value pair cannot be blank or null";
    
    public static String get(final String s) {
        return Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).getString(s, "blank_string_key");
    }
    
    public static String get(final String s, final String s2) {
        return Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).getString(s, s2);
    }
    
    public static Boolean getBoolean(final String s) {
        return Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).getBoolean(s, false);
    }
    
    public static int getInt(final String s) {
        return Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).getInt(s, 0);
    }
    
    public static long getLong(final String s) {
        return Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).getLong(s, 0L);
    }
    
    public static boolean put(final String s, final int n) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException("Key-Value pair cannot be blank or null");
        }
        final SharedPreferences.Editor edit = Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).edit();
        edit.putInt(s, n);
        return edit.commit();
    }
    
    public static boolean put(final String s, final long n) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException("Key-Value pair cannot be blank or null");
        }
        final SharedPreferences.Editor edit = Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).edit();
        edit.putLong(s, n);
        return edit.commit();
    }
    
    public static boolean put(final String s, final String s2) {
        if (s == null || s2 == null || s.equals("")) {
            throw new IllegalArgumentException("Key-Value pair cannot be blank or null");
        }
        final SharedPreferences.Editor edit = Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).edit();
        edit.putString(s, s2);
        return edit.commit();
    }
    
    public static boolean put(final String s, final boolean b) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException("Key-Value pair cannot be blank or null");
        }
        final SharedPreferences.Editor edit = Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).edit();
        edit.putBoolean(s, b);
        return edit.commit();
    }
    
    public static void remove(final String s) {
        final SharedPreferences.Editor edit = Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).edit();
        edit.remove(s);
        edit.commit();
    }
    
    public static void resetSession() {
        final SharedPreferences.Editor edit = Constants.APPLICATION_CONTEXT.getSharedPreferences("focial-session", 0).edit();
        edit.clear();
        edit.commit();
    }
}
