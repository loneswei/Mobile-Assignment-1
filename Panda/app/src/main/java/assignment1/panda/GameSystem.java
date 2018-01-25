package assignment1.panda;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class GameSystem
{
    public final static GameSystem Instance = new GameSystem();
    public final static String SHARED_PREF_ID = "GameSaveFile";
    private boolean isPause = false;
    private boolean showSprite = false;
    GameActivity gameActivity = null;

    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    //to not allow creation of another game
    private GameSystem()
    {

    }
    public void Init(SurfaceView _view)
    {
        sharedPref = gameActivity.getSharedPreferences(SHARED_PREF_ID,0);
        Tutorial.Instance.Init(_view);
        // Add game states here
        StateManager.Instance.AddState(new MainGameState());
        StateManager.Instance.AddState(new IntroState());
        StateManager.Instance.AddState(new MainMenuState());
        StateManager.Instance.AddState(new LevelSelectState());
        StateManager.Instance.AddState(new OptionState());
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

    // Shared Preference Section - Save
    public int GetIntFromSave(String _key) { return sharedPref.getInt(_key, 0); }
    public void SetIntInSave(String _key, int _value)
    {
        // Safety check
        if(editor == null)
            return;

        // Save data here
        editor.putInt(_key, _value);
    }

    public void SaveEditBegin()
    {
        // Safety catch,make sure no editor currently
        if(editor != null)
            return;

        // Start editing by providing editor
        editor = sharedPref.edit();
    }
    public void SaveEditEnd()
    {
        // Safety catch,only allow if there is editor
        if(editor == null)
            return;

        editor.commit();
        editor = null; // Clean up editor
    }
}
