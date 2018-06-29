package cevichito.omarcode.com.cevichito.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import cevichito.omarcode.com.cevichito.Modelo.User;

public class Session {

    private static Session INSTANCE;
    public static final String PREF_NAME = "DATA_SESSION";
    //public static final String PREF_USER_ID = "PREF_USER_ID";
    public static final String PREF_USER_NAME = "PREF_USER_NAME";
    public static final String PREF_USER_EMAIL = "PREF_USER_EMAIL";
    public static final String PREF_USER_SESSION = "PREF_USER_SESSION";
    private boolean isloggedIn = false;
    SharedPreferences preferences;

    public static Session get(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new Session(context);
        }
        return INSTANCE;
    }

    private Session(Context context) {
        preferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public boolean isLoggedIn() {
       isloggedIn = preferences.getBoolean("PREF_USER_SESSION", false);
       return isloggedIn;
    }

    public void saveUser(User user) {
        if(user != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(PREF_USER_NAME, user.getName());
            //editor.putString(PREF_USER_EMAIL, user.getEmail());
            editor.putBoolean(PREF_USER_SESSION, true);
            editor.apply();
        }
    }

    public User getUser() {
        User u = new User();
        u.setName(preferences.getString(PREF_USER_NAME, " "));
        return u;
    }

    public void logOut() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_USER_NAME, null);
        //editor.putString(PREF_USER_EMAIL, user.getEmail());
        editor.putBoolean(PREF_USER_SESSION, false);
        editor.apply();
    }
}
