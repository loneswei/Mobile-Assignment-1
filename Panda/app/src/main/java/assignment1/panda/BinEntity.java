package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class BinEntity implements EntityBase, Collidable
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
    public void onHit(Collidable _other)
    {
        EntityBase otherEntity = (EntityBase) _other;
        if(otherEntity.getType() == "PaperBin" ||
                otherEntity.getType() == "PlasticBin" ||
                otherEntity.getType() == "MetalBin" ||
                otherEntity.getType() == "OthersBin")
        {
            otherEntity.setIsDone(true);
            setIsDone(false);
        }
    }

    // EntityBase interface
    @Override
    public boolean isInit() { return isInit; }

    @Override
    public int getRenderLayer() { return LayerConstants.GAMEOBJECTS_LAYER; }

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
        if(this.getType() == "PaperBin")
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.blue_paper_recyclingbin);
        else if(this.getType() == "PlasticBin")
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_green_recyclingbin);
        else if(this.getType() == "MetalBin")
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_red_recyclingbin);
        else if(this.getType() == "OthersBin")
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.generalwaste_greyrecyclingbin);
        xPos = 900.0f;
        yPos = 1000.0f;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {}

    @Override
    public void Render(Canvas _canvas) { _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null); }

    public static BinEntity Create(String _Type)
    {
        BinEntity result = new BinEntity();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
