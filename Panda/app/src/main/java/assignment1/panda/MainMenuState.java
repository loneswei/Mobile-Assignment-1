package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class MainMenuState implements StateBase
{
    private int ScreenWidth, ScreenHeight;

    // Background
    private Bitmap bmp = null;
    // Play Button
    private Bitmap bmpPlayButton = null;
    // Options Button
    private Bitmap bmpOptionsButton = null;

    @Override
    public String GetName() { return "MainMenu"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // MainMenu Background
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.mainmenu_background);
        // Play Button
        bmpPlayButton = BitmapFactory.decodeResource(_view.getResources(), R.drawable.playbutton);
        // Option Button
        bmpOptionsButton = BitmapFactory.decodeResource(_view.getResources(), R.drawable.optionbutton);

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
        float imgRadius = bmpPlayButton.getWidth() * 0.5f;
        float imgRadius2 = bmpOptionsButton.getWidth() * 0.5f;

        // Checking if there's any touch on the buttons
        if(TouchManager.Instance.isDown())
        {
            // Play Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.15f, ScreenHeight * 0.8f, imgRadius))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                StateManager.Instance.ChangeState("LevelSelect");
            }
            // Option Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.85f, ScreenHeight * 0.8f, imgRadius2))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                StateManager.Instance.ChangeState("Options");
            }
        }
    }

    @Override
    public void Render(Canvas _canvas)
    {
        // Background
        Matrix backgroundTransform = new Matrix();
        backgroundTransform.setTranslate(ScreenWidth * -0.05f, ScreenHeight * -0.05f);
        _canvas.drawBitmap(bmp, backgroundTransform, null);

        // Play Button
        Matrix ButtonTransform = new Matrix();
        ButtonTransform.setTranslate(ScreenWidth * 0.1f, ScreenHeight * 0.8f);
        _canvas.drawBitmap(bmpPlayButton, ButtonTransform, null);

        // Option Button
        ButtonTransform = new Matrix();
        ButtonTransform.setTranslate(ScreenWidth * 0.8f, ScreenHeight * 0.8f);
        _canvas.drawBitmap(bmpOptionsButton, ButtonTransform, null);
    }

}
