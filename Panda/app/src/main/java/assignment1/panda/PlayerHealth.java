package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

public class PlayerHealth implements EntityBase
{
    public final static PlayerHealth Instance = new PlayerHealth();

    private Bitmap bmp = null;
    private boolean isDone = false;
    private boolean isInit = false;
    private float xPos, yPos;
    private String Type;
    private int ScreenWidth, ScreenHeight;
    private Bitmap scaledbmp = null;
    private int HP;

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
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        if(this.getType().equals("Heart1"))
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.life_heart_icon);
            scaledbmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(),true);
            xPos = ScreenWidth * 0.96f;
            yPos = ScreenHeight * 0.06f;

        }
        if(this.getType().equals("Heart2"))
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.life_heart_icon);
            scaledbmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(),true);
            xPos = ScreenWidth * 0.86f;
            yPos = ScreenHeight * 0.06f;

        }
        if(this.getType().equals("Heart3"))
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.life_heart_icon);
            scaledbmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(),true);
            xPos = ScreenWidth * 0.76f;
            yPos = ScreenHeight * 0.06f;

        }
        isInit = true;
    }

    @Override
    public void Update(float _dt) {}
    @Override
    public void Render(Canvas _canvas) { _canvas.drawBitmap(scaledbmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null); }

    public int getHP()
    {
        return HP;
    }

    public void setHP(int _hp)
    {
        HP = _hp;
    }

    public static PlayerHealth Create (String _Type)
    {
        PlayerHealth result = new PlayerHealth();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}

