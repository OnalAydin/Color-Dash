package com.onalaydin.colordash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ColorDash.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE high_scores (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS high_scores");
        onCreate(db);
    }

    public void addScore(String name, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("score", score);
        db.insert("high_scores", null, values);
        db.close();
    }

    public List<String> getHighScores() {
        List<String> scores = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, score FROM high_scores ORDER BY score DESC", null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));
                    String scoreText = name + " - " + score;
                    scores.add(scoreText);
                } catch (Exception e) {
                    e.printStackTrace(); // Hataları yazdır
                }
            } while (cursor.moveToNext());
        } else {
            // Boş cursor kontrolü
            System.out.println("Cursor is empty");
        }
        cursor.close();
        db.close();
        return scores;
    }
}
