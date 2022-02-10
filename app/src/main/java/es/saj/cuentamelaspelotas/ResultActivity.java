package es.saj.cuentamelaspelotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    boolean result;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = getIntent().getBooleanExtra("result", false);
        initComponents();
        checkResult(result);
    }

    private void checkResult(boolean result) {
        if (result){
            win();
        } else {
            lose();
        }
    }

    private void lose() {
        tvResult.setText("YOU LOSE");
        tvResult.setTextColor(Color.RED);
    }

    private void win() {
        tvResult.setText("YOU WIN!");
        tvResult.setTextColor(Color.GREEN);
    }

    private void initComponents() {
        tvResult = findViewById(R.id.tvResult);
    }
}