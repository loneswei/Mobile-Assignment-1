package assignment1.panda;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class UpdateThread extends Thread
{
    static final long targetFPS = 60;
    private GameView view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning = false;

    public UpdateThread(GameView _view)
    {
        view = _view;
        holder = _view.getHolder();
        AudioManager.Instance.Init(_view);
        Tutorial.Instance.Init(_view);
        Game.Instance.Init(_view);
    }

    public boolean isRunning() { return isRunning; }
    public void Init() { isRunning = true; }
    public void Terminate() { isRunning = false; }

    @Override
    public void run()
    {
        long framePerSecond = 1000 / targetFPS;
        long startTime = 0;
        long prevTime = System.nanoTime();

        while(isRunning())
        {
            startTime = System.currentTimeMillis();
            long currTime = System.nanoTime();
            float dt = (float)((currTime - prevTime) / 1000000000.0f);
            prevTime = currTime;
            Game.Instance.Update(dt);

            // Render
            Canvas canvas = holder.lockCanvas(null);
            if(canvas != null)
            {
                synchronized (holder)
                {
                    canvas.drawColor(Color.BLACK);
                    Game.Instance.Render(canvas);
                }
                holder.unlockCanvasAndPost(canvas);
            }

            try
            {
                long sleepTime = framePerSecond - (System.currentTimeMillis() - startTime);
                if(sleepTime > 0)
                    sleep(sleepTime);
            }
            catch (InterruptedException e) { Terminate(); }
        }
    }
}
