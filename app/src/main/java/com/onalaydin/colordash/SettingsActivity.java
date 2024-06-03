package com.onalaydin.colordash;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox soundCheckbox;
    private Spinner difficultySpinner;
    private Button saveSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        soundCheckbox = findViewById(R.id.soundCheckbox);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        saveSettingsButton = findViewById(R.id.saveSettingsButton);

        setupDifficultySpinner();
        loadSettings();

        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    private void setupDifficultySpinner() {
        // Zorluk seviyeleri için spinner ayarlama
        String[] levels = {"Easy", "Medium", "Hard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, levels);
        difficultySpinner.setAdapter(adapter);
    }

    private void saveSettings() {
        // Ayarların kaydedilmesi işlemleri
        boolean soundEnabled = soundCheckbox.isChecked();
        String selectedDifficulty = difficultySpinner.getSelectedItem().toString();

        // SharedPreferences nesnesini al
        SharedPreferences sharedPreferences = getSharedPreferences("GameSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Ayarları kaydet
        editor.putBoolean("soundEnabled", soundEnabled);
        editor.putString("difficulty", selectedDifficulty);
        editor.apply(); // Değişiklikleri kaydet

        // Geri bildirim mesajı
        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
    }

    private void loadSettings() {
        // SharedPreferences'dan ayarları yükle
        SharedPreferences sharedPreferences = getSharedPreferences("GameSettings", MODE_PRIVATE);
        boolean soundEnabled = sharedPreferences.getBoolean("soundEnabled", true);
        String difficulty = sharedPreferences.getString("difficulty", "Easy");

        soundCheckbox.setChecked(soundEnabled);
        int spinnerPosition = ((ArrayAdapter<String>)difficultySpinner.getAdapter()).getPosition(difficulty);
        difficultySpinner.setSelection(spinnerPosition);
    }
}
