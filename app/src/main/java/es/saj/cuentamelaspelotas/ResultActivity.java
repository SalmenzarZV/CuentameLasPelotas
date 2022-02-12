package es.saj.cuentamelaspelotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    boolean result;
    TextView tvResult, tvHowManyResult, tvThereWas,
            tvBlueResult, tvRedResult, tvGreenResult, tvPinkResult, tvYellowResult;
    MediaPlayer mediaPlayer;
    int difficulty;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ArrayList<int[]> balls = GraphicView.balls;
    Button btPhoto;
    ImageView ivPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = getIntent().getBooleanExtra("result", false);
        difficulty = getIntent().getIntExtra("difficulty", 0);
        initComponents();
        checkResult(result);
    }

    private void checkResult(boolean result) {
        if (result){
            mediaPlayer = MediaPlayer.create(this, R.raw.win);
            mediaPlayer.start();
            win();
        } else {
            mediaPlayer = MediaPlayer.create(this, R.raw.lose);
            mediaPlayer.start();
            lose();
        }

        if (difficulty != 0){
            showComponents(difficulty);
        }

    }

    private void showComponents(int difficulty) {
        if (difficulty == 1 || difficulty == 2){
            String result = "HABÍA "+balls.size()+" PELOTAS";
            tvHowManyResult.setText(result);
            tvHowManyResult.setVisibility(View.VISIBLE);
        } else {
            showHardComponents();
            tvBlueResult.append(appendBalls(Color.BLUE));
            tvRedResult.append(appendBalls(Color.RED));
            tvGreenResult.append(appendBalls(Color.GREEN));
            tvPinkResult.append(appendBalls(Color.MAGENTA));
            tvYellowResult.append(appendBalls(Color.YELLOW));
        }
    }

    private String appendBalls(int color) {
        int nBalls = 0;
        for (int[] ball: balls) {
            if (ball[4] == color){
                nBalls++;
            }
        }
        return String.valueOf(nBalls);
    }

    private void showHardComponents() {
        tvThereWas.setVisibility(View.VISIBLE);
        tvBlueResult.setVisibility(View.VISIBLE);
        tvRedResult.setVisibility(View.VISIBLE);
        tvGreenResult.setVisibility(View.VISIBLE);
        tvPinkResult.setVisibility(View.VISIBLE);
        tvYellowResult.setVisibility(View.VISIBLE);
    }

    private void hideComponents(){
        tvHowManyResult.setVisibility(View.GONE);
        tvThereWas.setVisibility(View.GONE);
        tvBlueResult.setVisibility(View.GONE);
        tvRedResult.setVisibility(View.GONE);
        tvGreenResult.setVisibility(View.GONE);
        tvPinkResult.setVisibility(View.GONE);
        tvYellowResult.setVisibility(View.GONE);
    }

    private void lose() {
        tvResult.setText(R.string.lose);
        tvResult.setTextColor(Color.RED);
    }

    private void win() {
        tvResult.setText(R.string.win);
        tvResult.setTextColor(Color.GREEN);
    }

    private void initComponents() {
        tvResult = findViewById(R.id.tvResult);
        tvThereWas = findViewById(R.id.tvThereWas);
        tvHowManyResult = findViewById(R.id.tvHowManyResult);
        tvBlueResult = findViewById(R.id.tvBlueResult);
        tvRedResult = findViewById(R.id.tvRedResult);
        tvGreenResult = findViewById(R.id.tvGreenResult);
        tvPinkResult = findViewById(R.id.tvPinkResult);
        tvYellowResult = findViewById(R.id.tvYellowResult);

        ivPhoto = findViewById(R.id.ivPhoto);

        btPhoto = findViewById(R.id.btPhoto);
        btPhoto.setOnClickListener(view -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPhoto.setImageBitmap(imageBitmap);
        }
        ivPhoto.setVisibility(View.VISIBLE);
        hideComponents();
        btPhoto.setText("Change photo");
        if (result){
            tvResult.setText("ABSOLUTE CHAD");
        } else {
            tvResult.setText("HA! LOSER!");
        }
    }
}