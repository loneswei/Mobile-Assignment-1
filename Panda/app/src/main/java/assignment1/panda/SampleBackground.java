package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class SampleBackground implements EntityBase
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private SurfaceView view = null;
    private float xPos, yPos, offset;

    @Override
    public boolean isDone() { return isDone; }

    @Override
    public void setIsDone(boolean _isDone) { isDone = _isDone; }

    @Override
    public void Init(SurfaceView _view)
    {
        view = _view;
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.mipmap.option_background);
        offset = 0.0f;
    }

    @Override
    public void Update(float _dt) { offset += _dt * 0.1f; }

    @Override
    public void Render(Canvas _canvas)
    {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        float xOffset = (float)Math.sin(offset) * bmp.getWidth() * 0.3f;

        _canvas.drawBitmap(bmp,xPos - bmp.getWidth() * 0.5f + xOffset, yPos - bmp.getHeight() * 0.5f, null);
    }

    public static SampleBackground Create()
    {
        SampleBackground result = new SampleBackground();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
