package es.saj.cuentamelaspelotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class GuessActivity extends AppCompatActivity {

    int difficulty, nBalls;
    ArrayList<int[]> balls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        difficulty = getIntent().getIntExtra("difficulty",0);
        getBalls();

    }

    private void getBalls() {
        nBalls = getIntent().getIntExtra("nBalls", 0);
        if (!(nBalls == 0)){
            for (int i=0; i<nBalls;i++){
                int[] ball = getIntent().getIntArrayExtra(String.valueOf(i));
                balls.add(i, ball);
            }
        } else {
            Log.v("jamaica", "jaja no kapasao");
        }
    }
}