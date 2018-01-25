package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.SurfaceView;

import java.util.Random;

public class MainGameState implements StateBase
{
    private float timer = 0.0f;
    private float spawnDelay = 1.0f;
    private Bitmap bmpPaperBin = null;
    private Bitmap bmpPlasticBin = null;
    private Bitmap bmpMetalBin = null;
    private Bitmap bmpOthersBin = null;
    private Bitmap bmpBack = null;
    private Vibrator vibrator;

    private int selectedLevel = 0;
    private SpriteAnimation spr = null;

    @Override
    public String GetName() { return "MainGame"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        selectedLevel = LevelManager.Instance.GetSelectedLevel();
        GameBackground.Create("BackGround");
        ButtonEntity.Create("PaperButton");
        ButtonEntity.Create("PlasticButton");
        spawnDelay = 4.0f;

        if(selectedLevel == 2)
        {
            ButtonEntity.Create("MetalButton");
            ButtonEntity.Create("OthersButton");
            bmpMetalBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_red_recyclingbin);
            bmpOthersBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.generalwaste_greyrecyclingbin);
            spawnDelay = 2.0f;
        }
        ButtonEntity.Create("BackButton");
        PlayerHealth.Create("Heart");
        bmpPaperBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.blue_paper_recyclingbin);
        bmpPlasticBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_green_recyclingbin);
        bmpBack = BitmapFactory.decodeResource(_view.getResources(), R.drawable.back);

        vibrator = (Vibrator) _view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);
        //AudioManager.Instance.PlayAudio(R.raw.background_music);

        /*
        This part is for creating a sprite for animation
        Parameters: bitmap, row, col, fps
         */
        spr = new SpriteAnimation(BitmapFactory.decodeResource(_view.getResources(), R.drawable.starsprite), 1, 3, 20);
    }

    public void startVibrate()
    {
        if(Build.VERSION.SDK_INT >= 26)
            vibrator.vibrate(VibrationEffect.createOneShot(150,10));
        else
        {
            long pattern[] = {0, 50, 0};
            vibrator.vibrate(pattern, -1);
        }
    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Update(float _dt)
    {
        if (!GameSystem.Instance.GetIsPaused())
        {
            // Update sprite's animation
            if(GameSystem.Instance.GetIsShowSprite())
            {
                AudioManager.Instance.PlayAudio(R.raw.stars);
                spr.Update(_dt);
            }

            // Rubbish Creation
            timer += _dt;

            if (timer > spawnDelay)
            {
                Random ranGen = new Random();
                int rubbishType = 0;// = ranGen.nextInt(4) + 1;

                if(selectedLevel == 1)
                    rubbishType = ranGen.nextInt(2) + 1;
                else if(selectedLevel == 2)
                    rubbishType = ranGen.nextInt(4) + 1;
                switch (rubbishType)
                {
                    case 1:
                        RubbishEntity.Create("Paper");
                        break;
                    case 2:
                        RubbishEntity.Create("Plastic");
                        break;
                    case 3:
                        RubbishEntity.Create("Metal");
                        break;
                    case 4:
                        RubbishEntity.Create("Others");
                        break;
                }
                timer = 0.0f;
            }
        }

        if (TouchManager.Instance.isDown())
        {
            float imgRadius = bmpPaperBin.getHeight() * 0.5f;
            float imgRadius2 = bmpPlasticBin.getHeight() * 0.5f;
            float imgRadius3 = bmpMetalBin.getHeight() * 0.5f;
            float imgRadius4 = bmpOthersBin.getHeight() * 0.5f;
            float imgRadius5 = bmpBack.getHeight() * 0.5f;

            if (!GameSystem.Instance.GetIsPaused())
            {
                if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 300.0f, 1000.0f, imgRadius))
                {
                    // Create bin
                    BinEntity.Create("PaperBin");
                    startVibrate();

                    AudioManager.Instance.PlayAudio(R.raw.paperbin);
                }
                else if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 600.0f, 1000.0f, imgRadius2))
                {
                    // Create bin
                    BinEntity.Create("PlasticBin");
                    startVibrate();

                    AudioManager.Instance.PlayAudio(R.raw.plasticbin);
                }
                else if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 1200.0f, 1000.0f, imgRadius3)) {
                    // Create bin
                    BinEntity.Create("MetalBin");
                    startVibrate();

                    AudioManager.Instance.PlayAudio(R.raw.metalbin);
                }
                else if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 1500.0f, 1000.0f, imgRadius4))
                {
                    // Create bin
                    BinEntity.Create("OthersBin");
                    startVibrate();

                    AudioManager.Instance.PlayAudio(R.raw.generalwastebin);
                }
                else if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 45.0f, 45.0f, imgRadius5))
                {
//                    gameActivity.switchScreen();
                    //GameSystem.Instance.SetIsPaused(true);

                    if (PauseConfirmDialogFragment.IsShown)
                        return;

                    PauseConfirmDialogFragment newPauseConfirmation = new PauseConfirmDialogFragment();
                    newPauseConfirmation.show(GameSystem.Instance.gameActivity.getFragmentManager(), "PauseConfirm");

                    AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                }
            }
            else
            {
                Tutorial.Instance.Update();
                if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 45.0f, 45.0f, imgRadius5))
                {
                    //gameActivity.switchScreen();
                    //GameSystem.Instance.SetIsPaused(false);

                    if(PauseConfirmDialogFragment.IsShown)
                        return;

                    PauseConfirmDialogFragment newPauseConfirmation = new PauseConfirmDialogFragment();
                    newPauseConfirmation.show(GameSystem.Instance.gameActivity.getFragmentManager(), "PauseConfirm");

                    AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                }
            }
        }
        EntityManager.Instance.Update(_dt);
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

        Tutorial.Instance.Render(_canvas, 1050, 400);
        /*
         Render the sprite here, else other stuffs will cover it
         Parameters: canvas, x position, y position
        */
        if(GameSystem.Instance.GetIsShowSprite())
            spr.Render(_canvas, 950, 850);

        String scoreText = String.format("SCORE : %d", GameSystem.Instance.GetIntFromSave("Score"));
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(64);
        _canvas.drawText(scoreText, 10, 220,paint);
    }
}
