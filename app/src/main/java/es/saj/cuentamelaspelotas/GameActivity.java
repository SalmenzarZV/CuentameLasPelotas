package es.saj.cuentamelaspelotas;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    GraphicView graphicView;
    int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        graphicView = findViewById(R.id.graphicView);
        difficulty = getIntent().getIntExtra("difficulty", 0);
        graphicView.setContext(this);
        graphicView.setDifficulty(difficulty);
    }

}