package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

// This is done by Wong Shih Wei.
public class GameBackground implements EntityBase
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private String Type;
    private int ScreenWidth, ScreenHeight;
    private Bitmap scaledbmp = null;

    @Override
    public boolean isInit() { return bmp != null; }

    @Override
    public int getRenderLayer() { return LayerConstants.BACKGROUND_LAYER; }

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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.gameplay);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight,true);
    }

    @Override
    public void Update(float _dt) {}

    @Override
    public void Render(Canvas _canvas)
    {
        _canvas.drawBitmap(scaledbmp,0.0f,0.0f,null);
    }

    public static GameBackground Create(String _Type)
    {
        GameBackground result = new GameBackground();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
