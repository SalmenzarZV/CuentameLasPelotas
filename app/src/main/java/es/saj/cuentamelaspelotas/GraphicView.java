package es.saj.cuentamelaspelotas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GraphicView extends View {
    Paint text;
    Paint[] paintBalls;
    ArrayList<int[]> balls;

    final int RADIO = 30 ;
    int initPosText = getHeight()+ 32,
        textSpeed = 13,
        difficulty = 0;
    int centroX, centroY;
    int[] colors = {Color.BLUE, Color.BLACK, Color.RED, Color.GREEN, Color.MAGENTA, Color.YELLOW};

    public GraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        text = new Paint();
        text.setColor(Color.BLUE);
        text.setTextSize(100);
        createBalls(difficulty);
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

    }

    private void createBalls(int difficulty) {
        int[] colors;
        int nBalls;
        switch (difficulty){
            case 1:/*Easy Mode*/  //Log.v("jamaica", "izi mode");
                colors = chooseColors(2);
                nBalls = ThreadLocalRandom.current().nextInt(2, 4);
                balls = inflateBalls(colors, nBalls);
                break;
            case 2:/*Normal Mode*/ //Log.v("jamaica", "normalelelemode");
                colors = chooseColors(ThreadLocalRandom.current().nextInt(0, this.colors.length));
                nBalls = ThreadLocalRandom.current().nextInt(4, 7);
                balls = inflateBalls(colors, nBalls);
                break;
            case 3:/*Hard Mode*/ //Log.v("jamaica", "Dificilisimode");
                colors = chooseColors(0);
                nBalls = ThreadLocalRandom.current().nextInt(7, 11);
                balls = inflateBalls(colors, nBalls);
                break;
        }

        paintBalls = new Paint[balls.size()];
    }

    private ArrayList<int[]> inflateBalls(int[] colors, int nBalls) {
        int randomX, randomY, color;
        int[] ball;
        ArrayList<int[]> balls= new ArrayList<int[]>();
        for (int i = 0; i<nBalls; i++){
            randomX = ThreadLocalRandom.current().nextInt(RADIO, getWidth());
            randomY = ThreadLocalRandom.current().nextInt(RADIO, getHeight());
            color = colors[ThreadLocalRandom.current().nextInt(0, colors.length)];
            ball = new int[] {randomX, randomY, color};
            balls.add(ball);
        }
        return balls;
    }

    private int[] chooseColors(int numColors) {
        int[] colors;
        if (numColors == 0){
            colors = this.colors;
        } else {
            ArrayList<Integer> addColors= new ArrayList<>();

            for (int i = 0; i<numColors; i++){
                int color = ThreadLocalRandom.current().nextInt(0,this.colors.length);
                if (addColors.contains(color)){
                    i--;
                } else {
                    addColors.add(color);
                }
            }
            colors = new int[addColors.size()];
            for (int i = 0; i<colors.length; i++){
                colors[i] = addColors.get(i);
            }
        }

        return colors;
    }

    private void normaleMode() {

    }

    private void dificilisiMode() {

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