package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class SampleEntity implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, xDir, yDir, lifeTime;

    // Collidable interface
    @Override
    public String getType() { return "SampleEntity"; }

    @Override
    public float getPosX() { return xPos; }

    @Override
    public float getPosY() { return yPos; }

    @Override
    public float getRadius() { return bmp.getHeight() * 0.5f; }

    @Override
    public void onHit(Collidable _other)
    {
        if(_other.getType() == "SampleEntity")
            setIsDone(true);
    }

    // EntityBase interface
    @Override
    public boolean isDone() { return isDone; }

    @Override
    public void setIsDone(boolean _isDone) { isDone = _isDone; }

    @Override
    public void Init(SurfaceView _view)
    {
        bmp = BitmapFactory.decodeResource(_view.getResources(),R.mipmap.ic_launcher_round);
        lifeTime = 30.0f;

        // Random class
        Random ranGen = new Random();
        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = ranGen.nextFloat() * _view.getHeight();
        xDir = ranGen.nextFloat() * 100.0f - 50.0f;
        yDir = ranGen.nextFloat() * 100.0f - 50.0f;
    }

    @Override
    public void Update(float _dt)
    {
        lifeTime -= _dt;
        if(lifeTime < 0.0f)
            setIsDone(true);
        xPos += xDir * _dt;
        yPos += yDir * _dt;

        if(TouchManager.Instance.isDown())
        {
            float imgRadius = bmp.getHeight() * 0.5f;

            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, xPos, yPos, imgRadius))
                setIsDone(true);
        }
    }

    @Override
    public void Render(Canvas _canvas) { _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null); }

    public static SampleEntity Create()
    {
        SampleEntity result = new SampleEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
