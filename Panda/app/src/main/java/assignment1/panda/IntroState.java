package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;

public class IntroState implements StateBase
{
    private float timer = 5.0f;
    private Bitmap splash = null;

    @Override
    public String GetName() { return "Intro"; }

    @Override
    public void OnEnter(SurfaceView _view) { splash = BitmapFactory.decodeResource(_view.getResources(),R.drawable.splashscreen_background); }

    @Override
    public void OnExit() {}

    @Override
    public void Update(float _dt)
    {
        timer -= _dt;
        if(timer <= 0.0f) { StateManager.Instance.ChangeState("MainGame"); }

        if(TouchManager.Instance.hasTouch()) { StateManager.Instance.ChangeState("MainGame"); }
    }

    @Override
    public void Render(Canvas _canvas)
    {
        Matrix transform = new Matrix();
        transform.postTranslate(-splash.getWidth() * 0.5f, -splash.getHeight()* 0.5f);
        transform.postScale(timer, timer);
        transform.postTranslate(_canvas.getWidth() * 0.5f, _canvas.getHeight() * 0.5f);
        _canvas.drawBitmap(splash,transform,null);
    }
}
