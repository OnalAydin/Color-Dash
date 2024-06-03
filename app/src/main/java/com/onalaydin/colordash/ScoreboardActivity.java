package com.onalaydin.colordash;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreboardActivity extends AppCompatActivity {

    private ListView scoreListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        scoreListView = findViewById(R.id.scoreListView);
        loadHighScores();
    }

    private void loadHighScores() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dbHelper.getHighScores());
        scoreListView.setAdapter(adapter);
    }

    private void addSampleScore() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.addScore("Kullanıcı Adı", 100); // Örnek kullanım
    }
}
