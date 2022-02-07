package es.saj.cuentamelaspelotas;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.concurrent.ThreadLocalRandom;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Ball {
    private static final int RADIO = 30 ;
    int w, h, randomX, randomY, color;


    public Ball(int width, int height, int color) {
        w = width - RADIO;
        h = height - RADIO;
        randomX = ThreadLocalRandom.current().nextInt(RADIO, w);
        randomY = ThreadLocalRandom.current().nextInt(RADIO, h);;
        this.color = color;
    }

    public static int getRADIO() {
        return RADIO;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getRandomX() {
        return randomX;
    }

    public void setRandomX(int randomX) {
        this.randomX = randomX;
    }

    public int getRandomY() {
        return randomY;
    }

    public void setRandomY(int randomY) {
        this.randomY = randomY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
