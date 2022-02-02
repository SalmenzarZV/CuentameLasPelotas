package es.saj.cuentamelaspelotas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GraphicView extends View {
    Paint text;
    Paint[] balls;

    int initPosText = getHeight()+ 32,
        textSpeed = 13;
    int posIniX, posIniY, centroX, centroY;


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
        } else {
            Paint apc = new Paint();
            apc.setColor(Color.RED);
            c.drawCircle(centroX, centroY, 30,apc);
        }
        postInvalidateDelayed(1);
    }

    private void paintText(Canvas c, int screenHeight) {
        initPosText += textSpeed;
        if (initPosText < screenHeight+100){
            c.drawText("Ready?", centroX, initPosText, text);
        }

    }

}