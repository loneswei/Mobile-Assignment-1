package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

// This is done by Goh Liang Li
public class WinState implements StateBase
{
    private int ScreenWidth, ScreenHeight;

    // Background
    private Bitmap bmp = null;
    // Back Button
    private Bitmap bmpBackButton = null;
    // Continue Button
    private Bitmap bmpContinueButton = null;
    // Lose Message
    private String winMessage = "Yay! You have won!";

    private Bitmap scaledbmp = null;

    @Override
    public String GetName() { return "Win"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // Win & Lose Background
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.losewin_screen);
        // Back Button
        bmpBackButton = BitmapFactory.decodeResource(_view.getResources(), R.drawable.backbutton);
        // Continue Button
        bmpContinueButton = BitmapFactory.decodeResource(_view.getResources(), R.drawable.continuebutton);

        // Display Metrics
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight,true);
    }

    @Override
    public void OnExit() { }

    @Override
    public void Update(float _dt)
    {
        float imgRadius = bmpBackButton.getWidth() * 0.5f;
        float imgRadius2 = bmpContinueButton.getWidth() * 0.5f;

        // Checking if there's any touch on the back button
        if (TouchManager.Instance.isDown())
        {
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.395f, ScreenHeight * 0.55f, imgRadius) &&
                    LevelManager.Instance.GetSelectedLevel() < 8)
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                // Go back to MainMenu
                StateManager.Instance.ChangeState("MainMenu");
            }
            // If it is Level 8, the back button is on a different position.
            else if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.49f, ScreenHeight * 0.55f, imgRadius) &&
                    LevelManager.Instance.GetSelectedLevel() == 8)
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                // Go back to MainMenu
                StateManager.Instance.ChangeState("MainMenu");
            }

            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.595f, ScreenHeight * 0.55f, imgRadius2) &&
                    LevelManager.Instance.GetSelectedLevel() != 8)
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                // Go to Next Level
                int currLevel = LevelManager.Instance.GetSelectedLevel();
                LevelManager.Instance.SetSelectedLevel(currLevel + 1);
                StateManager.Instance.ChangeState("MainGame");
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

        if(LevelManager.Instance.GetSelectedLevel() < 8)
        {
            // Back Button
            Matrix ButtonTransform = new Matrix();
            ButtonTransform.setTranslate(ScreenWidth * 0.35f, ScreenHeight * 0.5f);
            _canvas.drawBitmap(bmpBackButton, ButtonTransform, null);
        }
        // If it is level 8, back button is different position.
        else
        {
            // Back Button
            Matrix ButtonTransform = new Matrix();
            ButtonTransform.setTranslate(ScreenWidth * 0.45f, ScreenHeight * 0.5f);
            _canvas.drawBitmap(bmpBackButton, ButtonTransform, null);
        }

        if(LevelManager.Instance.GetSelectedLevel() != 8)
        {
            // Continue Button
            Matrix ButtonTransform = new Matrix();
            ButtonTransform.setTranslate(ScreenWidth * 0.55f, ScreenHeight * 0.5f);
            _canvas.drawBitmap(bmpContinueButton, ButtonTransform, null);
        }

        // Win Message
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        _canvas.drawText(winMessage, 500, 400, paint);

    }
}
