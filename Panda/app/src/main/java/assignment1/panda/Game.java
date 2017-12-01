package assignment1.panda;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class Game
{
    public final static Game Instance = new Game();
    private float timer = 0.0f;

    private Game() {}
    public void Init(SurfaceView _view)
    {
        EntityManager.Instance.Init(_view);
        GameBackground.Create();
    }

    public void Update(float _dt)
    {
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
        EntityManager.Instance.Update(_dt);
    }

    public void Render(Canvas _canvas) { EntityManager.Instance.Render(_canvas); }
}
