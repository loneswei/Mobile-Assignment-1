package assignment1.panda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

// This is done by Wong Shih Wei.
public class GameActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        GameSystem.Instance.SetGameActivity(this);
        setContentView(new GameView(this));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();

        TouchManager.Instance.Update(x, y, event.getAction());
        return true;
    }

    // SwitchScreen() to OptionState(XML), but doesn't work for now.
    public void switchScreen()
    {
        EntityManager.Instance.getEntityList().clear();
        finish();
        Intent intent = new Intent();
        intent.setClass(this, OptionState.class);
    }
}
