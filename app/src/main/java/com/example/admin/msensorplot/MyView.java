package com.example.admin.msensorplot;

/**
 * Created by admin on 2016/11/29.
 */
        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.LinearGradient;
        import android.graphics.Paint;
        import android.graphics.Shader;
        import android.util.AttributeSet;
        import android.util.DisplayMetrics;
        import android.view.Display;
        import android.view.View;
        import android.view.WindowManager;

/**
 * Created by admin on 2016/11/29.
 */
public class MyView extends View {   //水平仪的VIEW

    Bitmap back;
    Bitmap bubble;
    int bubbleX, bubbleY;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        back = Bitmap.createBitmap(screenWidth, screenWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(back);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Shader shader = new LinearGradient(0, screenWidth, screenWidth * 0.8f, screenWidth * 0.2f, Color.YELLOW, Color.WHITE, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        canvas.drawCircle(screenWidth/2 - 50, screenWidth/2, screenWidth/2-50, paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(5);
        paint2.setColor(Color.BLACK);
        canvas.drawCircle(screenWidth / 2 - 50, screenWidth / 2, screenWidth / 2-50, paint2);
        canvas.drawLine(0, screenWidth / 2, screenWidth, screenWidth / 2, paint2);
        canvas.drawLine(screenWidth / 2 - 50, 0, screenWidth / 2-50, screenWidth, paint2);
        paint2.setStrokeWidth(10);
        paint2.setColor(Color.RED);
        canvas.drawLine(screenWidth / 2 - 30 -50, screenWidth / 2, screenWidth / 2 + 30-50, screenWidth / 2, paint2);
        canvas.drawLine(screenWidth / 2-50, screenWidth / 2 - 30, screenWidth / 2-50, screenWidth / 2 + 30, paint2);
        bubble = BitmapFactory.decodeResource(getResources(), R.drawable.bubble);
    }
    @Override
    protected  void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(back, 0, 0, null);
        canvas.drawBitmap(bubble,bubbleX,bubbleY,null);

    }
}

