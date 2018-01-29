package assignment1.panda;

import android.graphics.Canvas;
import android.view.SurfaceView;

// This is done by Wong Shih Wei.
public class GameSystem
{
    public final static GameSystem Instance = new GameSystem();
    private boolean isPause = false;
    private boolean showSprite = false;
    GameActivity gameActivity = null;
    int currNumOfRubbish = 0;

    //to not allow creation of another game
    private GameSystem()
    {
    }
    public void Init(SurfaceView _view)
    {
        Tutorial.Instance.Init(_view);
        // Add game states here
        StateManager.Instance.AddState(new MainGameState());
        StateManager.Instance.AddState(new IntroState());
        StateManager.Instance.AddState(new MainMenuState());
        StateManager.Instance.AddState(new LevelSelectState());
        StateManager.Instance.AddState(new LoseState());
        StateManager.Instance.AddState(new WinState());
    }

    public void Update(float _dt)
    {
    }

    public void Render(Canvas _canvas)
    {
    }

    public boolean GetIsPaused() { return isPause; }
    public void SetIsPaused(boolean _isPaused) { isPause = _isPaused; }
    public boolean GetIsShowSprite() { return showSprite; }
    public void SetIsShowSprite(boolean isShowSprite) { showSprite = isShowSprite; }
    public void SetGameActivity(GameActivity _gameActivity) { gameActivity = _gameActivity; }

}
