package com.example.doodle;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
    private final String PREF_NAME = "game_data";
    private final String KEY_SCORE = "high_score";

    private static ScoreManager instance;

    private SharedPreferences prefs;

    public static ScoreManager getInstance(Context context){
        if(instance ==null){
            instance = new ScoreManager(context);
        }
        return instance;

    }

    private ScoreManager (Context context){
        prefs = context.getApplicationContext().getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);



    }

    public List<Integer> getScore() throws JSONException {
        String json = prefs.getString("high_scores", "[]");
        JSONArray arr = new JSONArray(json);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            result.add(arr.getInt(i));
        }
        return result;
    }

    public void addScore(int score) throws JSONException {
        List<Integer> scores = getScore();
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());
        if (scores.size() > 5) {
            scores = scores.subList(0, 5);
        }
        JSONArray arr = new JSONArray(scores);
        prefs.edit().putString(KEY_SCORE, arr.toString()).apply();
    }


}
