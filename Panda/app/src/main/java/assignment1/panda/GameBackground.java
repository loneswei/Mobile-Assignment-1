package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class GameBackground implements EntityBase
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private SurfaceView view = null;
    private float xPos, yPos;
    private String Type;

    @Override
    public boolean isDone() { return isDone; }

    @Override
    public void setIsDone(boolean _isDone) { isDone = _isDone; }

    @Override
    public String getType() { return Type; }

    @Override
    public void setType(String _Type) { Type = _Type; }

    @Override
    public void Init(SurfaceView _view)
    {
        view = _view;
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.gameplay);
    }

    @Override
    public void Update(float _dt) {}

    @Override
    public void Render(Canvas _canvas)
    {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        _canvas.drawBitmap(bmp,xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);
    }

    public static GameBackground Create(String _Type)
    {
        GameBackground result = new GameBackground();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
