package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

// This is done by Goh Liang Li.
public class IntroState implements StateBase
{
    private float timer = 5.0f;
    private Bitmap splash = null;
    private int ScreenWidth, ScreenHeight;
    private Bitmap scaledbmp = null;

    @Override
    public String GetName() { return "Intro"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        splash = BitmapFactory.decodeResource(_view.getResources(),R.drawable.splashscreen_background);
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        scaledbmp = Bitmap.createScaledBitmap(splash, ScreenWidth, ScreenHeight,true);
    }

    @Override
    public void OnExit(){}

    @Override
    public void Update(float _dt)
    {
        timer -= _dt;
        if(timer <= 0.0f) { StateManager.Instance.ChangeState("MainMenu"); }
        if(TouchManager.Instance.hasTouch()) { StateManager.Instance.ChangeState("MainMenu"); }
    }

    @Override
    public void Render(Canvas _canvas) { _canvas.drawBitmap(scaledbmp,0.0f,0.0f,null); }
}
