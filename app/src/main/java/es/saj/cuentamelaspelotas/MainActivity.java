package es.saj.cuentamelaspelotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btEasy, btNormal, btHard;
    public int difficulty;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        context = this;
        initComponents();
    }

    private void initComponents() {
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
        btEasy.setVisibility(View.GONE);
        btNormal.setVisibility(View.GONE);
        btHard.setVisibility(View.GONE);

        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra("difficulty", difficulty);
        context.startActivity(intent);
    }


}