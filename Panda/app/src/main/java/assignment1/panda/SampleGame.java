package assignment1.panda;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class SampleGame
{
    public final static SampleGame Instance = new SampleGame();
    private float timer = 0.0f;

    private SampleGame() {}
    public void Init(SurfaceView _view)
    {
        EntityManager.Instance.Init(_view);
        SampleBackground.Create();
    }

    public void Update(float _dt)
    {
        timer += _dt;
        if(timer > 1.0f)
        {
            SampleEntity.Create();
            timer = 0.0f;
        }
        EntityManager.Instance.Update(_dt);
    }

    public void Render(Canvas _canvas) { EntityManager.Instance.Render(_canvas); }
}
