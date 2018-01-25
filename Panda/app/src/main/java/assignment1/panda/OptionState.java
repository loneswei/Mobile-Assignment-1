package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class OptionState implements StateBase
{
    private int ScreenWidth, ScreenHeight;

    // Background
    private Bitmap bmp = null;
    // Back Button
    private Bitmap bmpBackButton = null;

    @Override
    public String GetName() { return "Options"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // Options Background
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.option_background);
        // Back Button
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
        float imgBack = bmpBackButton.getWidth() * 0.5f;

        // Checking if there's any touch on any buttons
        if(TouchManager.Instance.isDown())
        {
            // Back Button.
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.03f, ScreenHeight * 0.05f, imgBack))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                StateManager.Instance.ChangeState("MainMenu");
            }
        }
    }

    @Override
    public void Render(Canvas _canvas)
    {
        // Background
        Matrix backgroundTransform = new Matrix();
        backgroundTransform.setTranslate(ScreenWidth * -0.05f, ScreenHeight * -0.09f);
        _canvas.drawBitmap(bmp, backgroundTransform, null);

        // Back Button
        Matrix buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.f, ScreenHeight * 0.0f);
        _canvas.drawBitmap(bmpBackButton, buttonsTransform, null);
    }
}
