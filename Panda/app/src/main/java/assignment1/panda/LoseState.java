package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

// This is done by Goh Liang Li.
public class LoseState implements StateBase
{
    private int ScreenWidth, ScreenHeight;
    private Bitmap scaledbmp = null;

    // Background
    private Bitmap bmp = null;
    // Back Button
    private Bitmap bmpBackButton = null;
    // Lose Message
    private String loseMessage = "You have lost!";

    @Override
    public String GetName() { return "Lose"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // Win & Lose Background
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.losewin_screen);
        // Back Button
        bmpBackButton = BitmapFactory.decodeResource(_view.getResources(), R.drawable.backbutton);

        // Display Metrics
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight, true);
    }

    @Override
    public void OnExit() { }

    @Override
    public void Update(float _dt)
    {
        float imgRadius = bmpBackButton.getWidth() * 0.5f;

        // Checking if there's any touch on the back button
        if (TouchManager.Instance.isDown())
        {
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.495f, ScreenHeight * 0.55f, imgRadius))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                // Go back to MainMenu
                StateManager.Instance.ChangeState("MainMenu");
            }
        }
    }


    @Override
    public void Render(Canvas _canvas)
    {
        // Background
        Matrix backgroundTransform = new Matrix();
        backgroundTransform.setTranslate(ScreenWidth * -0.05f, ScreenHeight * -0.05f);
        _canvas.drawBitmap(scaledbmp, backgroundTransform, null);

        // Back Button
        Matrix ButtonTransform = new Matrix();
        ButtonTransform.setTranslate(ScreenWidth * 0.45f, ScreenHeight * 0.5f);
        _canvas.drawBitmap(bmpBackButton, ButtonTransform, null);

        // Lose Message
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        _canvas.drawText(loseMessage, 590, 400, paint);

    }
}
