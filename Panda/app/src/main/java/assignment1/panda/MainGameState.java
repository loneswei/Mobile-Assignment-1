package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

// This is done by Wong Shih Wei and Goh Liang Li.
public class MainGameState implements StateBase
{
    private float timer = 0.0f;
    private float spawnDelay = 1.0f;
    private Bitmap bmpPaperBin = null;
    private Bitmap bmpPlasticBin = null;
    private Bitmap bmpMetalBin = null;
    private Bitmap bmpOthersBin = null;
    private Bitmap bmpPause = null;

    private int selectedLevel = 0;
    private int numOfRubbish = 0;
    private int maxNumOfRubbish = 0;
    private SpriteAnimation spr = null;
    private int ScreenWidth, ScreenHeight;
    private int health = 3;

    @Override
    public String GetName() { return "MainGame"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        GameBackground.Create("BackGround");
        ButtonEntity.Create("PaperButton");
        ButtonEntity.Create("PlasticButton");
        ButtonEntity.Create("MetalButton");
        ButtonEntity.Create("OthersButton");
        ButtonEntity.Create("PauseButton");

        GameSystem.Instance.SetIsPaused(false);

        bmpPaperBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.blue_paper_recyclingbin);
        bmpPlasticBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_green_recyclingbin);
        bmpMetalBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_red_recyclingbin);
        bmpOthersBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.generalwaste_greyrecyclingbin);
        bmpPause = BitmapFactory.decodeResource(_view.getResources(), R.drawable.pausebutton);

        selectedLevel = LevelManager.Instance.GetSelectedLevel();
        spawnDelay = 4.0f;
        maxNumOfRubbish = 12;
        GameSystem.Instance.currNumOfRubbish = 0;

        // Player Health
        PlayerHealth.Create("Heart3");
        PlayerHealth.Create("Heart2");
        PlayerHealth.Create("Heart1");
        PlayerHealth.Instance.setHP(health);

        if(selectedLevel == 2 || selectedLevel == 4 || selectedLevel == 6 || selectedLevel == 8)
        {
            spawnDelay = 2.0f;
            maxNumOfRubbish = 20;
        }

        /*
        This part is for creating a sprite for animation
        Parameters: bitmap, row, col, fps
         */
        spr = new SpriteAnimation(BitmapFactory.decodeResource(_view.getResources(), R.drawable.starsprite), 1, 3, 20);
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
    }


    @Override
    public void OnExit()
    {
        // Clear the EntityList, and reset variables
        EntityManager.Instance.getEntityList().clear();
        numOfRubbish = 0;
        GameSystem.Instance.currNumOfRubbish = 0;
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

            // Rubbish Creation at regular interval
            timer += _dt;
            // Create rubbish until numOfRubbish reaches the Pre-Set limit.
            if (numOfRubbish <= maxNumOfRubbish && timer > spawnDelay)
            {
                numOfRubbish += 1;
                GameSystem.Instance.currNumOfRubbish += 1;
                Random ranGen = new Random();
                int rubbishType = 0;

                // Spawn Rubbish Type according to selected level.
                switch(selectedLevel)
                {
                    // Tutorial
                    case 1:
                        rubbishType = 1;    // Paper
                        break;
                    case 3:
                        rubbishType = 2;    // Plastic
                        break;
                    case 5:
                        rubbishType = 3;    // Metal
                        break;
                    case 7:
                        rubbishType = 4;    // Others
                        break;

                    // Non - Tutorial
                    case 2:
                        rubbishType = 1;
                        break;
                    case 4:
                        rubbishType = ranGen.nextInt(2) + 1;    // Paper and Plastic
                        break;
                    case 6:
                        rubbishType = ranGen.nextInt(3) + 1;    // Paper, Plastic and Metal
                        break;
                    case 8:
                        rubbishType = ranGen.nextInt(4) + 1;    // Everything
                        break;
                }

                // Create rubbish based on its type
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

            // Wnning Condition
            // When finish recycling all the rubbbish
            if(PlayerHealth.Instance.getHP() > 0 && numOfRubbish != 0 && GameSystem.Instance.currNumOfRubbish <= 0)
            {
                // Go to Win Screen
                StateManager.Instance.ChangeState("Win");
            }

            // Losing Condition
            // If Health is 0, go to Lose Screen
            if(PlayerHealth.Instance.getHP() <= 0)
            {
                StateManager.Instance.ChangeState("Lose");
            }
        }

        if (TouchManager.Instance.isDown())
        {
            float imgRadius = bmpPaperBin.getHeight() * 0.5f;
            float imgRadius2 = bmpPlasticBin.getHeight() * 0.5f;
            float imgRadius3 = bmpMetalBin.getHeight() * 0.5f;
            float imgRadius4 = bmpOthersBin.getHeight() * 0.5f;
            float imgRadius5 = bmpPause.getHeight() * 0.5f;

            if (!GameSystem.Instance.GetIsPaused())
            {
                if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.2f, ScreenHeight * 0.925f, imgRadius))
                {
                    BinEntity.Create("PaperBin");

                    AudioManager.Instance.PlayAudio(R.raw.paperbin);
                }
                else if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.35f, ScreenHeight * 0.925f, imgRadius2))
                {
                    BinEntity.Create("PlasticBin");

                    AudioManager.Instance.PlayAudio(R.raw.plasticbin);
                }
                else if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.65f, ScreenHeight * 0.925f, imgRadius3))
                {
                    BinEntity.Create("MetalBin");

                    AudioManager.Instance.PlayAudio(R.raw.metalbin);
                }
                else if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.8f, ScreenHeight * 0.925f, imgRadius4))
                {
                    BinEntity.Create("OthersBin");

                    AudioManager.Instance.PlayAudio(R.raw.generalwastebin);
                }
                // Press Pause Button
                else if (Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.025f, ScreenHeight * 0.04f, imgRadius5))
                {
                    AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                    GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());

                    if (PauseConfirmDialogFragment.IsShown)
                        return;

                    PauseConfirmDialogFragment newPauseConfirmation = new PauseConfirmDialogFragment();
                    newPauseConfirmation.show(GameSystem.Instance.gameActivity.getFragmentManager(), "PauseConfirm");

                }
            }
            // Press Pause Button
            else
            {
                if(selectedLevel == 1 || selectedLevel == 3 || selectedLevel == 5 || selectedLevel == 7)
                    Tutorial.Instance.Update();
                if (!Tutorial.Instance.isTeaching && Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.025f, ScreenHeight * 0.04f, imgRadius5))
                {
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

        if(selectedLevel == 1 || selectedLevel == 3 || selectedLevel == 5 || selectedLevel == 7)
            Tutorial.Instance.Render(_canvas, ScreenWidth * 0.58f, ScreenHeight * 0.4f);
        /*
         Render the sprite here, else other stuffs will cover it
         Parameters: canvas, x position, y position
        */
        if(GameSystem.Instance.GetIsShowSprite())
            spr.Render(_canvas, (int)(ScreenWidth * 0.55f), (int)(ScreenHeight * 0.775f));
    }
}
