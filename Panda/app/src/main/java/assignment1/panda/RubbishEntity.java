package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

// This is done by Wong Shih Wei and Goh Liang Li.
public class RubbishEntity implements EntityBase, Collidable
{
    private boolean isDone = false;
    private boolean isInit = false;
    private float xPos, yPos, xDir, yDir, lifeTime;
    private int selectedLevel;
    private int audioSFX_speechbubble = R.raw.speechbubblesound;
    private int health;
    private int ScreenWidth, ScreenHeight;
    private String Type;

    private Bitmap bmp = null;
    private Bitmap scaledbmp = null;
    private Vibrator vibrator;


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
        }
        else
        {
            if((this.getType().equals("Paper")  || otherEntity.getType().equals("PaperBin")) ||
                    (this.getType().equals("Plastic") || otherEntity.getType().equals("PlasticBin")) ||
                    (this.getType().equals("Metal") || otherEntity.getType().equals("MetalBin")) ||
                    (this.getType().equals("Others") || otherEntity.getType().equals("OthersBin")))
            {
                setIsDone(true);
                otherEntity.setIsDone(true);

                for(EntityBase currEntity : EntityManager.Instance.getEntityList())
                {
                    if(currEntity.getType().equals("Heart3") || currEntity.getType().equals("Heart2") ||
                            currEntity.getType().equals("Heart1"))
                    {
                        currEntity.setIsDone(true);
                        break;
                    }
                }

                startVibrate();
            }

            health = PlayerHealth.Instance.getHP();
            health -=1;
            PlayerHealth.Instance.setHP(health);
        }
        GameSystem.Instance.currNumOfRubbish -= 1;
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
        vibrator = (Vibrator) _view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);

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
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        scaledbmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth(), bmp.getHeight(),true);

        lifeTime = 30.0f;
        xPos = ScreenWidth * 0.43f;
        yPos = ScreenHeight * 0.3f;
        xDir = 0.0f;
        yDir = 300.0f;

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
        // First movement : LEFT
        if(yPos <= ScreenHeight * 0.3f && xPos >= ScreenWidth * 0.08f)
        {
            xDir = 300.0f;
            xPos -= xDir * _dt;
        }
        // Second movement : DOWN
        else if(xPos < ScreenWidth * 0.08f && yPos <= ScreenHeight * 0.55f)
        {
            yPos += yDir * _dt;

            // Check position for tutorial
            if((selectedLevel == 1 || selectedLevel == 3 || selectedLevel == 5 || selectedLevel == 7) && yPos > ScreenHeight * 0.55f)
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
        // Third movement : RIGHT
        else if(yPos > ScreenHeight * 0.55f && xPos <= ScreenWidth * 0.5f)
            xPos += xDir * _dt;
        // Fourth movement : DOWN - Reach bin at xPos > 800.0f && yPos > 800.0f
        else if(xPos > ScreenWidth * 0.5f && yPos <= ScreenHeight)
            yPos += yDir * _dt;

        // Check if Rubbish is still there but no bins were pressed.
        if(yPos >= ScreenHeight)
        {
            setIsDone(true);

            for(EntityBase currEntity : EntityManager.Instance.getEntityList())
            {
                if(currEntity.getType().equals("Heart3") || currEntity.getType().equals("Heart2") ||
                        currEntity.getType().equals("Heart1"))
                {
                    currEntity.setIsDone(true);
                    break;
                }
            }

            health = PlayerHealth.Instance.getHP();
            health -=1;
            PlayerHealth.Instance.setHP(health);
            GameSystem.Instance.currNumOfRubbish -= 1;

            startVibrate();
        }
    }

    @Override
    public void Render(Canvas _canvas) { _canvas.drawBitmap(scaledbmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null); }

    private void startVibrate()
    {
        // If API version is higher or equal to 26 then vibrate.
        if(Build.VERSION.SDK_INT >= 26)
            vibrator.vibrate(VibrationEffect.createOneShot(150,10));
        else
        {
            long pattern[] = {0, 50, 0};
            vibrator.vibrate(pattern, -1);
        }
    }

    public static RubbishEntity Create(String _Type)
    {
        RubbishEntity result = new RubbishEntity();
        result.setType(_Type);
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
