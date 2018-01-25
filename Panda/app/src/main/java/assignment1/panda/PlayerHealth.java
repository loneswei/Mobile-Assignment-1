package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

public class PlayerHealth implements EntityBase
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private boolean isInit = false;
    private boolean canRemove = false;
    private SurfaceView view = null;
    private float xPos, yPos;
    private String Type;


    @Override
    public boolean isInit() { return isInit; }

    @Override
    public int getRenderLayer() { return LayerConstants.UI_LAYER; }

    @Override
    public void setRenderLayer(int _newLayer) {}

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

        if(this.getType().equals("Heart"))
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.life_heart_icon);
        }
        isInit = true;
    }

    @Override
    public void Update(float _dt) {}

    @Override
    public void Render(Canvas _canvas)
    {
        xPos = 1700.0f;
        yPos = 100.0f;

        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);
    }

    public static PlayerHealth Create (String _Type)
    {
        PlayerHealth result = new PlayerHealth();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}

