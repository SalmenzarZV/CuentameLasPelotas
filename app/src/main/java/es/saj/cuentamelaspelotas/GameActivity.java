package es.saj.cuentamelaspelotas;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    GraphicView graphicView;
    int difficulty;
    public MediaPlayer readySound;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        graphicView = findViewById(R.id.graphicView);
        difficulty = getIntent().getIntExtra("difficulty", 0);
        graphicView.setContext(this);
        graphicView.setDifficulty(difficulty);
        readySound = MediaPlayer.create(this, R.raw.ready);
        readySound.start();

    }
}