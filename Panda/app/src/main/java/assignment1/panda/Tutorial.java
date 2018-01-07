package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class Tutorial
{
    public final static Tutorial Instance = new Tutorial();

    private SurfaceView view = null;

    Bitmap crumpledPaperTutorial = null;
    Bitmap plasticBottleTutorial = null;
    Bitmap eatenAppleTutorial = null;
    // Booleans for checking first occurrence of each rubbish
    private boolean firstCrumpledPaper = true;
    private boolean firstPlasticBottle = true;
    private boolean firstEatenApple = true;

    // Booleans for Tutorial rendering of each rubbish
    private boolean teachCrumpledPaper = false;
    private boolean teachPlasticBottle = false;
    private boolean teachEatenApple = false;

    private Tutorial() {}

    public void Init(SurfaceView _view)
    {
        view = _view;

        // Tutorial image for each rubbish
        crumpledPaperTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ic_launcher);
        plasticBottleTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ic_launcher_round);
        eatenAppleTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachapple);
    }

    public void Update()
    {
        if(TouchManager.Instance.isDown())
        {
            Game.Instance.setIsPaused(false);

            // Reset teach to stop rendering tutorial bmp
            // Set first to false as it is taught to the player
            if (teachCrumpledPaper)
            {
                teachCrumpledPaper = false;
                firstCrumpledPaper = false;
            }
            else if(teachPlasticBottle)
            {
                teachPlasticBottle = false;
                firstPlasticBottle = false;
            }
            else if(teachEatenApple)
            {
                teachEatenApple = false;
                firstEatenApple = false;
            }
        }
    }
    public void Render(Canvas _canvas, float xPos, float yPos)
    {
        if(teachCrumpledPaper)
            _canvas.drawBitmap(crumpledPaperTutorial, xPos - crumpledPaperTutorial.getWidth() * 0.5f, yPos - crumpledPaperTutorial.getHeight() * 0.5f, null);
        else if(teachPlasticBottle)
            _canvas.drawBitmap(plasticBottleTutorial, xPos - plasticBottleTutorial.getWidth() * 0.5f, yPos - plasticBottleTutorial.getHeight() * 0.5f, null);
        else if(teachEatenApple)
            _canvas.drawBitmap(eatenAppleTutorial, xPos - eatenAppleTutorial.getWidth() * 0.5f, yPos - eatenAppleTutorial.getHeight() * 0.5f, null);

    }
    //-------------------------------------------------------------------------------------------------------------------

    public boolean IsFirstCrumpledPaper() { return firstCrumpledPaper; }
    public boolean IsFirstPlasticBottle() { return firstPlasticBottle; }
    public boolean IsFirstEatenApple() { return firstEatenApple; }

    //-------------------------------------------------------------------------------------------------------------------

    public boolean IsTeachCrumpledPaper() { return teachCrumpledPaper; }
    public void SetTeachCrumpledPaper(boolean _teachCrumpledPaper) { teachCrumpledPaper = _teachCrumpledPaper; }
    public boolean IsTeachPlasticBottle() { return teachPlasticBottle; }
    public void SetTeachPlasticBottle(boolean _teachPlasticBottle) { teachPlasticBottle = _teachPlasticBottle; }
    public boolean IsTeachEatenApple() { return teachEatenApple; }
    public void SetTeachEatenApple(boolean _teachEatenApple) { teachEatenApple = _teachEatenApple; }
}
