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
    private boolean isInit = false;
    private float xPos, yPos, xDir, yDir, lifeTime, bottomYPos;
    private String Type;
    private int selectedLevel;

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

        // Checking onHit between rubbish and bin
        if((this.getType().equals("Paper")  && otherEntity.getType().equals("PaperBin")) ||
                (this.getType().equals("Plastic") && otherEntity.getType().equals("PlasticBin")) ||
                (this.getType().equals("Metal") && otherEntity.getType().equals("MetalBin")) ||
                (this.getType().equals("Others") && otherEntity.getType().equals("OthersBin")))
            setIsDone(true);
        else
        {
            for(EntityBase currEntity : EntityManager.Instance.getEntityList())
            {
                if(currEntity.getType().equals("Heart"))
                {
                    currEntity.setIsDone(true);
                    break;
                }
            }
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
        Random ranGen = new Random();
        selectedLevel = LevelManager.Instance.GetSelectedLevel();
        // Check for 4 different rubbish type and assign respective image to bmp
        switch (this.getType())
        {
            case "Paper":
            {
                int random = ranGen.nextInt(3) + 1;
                switch (random)
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
                break;
            }
            case "Plastic":
            {
                int random = ranGen.nextInt(3) + 1;
                switch (random)
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
                break;
            }
            case "Metal":
            {
                int random = ranGen.nextInt(3) + 1;
                switch (random)
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
                break;
            }
            case "Others":
            {
                int random = ranGen.nextInt(3) + 1;
                switch (random)
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
                break;
            }
        }

        lifeTime = 30.0f;
        xPos = 750.0f;
        yPos = 250.0f;
        xDir = 0.0f;
        yDir = 300.0f;
        bottomYPos = _view.getHeight();

        isInit = true;
    }

    @Override
    public void Update(float _dt)
    {
        if(Game.Instance.getIsPaused())
            return;
        lifeTime -= _dt;
        if(lifeTime < 0.0f)
            setIsDone(true);

        // Movement of Rubbishes
        // First movement : DOWN
        if(yPos <= 300.0f)
            yPos += yDir * _dt;
        // Second movement : LEFT
        else if(yPos > 300.0f && yPos <= 600.0f && xPos >= 100.0f)
        {
            xDir = 300.0f;
            xPos -= xDir * _dt;
        }
        // Third movement : DOWN
        else if(xPos < 100.0f && yPos <= 600.0f)
            yPos += yDir * _dt;
        // Fourth movement : RIGHT
        else if(yPos > 600.0f && xPos <= 850.0f)
            xPos += xDir * _dt;
        // Fifth movement : DOWN - Reach bin at xPos > 800.0f && yPos > 800.0f
        else if(xPos > 850.0f && yPos <= bottomYPos)
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
