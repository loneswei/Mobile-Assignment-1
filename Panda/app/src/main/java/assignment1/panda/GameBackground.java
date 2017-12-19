package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

public class GameBackground implements EntityBase
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private SurfaceView view = null;
    private float xPos, yPos;
    private String Type;
    private Typeface myfont;

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
        view = _view;
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.gameplay);
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/Gemcut.otf");
    }

    @Override
    public void Update(float _dt) {}

    @Override
    public void Render(Canvas _canvas)
    {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        _canvas.drawBitmap(bmp,xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);

        // Text on Screen
        Paint paint = new Paint();
        paint.setARGB(255,100,0,0);
        paint.setStrokeWidth(200);
        paint.setTextSize(100.0f);
        paint.setTypeface(myfont);
        if(!Game.Instance.getIsPaused())
            _canvas.drawText("Playing", 300,800,paint);
        else
            _canvas.drawText("Paused", 300,800,paint);
    }

    public static GameBackground Create(String _Type)
    {
        GameBackground result = new GameBackground();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
