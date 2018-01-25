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
    private int audioSFX_speechbubble = R.raw.speechbubblesound;

    /*
    1 -> Crumpled Paper, Plastic Bag, Metal Drink Can, Banana Peel
    2 -> Newspaper, Plastic Bottle, Metal Food Can, Eaten Apple
    3 -> Milk Carton, Plastic Spray Bottle, Metal Spray Can, ToothBrush
     */
    private int rubbishType = 0;

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
        {
            GameSystem.Instance.SetIsShowSprite(true);
            setIsDone(true);
            otherEntity.setIsDone(true);

            //Update score
            int currScore = GameSystem.Instance.GetIntFromSave("Score");
            ++currScore;
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("Score", currScore);
            GameSystem.Instance.SaveEditEnd();
        }
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
        rubbishType = ranGen.nextInt(3) + 1;
        switch (this.getType())
        {
            case "Paper":
            {
                switch (rubbishType)
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
                switch (rubbishType)
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
                switch (rubbishType)
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
                switch (rubbishType)
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
        if(GameSystem.Instance.GetIsPaused())
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
        {
            yPos += yDir * _dt;

            // Check position for tutorial
            if(selectedLevel == 1 && yPos > 600.0f)
            {
                switch(getType())
                {
                    case ("Paper"):
                    {
                        switch (rubbishType)
                        {
                            case 1:
                                if (Tutorial.Instance.IsFirstCrumpledPaper() && !Tutorial.Instance.IsTeachCrumpledPaper())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachCrumpledPaper(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                            case 2:
                                if (Tutorial.Instance.IsFirstNewsPaper() && !Tutorial.Instance.IsTeachNewsPaper())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachNewsPaper(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                            case 3:
                                if (Tutorial.Instance.IsFirstMilkCarton() && !Tutorial.Instance.IsTeachMilkCarton())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachMilkCarton(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                        }
                    }
                    break;
                    case ("Plastic"):
                    {
                        switch (rubbishType)
                        {
                            case 1:
                                if (Tutorial.Instance.IsFirstPlasticBag() && !Tutorial.Instance.IsTeachPlasticBag())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachPlasticBag(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                            case 2:
                                if (Tutorial.Instance.IsFirstPlasticBottle() && !Tutorial.Instance.IsTeachPlasticBottle())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachPlasticBottle(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                            case 3:
                                if (Tutorial.Instance.IsFirstPlasticSprayBottle() && !Tutorial.Instance.IsTeachPlasticSprayBottle())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachPlasticSprayBottle(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                        }
                    }
                    break;
                    case("Metal"):
                    {
                        switch(rubbishType)
                        {
                            case 1:
                                if (Tutorial.Instance.IsFirstMetalDrinkCan() && !Tutorial.Instance.IsTeachMetalDrinkCan())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachMetalDrinkCan(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                            case 2:
                                if (Tutorial.Instance.IsFirstMetalFoodCan() && !Tutorial.Instance.IsTeachMetalFoodCan())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachMetalFoodCan(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                            case 3:
                                if (Tutorial.Instance.IsFirstMetalSprayCan() && !Tutorial.Instance.IsTeachMetalSprayCan())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachMetalSprayCan(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                        }
                    }
                    break;
                    case("Others"):
                    {
                        switch(rubbishType)
                        {
                            case 1:
                                if (Tutorial.Instance.IsFirstBananaPeel() && !Tutorial.Instance.IsTeachBananaPeel())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachBananaPeel(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                            case 2:
                                if (Tutorial.Instance.IsFirstEatenApple() && !Tutorial.Instance.IsTeachEatenApple())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachEatenApple(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                            case 3:
                                if (Tutorial.Instance.IsFirstToothBrush() && !Tutorial.Instance.IsTeachToothBrush())
                                {
                                    AudioManager.Instance.PlayAudio(audioSFX_speechbubble);

                                    Tutorial.Instance.isTeaching = true;
                                    Tutorial.Instance.SetTeachToothBrush(true);
                                    GameSystem.Instance.SetIsPaused(true);
                                }
                                break;
                        }
                    }
                    break;
                }
            }
        }
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
