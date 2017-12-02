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
    private Bitmap bmpPaperBin = null;
    private Bitmap bmpPlasticBin = null;
    private Bitmap bmpMetalBin = null;
    private Bitmap bmpOthersBin = null;

    private Game() {}
    public void Init(SurfaceView _view)
    {
        EntityManager.Instance.Init(_view);
        GameBackground.Create();
        ButtonEntity.Create("Paper");
        ButtonEntity.Create("Plastic");
        ButtonEntity.Create("Metal");
        ButtonEntity.Create("Others");
        bmpPaperBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.blue_paper_recyclingbin);
        bmpPlasticBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.plastic_green_recyclingbin);
        bmpMetalBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_red_recyclingbin);
        bmpOthersBin = BitmapFactory.decodeResource(_view.getResources(), R.drawable.generalwaste_greyrecyclingbin);
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

        if(TouchManager.Instance.isDown())
        {
            float imgRadius = bmpPaperBin.getHeight() * 0.5f;
            float imgRadius2 = bmpPlasticBin.getHeight() * 0.5f;
            float imgRadius3 = bmpMetalBin.getHeight() * 0.5f;
            float imgRadius4 = bmpOthersBin.getHeight() * 0.5f;
            if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 1700.0f, 300.0f, imgRadius))
            {
                // Create bin
                BinEntity.Create("PaperBin");
            }
            else if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 1700.0f, 500.0f, imgRadius2))
            {
                // Create bin
                BinEntity.Create("PlasticBin");
            }
            else if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 1700.0f, 700.0f, imgRadius3))
            {
                // Create bin
                BinEntity.Create("MetalBin");
            }
            else if(Collision.sphereToSphere(TouchManager.Instance.getPosX(), TouchManager.Instance.getPosY(), 0.0f, 1700.0f, 900.0f, imgRadius4))
            {
                // Create bin
                BinEntity.Create("OthersBin");
            }
        }
        EntityManager.Instance.Update(_dt);
    }

    public void Render(Canvas _canvas) { EntityManager.Instance.Render(_canvas); }
}
