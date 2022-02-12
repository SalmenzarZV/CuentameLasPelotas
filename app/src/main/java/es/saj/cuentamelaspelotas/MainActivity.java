package es.saj.cuentamelaspelotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btEasy, btNormal, btHard;
    public int difficulty;
    TextView tvGameTitle;
    Context context;
    private MediaPlayer mediaPlayer;
    int red = 255, green = 0, blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        tvGameTitle.setOnClickListener(view -> {
            rainbow();
        });
    }

    private void rainbow() {
        tvGameTitle.setTextColor(Color.rgb(generateRandom(0, 255),generateRandom(0, 255),generateRandom(0, 255)));
    }



    private void initialize() {
        context = this;
        initComponents();
        mediaPlayer = MediaPlayer.create(this, R.raw.menu_theme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    private void initComponents() {
        tvGameTitle = findViewById(R.id.tvGameTitle);

        btEasy = findViewById(R.id.btEasy);
        btNormal = findViewById(R.id.btNormal);
        btHard = findViewById(R.id.btHard);

        btEasy.setOnClickListener(this);
        btNormal.setOnClickListener(this);
        btHard.setOnClickListener(this);
    }

    //TODO IMPLEMENTAR PISTAS DE AUDIO PARA CADA NIVEL
    @Override
    public void onClick(View view) {
        if (view.equals(btEasy)){
            difficulty = 1;
        }

        if (view.equals(btNormal)){
            difficulty = 2;
        }

        if (view.equals(btHard)){
            difficulty = 3;
        }
        mediaPlayer.stop();
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("difficulty", difficulty);
        context.startActivity(intent);
    }

    public  int generateRandom(int min, int max){
        int random;
        max++;
        random = (int) (Math.random()*(max-min) +min);

        return random;
    }

}