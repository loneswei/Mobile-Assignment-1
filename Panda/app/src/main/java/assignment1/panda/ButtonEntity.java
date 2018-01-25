package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class ButtonEntity implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private boolean isInit = false;
    private float xPos, yPos;
    private String Type;

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
        // Check for 4 different rubbish type and assign respective image to bmp
        yPos = 1000.0f;
        if(this.getType() == "PaperButton")
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.blue_paper_recyclingbin);
            xPos = 300.0f;
        }
        else if(this.getType() == "PlasticButton")
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_green_recyclingbin);
            xPos = 600.0f;
        }
        else if(this.getType() == "MetalButton")
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_red_recyclingbin);
            xPos = 1200.0f;
        }
        else if(this.getType() == "OthersButton")
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.generalwaste_greyrecyclingbin);
            xPos = 1500.0f;
        }
        else if(this.getType() == "BackButton")
        {
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.back);
            xPos = 45.0f;
            yPos = 45.0f;
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
