package es.saj.cuentamelaspelotas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ThreadLocalRandom;

public class GraphicView extends View {
    Paint text;
    Paint[] balls;

    int initPosText = getHeight()+ 32,
        textSpeed = 13,
        difficulty = 0;
    int posIniX, posIniY, centroX, centroY, xPeed, ySpeed;
    int[] colors = {Color.BLUE, Color.BLACK, Color.RED, Color.GREEN, Color.MAGENTA, Color.YELLOW};

    public GraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        text = new Paint();
        text.setColor(Color.BLUE);
        text.setTextSize(100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        centroX = w / 2;
        centroY = h / 2;
    }

    protected void onDraw(Canvas c) {
        final int screenWidth = getWidth(),
                screenHeight = getHeight();
        if (initPosText < screenHeight+500){
            paintText(c, screenHeight);
        } else if (difficulty != 0){
            paintBalls(c);
            /*
            Paint apc = new Paint();
            apc.setColor(Color.RED);
            c.drawCircle(centroX, centroY, 30,apc);
            */

        } else {
            String sorry = "We're sorry, something bad happened...";
            text.setTextSize(50);
            c.drawText(sorry, centroX-250, centroY, text);
        }
        postInvalidateDelayed(1);
    }



    private void paintText(Canvas c, int screenHeight) {
        initPosText += textSpeed;
        if (initPosText < screenHeight+100){
            c.drawText("Ready?", centroX-150, initPosText, text);
        }

    }

    //TODO COMPROBAR QUE EL ARRAY DE PELOTAS ESTA INICIALIZADO
    private void paintBalls(Canvas c) {
        if (balls == null || balls.length == 0){
            createBalls(difficulty);
        }

    }

    private void createBalls(int difficulty) {
        switch (difficulty){
            case 1:/*Easy Mode*/  //Log.v("jamaica", "izi mode");
                iziMode();
                break;
            case 2:/*Normal Mode*/ //Log.v("jamaica", "normalelelemode");
                normaleMode();
                break;
            case 3:/*Hard Mode*/ //Log.v("jamaica", "Dificilisimode");
                dificilisiMode();
                break;
        }
    }

    private void dificilisiMode() {

    }

    private void normaleMode() {

    }

    private void iziMode() {
        int color1 = ThreadLocalRandom.current().nextInt(0, colors.length), color2;

        //Comprobamos que los colores no sean los
        do{
            color2 = ThreadLocalRandom.current().nextInt(0,colors.length);
        }while (color2 == color1);

        int numBalls = ThreadLocalRandom.current().nextInt(2, 4);
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}