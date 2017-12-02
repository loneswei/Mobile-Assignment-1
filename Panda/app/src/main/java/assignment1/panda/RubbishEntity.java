package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class RubbishEntity implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, xDir, yDir, lifeTime, bottomYPos;
    private String Type;

    // Collidable interface
    @Override
    public String getType() { return Type; }

    @Override
    public void setType(String _Type) { Type = _Type; }

    @Override
    public float getPosX() { return xPos; }

    @Override
    public float getPosY() { return yPos; }

    @Override
    public float getRadius() { return bmp.getHeight() * 0.5f; }

    @Override
    public void onHit(Collidable _other)
    {
        if((this.getType() == "Paper" && _other.getType() == "PaperBin") ||
                (this.getType() == "Plastic" && _other.getType() == "PlasticBin") ||
                (this.getType() == "Metal" && _other.getType() == "MetalBin") ||
                (this.getType() == "Others" && _other.getType() == "OthersBin"))
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
        Random ranGen = new Random();
        // Check for 4 different rubbish type and assign respective image to bmp
        if(this.getType() == "Paper")
        {
            int random = ranGen.nextInt(3) + 1;
            switch(random)
            {
                case 1:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.crumpled_paper);
                    break;
                case 2:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.newspaper);
                    break;
                case 3:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.milk_carton);
                    break;
            }
        }
        else if(this.getType() == "Plastic")
        {
            int random = ranGen.nextInt(3) + 1;
            switch(random)
            {
                case 1:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_bag);
                    break;
                case 2:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_bottle);
                    break;
                case 3:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_spray_bottle);
                    break;
            }
        }
        else if(this.getType() == "Metal")
        {
            int random = ranGen.nextInt(3) + 1;
            switch(random)
            {
                case 1:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_drink_can);
                    break;
                case 2:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_food_can);
                    break;
                case 3:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_spray_can);
                    break;
            }
        }
        else if(this.getType() == "Others")
        {
            int random = ranGen.nextInt(3) + 1;
            switch(random)
            {
                case 1:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.banana_peel);
                    break;
                case 2:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.eaten_apple);
                    break;
                case 3:
                    bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.toothbrush);
                    break;
            }
        }

        lifeTime = 30.0f;
        xPos = 600.0f;
        yPos = 10.0f;
        xDir = 0.0f;
        yDir = 300.0f;
        bottomYPos = _view.getHeight();
    }

    @Override
    public void Update(float _dt)
    {
        lifeTime -= _dt;
        if(lifeTime < 0.0f)
            setIsDone(true);

        // Movement of Rubbishes
        // First movement : DOWN
        if(yPos <= 400.0f)
            yPos += yDir * _dt;
        // Second movement : LEFT
        else if(yPos > 400.0f && yPos <= 700.0f && xPos >= 100.0f)
        {
            xDir = 300.0f;
            xPos -= xDir * _dt;
        }
        // Third movement : DOWN
        else if(xPos < 100.0f && yPos <= 700.0f)
            yPos += yDir * _dt;
        // Fourth movement : RIGHT
        else if(yPos > 700.0f && xPos <= 800.0f)
            xPos += xDir * _dt;
        // Fifth movement : DOWN - Reach bin at xPos > 800.0f && yPos > 800.0f
        else if(xPos > 800.0f && yPos <= bottomYPos)
            yPos += yDir * _dt;
    }

    @Override
    public void Render(Canvas _canvas) { _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null); }

    public static RubbishEntity Create(String _Type)
    {
        RubbishEntity result = new RubbishEntity();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
