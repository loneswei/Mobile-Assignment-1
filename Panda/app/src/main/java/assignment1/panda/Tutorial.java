package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class Tutorial
{
    public final static Tutorial Instance = new Tutorial();

    private SurfaceView view = null;

    // All rubbish tutorial bitmaps
    Bitmap crumpledPaperTutorial = null;
    Bitmap newsPaperTutorial = null;
    Bitmap milkCartonTutorial = null;

    Bitmap plasticBagTutorial = null;
    Bitmap plasticBottleTutorial = null;
    Bitmap plasticSprayBottleTutorial = null;

    Bitmap metalDrinkCanTutorial = null;
    Bitmap metalFoodCanTutorial = null;
    Bitmap metalSprayCanTutorial = null;

    Bitmap bananaPeelTutorial = null;
    Bitmap eatenAppleTutorial = null;
    Bitmap toothBrushTutorial = null;

    // Booleans for checking first occurrence of each rubbish
    private boolean firstCrumpledPaper = true;
    private boolean firstNewsPaper = true;
    private boolean firstMilkCarton = true;

    private boolean firstPlasticBag = true;
    private boolean firstPlasticBottle = true;
    private boolean firstPlasticSprayBottle = true;

    private boolean firstMetalDrinkCan = true;
    private boolean firstMetalFoodCan = true;
    private boolean firstMetalSprayCan = true;

    private boolean firstBananaPeel = true;
    private boolean firstEatenApple = true;
    private boolean firstToothBrush = true;

    // Booleans for Tutorial rendering of each rubbish
    private boolean teachCrumpledPaper = false;
    private boolean teachNewsPaper = false;
    private boolean teachMilkCarton = false;

    private boolean teachPlasticBag = false;
    private boolean teachPlasticBottle = false;
    private boolean teachPlasticSprayBottle = false;

    private boolean teachMetalDrinkCan = false;
    private boolean teachMetalFoodCan = false;
    private boolean teachMetalSprayCan = false;

    private boolean teachBananaPeel = false;
    private boolean teachEatenApple = false;
    private boolean teachToothBrush = false;

    private Tutorial() {}

    public void Init(SurfaceView _view)
    {
        view = _view;

        // Tutorial image for each rubbish
        // Paper
        crumpledPaperTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachcrumpledpaper);
        newsPaperTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachnewspaper);
        milkCartonTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachmilkcarton);

        // Plastic
        plasticBagTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachplasticbag);
        plasticBottleTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachplasticbottle);
        plasticSprayBottleTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachplasticspraybottle);

        // Metal
        metalDrinkCanTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachmetaldrinkcan);
        metalFoodCanTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachmetalfoodcan);
        metalSprayCanTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachmetalspraycan);

        // Others
        bananaPeelTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachbananapeel);
        eatenAppleTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teacheatenapple);
        toothBrushTutorial = BitmapFactory.decodeResource(_view.getResources(), R.drawable.teachtoothbrush);
    }

    public void Update()
    {
        if(TouchManager.Instance.isDown())
        {
            Game.Instance.setIsPaused(false);

            // Reset teach to stop rendering tutorial bmp
            // Set first to false as it is taught to the player
            // Paper
            if (teachCrumpledPaper)
            {
                teachCrumpledPaper = false;
                firstCrumpledPaper = false;
            }
            else if(teachNewsPaper)
            {
                teachNewsPaper = false;
                firstNewsPaper = false;
            }
            else if(teachMilkCarton)
            {
                teachMilkCarton = false;
                firstMilkCarton = false;
            }

            // Plastic
            else if(teachPlasticBag)
            {
                teachPlasticBag = false;
                firstPlasticBag = false;
            }
            else if(teachPlasticBottle)
            {
                teachPlasticBottle = false;
                firstPlasticBottle = false;
            }
            else if(teachPlasticSprayBottle)
            {
                teachPlasticSprayBottle = false;
                firstPlasticSprayBottle = false;
            }

            // Metal
            else if(teachMetalDrinkCan)
            {
                teachMetalDrinkCan = false;
                firstMetalDrinkCan = false;
            }
            else if(teachMetalFoodCan)
            {
                teachMetalFoodCan = false;
                firstMetalFoodCan = false;
            }
            else if(teachMetalSprayCan)
            {
                teachMetalSprayCan = false;
                firstMetalSprayCan = false;
            }

            // Others
            else if(teachBananaPeel)
            {
                teachBananaPeel = false;
                firstBananaPeel = false;
            }
            else if(teachEatenApple)
            {
                teachEatenApple = false;
                firstEatenApple = false;
            }
            else if(teachToothBrush)
            {
                teachToothBrush = false;
                firstToothBrush = false;
            }
        }
    }
    public void Render(Canvas _canvas, float xPos, float yPos)
    {
        // Paper
        if(teachCrumpledPaper)
            _canvas.drawBitmap(crumpledPaperTutorial, xPos - crumpledPaperTutorial.getWidth() * 0.5f, yPos - crumpledPaperTutorial.getHeight() * 0.5f, null);
        else if(teachNewsPaper)
            _canvas.drawBitmap(newsPaperTutorial, xPos - newsPaperTutorial.getWidth() * 0.5f, yPos - newsPaperTutorial.getHeight() * 0.5f, null);
        else if(teachMilkCarton)
            _canvas.drawBitmap(milkCartonTutorial, xPos - milkCartonTutorial.getWidth() * 0.5f, yPos - milkCartonTutorial.getHeight() * 0.5f, null);

        // Plastic
        else if(teachPlasticBag)
            _canvas.drawBitmap(plasticBagTutorial, xPos - plasticBagTutorial.getWidth() * 0.5f, yPos - plasticBagTutorial.getHeight() * 0.5f, null);
        else if(teachPlasticBottle)
            _canvas.drawBitmap(plasticBottleTutorial, xPos - plasticBottleTutorial.getWidth() * 0.5f, yPos - plasticBottleTutorial.getHeight() * 0.5f, null);
        else if(teachPlasticSprayBottle)
            _canvas.drawBitmap(plasticSprayBottleTutorial, xPos - plasticSprayBottleTutorial.getWidth() * 0.5f, yPos - plasticSprayBottleTutorial.getHeight() * 0.5f, null);

        // Metal
        else if(teachMetalDrinkCan)
            _canvas.drawBitmap(metalDrinkCanTutorial, xPos - metalDrinkCanTutorial.getWidth() * 0.5f, yPos - metalDrinkCanTutorial.getHeight() * 0.5f, null);
        else if(teachMetalFoodCan)
            _canvas.drawBitmap(metalFoodCanTutorial, xPos - metalFoodCanTutorial.getWidth() * 0.5f, yPos - metalFoodCanTutorial.getHeight() * 0.5f, null);
        else if(teachMetalSprayCan)
            _canvas.drawBitmap(metalSprayCanTutorial, xPos - metalSprayCanTutorial.getWidth() * 0.5f, yPos - metalSprayCanTutorial.getHeight() * 0.5f, null);

        // Others
        else if(teachBananaPeel)
            _canvas.drawBitmap(bananaPeelTutorial, xPos - bananaPeelTutorial.getWidth() * 0.5f, yPos - bananaPeelTutorial.getHeight() * 0.5f, null);
        else if(teachEatenApple)
            _canvas.drawBitmap(eatenAppleTutorial, xPos - eatenAppleTutorial.getWidth() * 0.5f, yPos - eatenAppleTutorial.getHeight() * 0.5f, null);
        else if(teachToothBrush)
            _canvas.drawBitmap(toothBrushTutorial, xPos - toothBrushTutorial.getWidth() * 0.5f, yPos - toothBrushTutorial.getHeight() * 0.5f, null);
    }
    //-------------------------------------------------------------------------------------------------------------------

    public boolean IsFirstCrumpledPaper() { return firstCrumpledPaper; }
    public boolean IsFirstNewsPaper() { return firstNewsPaper; }
    public boolean IsFirstMilkCarton() { return firstMilkCarton; }

    public boolean IsFirstPlasticBag() { return firstPlasticBag; }
    public boolean IsFirstPlasticBottle() { return firstPlasticBottle; }
    public boolean IsFirstPlasticSprayBottle() { return firstPlasticSprayBottle; }

    public boolean IsFirstMetalDrinkCan() { return firstMetalDrinkCan; }
    public boolean IsFirstMetalFoodCan() { return firstMetalFoodCan; }
    public boolean IsFirstMetalSprayCan() { return firstMetalSprayCan; }

    public boolean IsFirstBananaPeel() { return firstBananaPeel; }
    public boolean IsFirstEatenApple() { return firstEatenApple; }
    public boolean IsFirstToothBrush() { return firstToothBrush; }

    //-------------------------------------------------------------------------------------------------------------------

    public boolean IsTeachCrumpledPaper() { return teachCrumpledPaper; }
    public void SetTeachCrumpledPaper(boolean _teachCrumpledPaper) { teachCrumpledPaper = _teachCrumpledPaper; }
    public boolean IsTeachNewsPaper() { return teachNewsPaper; }
    public void SetTeachNewsPaper(boolean _teachNewsPaper) { teachNewsPaper = _teachNewsPaper; }
    public boolean IsTeachMilkCarton() { return teachMilkCarton; }
    public void SetTeachMilkCarton(boolean _teachMilkCarton) { teachMilkCarton = _teachMilkCarton; }

    public boolean IsTeachPlasticBag() { return teachPlasticBag; }
    public void SetTeachPlasticBag(boolean _teachPlasticBag) { teachPlasticBag = _teachPlasticBag; }
    public boolean IsTeachPlasticBottle() { return teachPlasticBottle; }
    public void SetTeachPlasticBottle(boolean _teachPlasticBottle) { teachPlasticBottle = _teachPlasticBottle; }
    public boolean IsTeachPlasticSprayBottle() { return teachPlasticSprayBottle; }
    public void SetTeachPlasticSprayBottle(boolean _teachPlasticSprayBottle) { teachPlasticSprayBottle = _teachPlasticSprayBottle; }

    public boolean IsTeachMetalDrinkCan() { return teachMetalDrinkCan; }
    public void SetTeachMetalDrinkCan(boolean _teachMetalDrinkCan) { teachMetalDrinkCan = _teachMetalDrinkCan; }
    public boolean IsTeachMetalFoodCan() { return teachMetalFoodCan; }
    public void SetTeachMetalFoodCan(boolean _teachMetalFoodCan) { teachMetalFoodCan = _teachMetalFoodCan; }
    public boolean IsTeachMetalSprayCan() { return teachMetalSprayCan; }
    public void SetTeachMetalSprayCan(boolean _teachMetalSprayCan) { teachMetalSprayCan = _teachMetalSprayCan; }

    public boolean IsTeachBananaPeel() { return teachBananaPeel; }
    public void SetTeachBananaPeel(boolean _teachBananaPeel) { teachBananaPeel = _teachBananaPeel; }
    public boolean IsTeachEatenApple() { return teachEatenApple; }
    public void SetTeachEatenApple(boolean _teachEatenApple) { teachEatenApple = _teachEatenApple; }
    public boolean IsTeachToothBrush() { return teachToothBrush; }
    public void SetTeachToothBrush(boolean _teachToothBrush) { teachToothBrush = _teachToothBrush; }
}
