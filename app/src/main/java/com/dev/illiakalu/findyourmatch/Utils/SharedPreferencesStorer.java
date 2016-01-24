package com.dev.illiakalu.findyourmatch.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by illiakaliuzhnyi on 11/30/15.
 */
public class SharedPreferencesStorer {

    Context _cnt;

    private static SharedPreferencesStorer instance = null;

    SharedPreferences prefs;
    Editor editor;

    private SharedPreferencesStorer(Context context) {
        this._cnt = context;

        prefs = context.getSharedPreferences("FindYourMatch",  Context.MODE_PRIVATE);
        editor = prefs.edit();
    }


    public static SharedPreferencesStorer getInstance(Context _context) {

        if (instance == null){
            instance = new SharedPreferencesStorer(_context);
        }

        return instance;
    }

    public boolean isFirstLaunch() {
            return prefs.getBoolean("isFirstLaunch", true);
    }

    public void setIsFirstLaunch(boolean isFirstLaunch){
            editor.putBoolean("isFirstLaunch", isFirstLaunch);
            editor.commit();
    }

    public void incrementPageOfPersons() {
        editor.putInt("pageNumber", getPageOfPersonsToLoad() + 1);
        editor.commit();

    }

    public int getPageOfPersonsToLoad(){
        return prefs.getInt("pageNumber", 0);
    }
}
