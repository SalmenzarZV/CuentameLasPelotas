package es.saj.cuentamelaspelotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class GuessActivity extends AppCompatActivity {

    int difficulty;
    ArrayList<int[]> balls = GraphicView.balls;
    Button btGuess;
    EditText etHowMany, etBlue, etRed, etGreen, etPink, etYellow;
    TextView lbBlue, lbRed, lbGreen, lbPink, lbYellow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        difficulty = getIntent().getIntExtra("difficulty",0);

        btGuess = findViewById(R.id.btGuess);
        showComponents(difficulty);
        Log.v("jamaica", balls.size()+"");
        printColorBalls();

        btGuess.setOnClickListener(view -> {
            boolean result;
            if (difficulty == 3){
                result = checkHardMode();
            } else {
                int numBalls = Integer.parseInt(etHowMany.getText().toString());
                result = (numBalls == balls.size());
            }
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("result", result);
            Log.v("jamaica", Boolean.toString(result));
            startActivity(intent);
        });
    }

    private boolean checkHardMode() {
        HashMap<String, Integer> nBallsPerColor = getBallsPerColor();
        int blue = Integer.parseInt(etBlue.getText().toString()),
                red = Integer.parseInt(etRed.getText().toString()),
                green = Integer.parseInt(etGreen.getText().toString()),
                pink = Integer.parseInt(etPink.getText().toString()),
                yellow = Integer.parseInt(etYellow.getText().toString());

        return (blue == nBallsPerColor.get("blue") && red == nBallsPerColor.get("red") &&
                    green == nBallsPerColor.get("green") && pink == nBallsPerColor.get("pink") &&
                    yellow == nBallsPerColor.get("yellow"));
    }

    private HashMap<String, Integer> getBallsPerColor() {
        HashMap<String, Integer> colorBalls = new HashMap<>();
        int blue, red, green, pink, yellow;
        blue = red = green = pink = yellow = 0;
        for (int[] ball: balls) {
            int color = ball[4];
            switch(color){
                case Color.BLUE:
                    blue++;
                    break;
                case Color.RED:
                    red++;
                    break;
                case Color.GREEN:
                    green++;
                    break;
                case Color.MAGENTA:
                    pink++;
                    break;
                case Color.YELLOW:
                    yellow++;
                    break;
            }
        }
        colorBalls.put("blue", blue);
        colorBalls.put("red", red);
        colorBalls.put("green", green);
        colorBalls.put("pink", pink);
        colorBalls.put("yellow", yellow);
        return colorBalls;
    }

    private void printColorBalls(){
        int blue, red, green, pink, yellow;
        blue = red = green = pink = yellow = 0;
        for (int[] ball: balls) {
            int color = ball[4];
            switch(color){
                case Color.BLUE:
                    blue++;
                    break;
                case Color.RED:
                    red++;
                    break;
                case Color.GREEN:
                    green++;
                    break;
                case Color.MAGENTA:
                    pink++;
                    break;
                case Color.YELLOW:
                    yellow++;
                    break;
            }
        }
        Log.v("jamaica", "Azules:"+blue);
        Log.v("jamaica", "Rojas:"+red);
        Log.v("jamaica", "Verdes:"+green);
        Log.v("jamaica", "Rosas:"+pink);
        Log.v("jamaica", "Amarillas"+yellow);
    }

    private void showComponents(int difficulty) {
        if (difficulty == 3){
            initHardComponents();
            showHardComponents();
        } else {
            etHowMany = findViewById(R.id.etHowMany);
            etHowMany.setVisibility(View.VISIBLE);
        }
    }

    private void initHardComponents(){
        etBlue = findViewById(R.id.etBlue);
        etRed = findViewById(R.id.etRed);
        etGreen = findViewById(R.id.etGreen);
        etPink = findViewById(R.id.etPink);
        etYellow = findViewById(R.id.etYellow);

        lbBlue = findViewById(R.id.tvBlue);
        lbRed = findViewById(R.id.tvRed);
        lbGreen = findViewById(R.id.tvGreen);
        lbPink = findViewById(R.id.tvPink);
        lbYellow = findViewById(R.id.tvYellow);
    }

    private void showHardComponents(){
        etBlue.setVisibility(View.VISIBLE);
        etRed.setVisibility(View.VISIBLE);
        etGreen.setVisibility(View.VISIBLE);
        etPink.setVisibility(View.VISIBLE);
        etYellow.setVisibility(View.VISIBLE);
        lbBlue.setVisibility(View.VISIBLE);
        lbRed.setVisibility(View.VISIBLE);
        lbGreen.setVisibility(View.VISIBLE);
        lbPink.setVisibility(View.VISIBLE);
        lbYellow.setVisibility(View.VISIBLE);
    }
}