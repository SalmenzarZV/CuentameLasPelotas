package es.saj.cuentamelaspelotas;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.concurrent.ThreadLocalRandom;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Ball {
    private static final int RADIO = 30 ;
    final int[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.BLACK, Color.MAGENTA};

    int w, h, randomBalls, randomX, randomY, color;


    public Ball(int width, int height) {
        w = width - RADIO;
        h = height - RADIO;
        randomBalls = ThreadLocalRandom.current().nextInt(2, 6);//OJO, RANGO ENTRE 2 Y 6
        color = colors[ThreadLocalRandom.current().nextInt(0, colors.length-1)];
    }

}
