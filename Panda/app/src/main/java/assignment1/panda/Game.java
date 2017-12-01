package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class Game
{
    public final static Game Instance = new Game();
    private float timer = 0.0f;
    private Bitmap bmp = null;
    private Bitmap bmp2 = null;

    private Game() {}
    public void Init(SurfaceView _view)
    {
        EntityManager.Instance.Init(_view);
        GameBackground.Create();
        ButtonEntity.Create("Paper");
        ButtonEntity.Create("Glass");
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ic_launcher);
        bmp2 = BitmapFactory.decodeResource(_view.getResources(), R.drawable.ic_shortcut_icon_recyclingbin);
    }

    public void Update(float _dt)
    {
        // Rubbish Creation
        timer += _dt;
        if(timer > 1.0f)
        {
            Random ranGen = new Random();
            int rubbishType = ranGen.nextInt(4) + 1;
            switch(rubbishType)
            {
                case 1:
                    RubbishEntity.Create("Paper");
                    break;
                case 2:
                    RubbishEntity.Create("Glass");
                    break;
                case 3:
                    RubbishEntity.Create("Metal");
                    break;
                case 4:
                    RubbishEntity.Create("Organic");
                    break;
            }
            timer = 0.0f;
        }
        if(TouchManager.Instance.isDown())
        {
            float imgRadius = bmp.getHeight() * 0.5f;
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 1700.0f, 300.0f, imgRadius))
            {
                // Create bin
                BinEntity.Create("PaperBin");
            }
            float imgRadius2 = bmp2.getHeight() * 0.5f;
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 1700.0f, 500.0f, imgRadius2))
            {
                // Create bin
                BinEntity.Create("GlassBin");
            }
        }
        EntityManager.Instance.Update(_dt);
    }

    public void Render(Canvas _canvas) { EntityManager.Instance.Render(_canvas); }
}
