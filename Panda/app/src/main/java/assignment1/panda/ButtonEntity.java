package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

// This is done by Wong Shih Wei.
public class ButtonEntity implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private boolean isInit = false;
    private float xPos, yPos;
    private String Type;
    private int ScreenWidth, ScreenHeight;

    // Collidable interface
    @Override
    public float getPosX() { return xPos; }

    @Override
    public float getPosY() { return yPos; }

    @Override
    public float getRadius() { return bmp.getHeight() * 0.5f; }

    @Override
    public void onHit(Collidable _other) {}

    // EntityBase interface
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

        // Check for 4 different rubbish type and assign respective image to bmp
        yPos = ScreenHeight * 0.925f;
        switch(this.getType())
        {
            case "PaperButton":
                bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.blue_paper_recyclingbin);
                xPos = ScreenWidth * 0.2f;
                break;
            case "PlasticButton":
                bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_green_recyclingbin);
                xPos = ScreenWidth * 0.35f;
                break;
            case "MetalButton":
                bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_red_recyclingbin);
                xPos = ScreenWidth * 0.65f;
                break;
            case "OthersButton":
                bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.generalwaste_greyrecyclingbin);
                xPos = ScreenWidth * 0.8f;
                break;
            // This is for GamePlay, Pause button. - Done by Liang Li.
            case "PauseButton":
                bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.pausebutton);
                xPos = ScreenWidth * 0.025f;
                yPos = ScreenHeight * 0.04f;
                break;
        }
        isInit = true;
    }

    @Override
    public void Update(float _dt) {}

    @Override
    public void Render(Canvas _canvas) { _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null); }

    public static ButtonEntity Create(String _Type)
    {
        ButtonEntity result = new ButtonEntity();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
