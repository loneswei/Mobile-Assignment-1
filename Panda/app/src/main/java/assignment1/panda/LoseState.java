package assignment1.panda;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class LoseState implements StateBase
{

    private int ScreenWidth, ScreenHeight;

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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level_select);
        // Back Button
        // Need to change to a better image
        bmpBackButton = BitmapFactory.decodeResource(_view.getResources(), R.drawable.back);

        // Display Metrics
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
    }

    @Override
    public void OnExit() { }

    @Override
    public void Update(float _dt)
    {
        float imgRadius = bmpBackButton.getHeight() * 0.5f;

        // Checking if there's any touch on the back button
        if (TouchManager.Instance.isDown())
        {
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.475f, ScreenHeight * 0.55f, imgRadius))
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
        backgroundTransform.setScale(10 ,10);
        _canvas.drawBitmap(bmp, backgroundTransform, null);

        // Back Button
        Matrix ButtonTransform = new Matrix();
        ButtonTransform.setTranslate(ScreenWidth * 0.45f, ScreenHeight * 0.5f);
        _canvas.drawBitmap(bmpBackButton, ButtonTransform, null);

        // Lose Message
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        _canvas.drawText(loseMessage, 500, 400, paint);

    }
}
