package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

// This is done by Goh Liang Li.
public class LevelSelectState implements StateBase
{
    private int ScreenWidth, ScreenHeight;
    private Bitmap scaledbmp = null;

    // Background
    private Bitmap bmp = null;
    // Level 1 Button
    private Bitmap bmpLevel1Button = null;
    // Level 2 Button
    private Bitmap bmpLevel2Button = null;
    // Level 3 Button
    private Bitmap bmpLevel3Button = null;
    // Level 4 Button
    private Bitmap bmpLevel4Button = null;
    // Level 5 Button
    private Bitmap bmpLevel5Button = null;
    // Level 6 Button
    private Bitmap bmpLevel6Button = null;
    // Level 7 Button
    private Bitmap bmpLevel7Button = null;
    // Level 8 Button
    private Bitmap bmpLevel8Button = null;
    // Back Button
    private Bitmap bmpBackButton = null;

    @Override
    public String GetName() { return "LevelSelect"; }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        // Level Select Background
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level_select);

        // Level 1 Button
        bmpLevel1Button = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level1button);
        // Level 2 Button
        bmpLevel2Button = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level2button);
        // Level 3 Button
        bmpLevel3Button = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level3button);
        // Level 4 Button
        bmpLevel4Button = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level4button);
        // Level 5 Button
        bmpLevel5Button = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level5button);
        // Level 6 Button
        bmpLevel6Button = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level6button);
        // Level 7 Button
        bmpLevel7Button = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level7button);
        // Level 8 Button
        bmpLevel8Button = BitmapFactory.decodeResource(_view.getResources(), R.drawable.level8button);

        // Back Button
        bmpBackButton = BitmapFactory.decodeResource(_view.getResources(), R.drawable.back);

        // Display Metrics
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp, ScreenWidth, ScreenHeight,true);
    }

    @Override
    public void OnExit() { }

    @Override
    public void Update(float _dt)
    {
        float imgRadius1 = bmpLevel1Button.getWidth() * 0.5f;
        float imgRadius2 = bmpLevel2Button.getWidth() * 0.5f;
        float imgRadius3 = bmpLevel3Button.getWidth() * 0.5f;
        float imgRadius4 = bmpLevel4Button.getWidth() * 0.5f;
        float imgRadius5 = bmpLevel5Button.getWidth() * 0.5f;
        float imgRadius6 = bmpLevel6Button.getWidth() * 0.5f;
        float imgRadius7 = bmpLevel7Button.getWidth() * 0.5f;
        float imgRadius8 = bmpLevel8Button.getWidth() * 0.5f;

        float imgBack = bmpBackButton.getWidth() * 0.5f;

        // Checking if there's any touch on any buttons
        if(TouchManager.Instance.isDown())
        {
            // Level 1 Button.
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.19f, ScreenHeight * 0.45f, imgRadius1))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                // Tutorial Level 1, Paper.
                LevelManager.Instance.SetSelectedLevel(1);
                StateManager.Instance.ChangeState("MainGame");
            }
            // Level 2 Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.39f, ScreenHeight * 0.45f, imgRadius2))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                LevelManager.Instance.SetSelectedLevel(2);
                StateManager.Instance.ChangeState("MainGame");
            }
            // Level 3 Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.59f, ScreenHeight * 0.45f, imgRadius3))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                // Tutorial Level 3, Plastic.
                LevelManager.Instance.SetSelectedLevel(3);
                StateManager.Instance.ChangeState("MainGame");
            }
            // Level 4 Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.79f, ScreenHeight * 0.45f, imgRadius4))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                LevelManager.Instance.SetSelectedLevel(4);
                StateManager.Instance.ChangeState("MainGame");
            }
            // Level 5 Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.19f, ScreenHeight * 0.65f, imgRadius5))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                // Tutorial Level 5, Metal.
                LevelManager.Instance.SetSelectedLevel(5);
                StateManager.Instance.ChangeState("MainGame");
            }
            // Level 6 Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.39f, ScreenHeight * 0.65f, imgRadius6))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                LevelManager.Instance.SetSelectedLevel(6);
                StateManager.Instance.ChangeState("MainGame");
            }
            // Level 7 Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.59f, ScreenHeight * 0.65f, imgRadius7))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                // Tutorial Level 7, others.
                LevelManager.Instance.SetSelectedLevel(7);
                StateManager.Instance.ChangeState("MainGame");
            }
            // Level 8 Button
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.79f, ScreenHeight * 0.65f, imgRadius8))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                LevelManager.Instance.SetSelectedLevel(8);
                StateManager.Instance.ChangeState("MainGame");
            }

            // Back Button.
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, ScreenWidth * 0.04f, ScreenHeight * 0.05f, imgBack))
            {
                AudioManager.Instance.PlayAudio(R.raw.outsidegameplaysfx);
                StateManager.Instance.ChangeState("MainMenu");
            }
        }

    }

    @Override
    public void Render(Canvas _canvas)
    {
        // Background
        _canvas.drawBitmap(scaledbmp,0.0f,0.0f,null);

        // Level 1 Button
        Matrix buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.15f, ScreenHeight * 0.4f);
        _canvas.drawBitmap(bmpLevel1Button, buttonsTransform, null);

        // Level 2 Button
        buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.35f, ScreenHeight * 0.4f);
        _canvas.drawBitmap(bmpLevel2Button, buttonsTransform, null);

        // Level 3 Button
        buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.55f, ScreenHeight * 0.4f);
        _canvas.drawBitmap(bmpLevel3Button, buttonsTransform, null);

        // Level 4 Button
        buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.75f, ScreenHeight * 0.4f);
        _canvas.drawBitmap(bmpLevel4Button, buttonsTransform, null);

        // Level 5 Button
        buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.15f, ScreenHeight * 0.6f);
        _canvas.drawBitmap(bmpLevel5Button, buttonsTransform, null);

        // Level 6 Button
        buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.35f, ScreenHeight * 0.6f);
        _canvas.drawBitmap(bmpLevel6Button, buttonsTransform, null);

        // Level 7 Button
        buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.55f, ScreenHeight * 0.6f);
        _canvas.drawBitmap(bmpLevel7Button, buttonsTransform, null);

        // Level 8 Button
        buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.75f, ScreenHeight * 0.6f);
        _canvas.drawBitmap(bmpLevel8Button, buttonsTransform, null);

        // Back Button
        buttonsTransform = new Matrix();
        buttonsTransform.setTranslate(ScreenWidth * 0.f, ScreenHeight * 0.0f);
        _canvas.drawBitmap(bmpBackButton, buttonsTransform, null);
    }
}
