package assignment1.panda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class Splashscreen extends Activity {

    boolean _active = true;
    int _splashTime = 5000; // time for the splash screen to be display in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // this is using layout
        setContentView(R.layout.splashscreen);

        //thread for displaying the splash screen
        Thread splashThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime))
                    {
                        sleep(200);
                        if (_active)
                        {
                            waited += 200;
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    // do nothing
                }
                finally
                {
                    finish();
                    // Create new activity based on and intent with CurrentActivity
                    Intent intent = new Intent(Splashscreen.this, Mainmenu.class);
                    startActivity(intent);
                }
            }
        };
        splashThread.start();
    }


    // Checking if there is any tap on screen during splash screen
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // If there is tap action, set _active to false so as to move to next activity(Main menu)
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            _active = false;
        }
        return true;
    }
}