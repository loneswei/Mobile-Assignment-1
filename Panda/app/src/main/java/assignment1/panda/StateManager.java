package assignment1.panda;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.HashMap;

// This is done by Wong Shih Wei
public class StateManager
{
    public final static StateManager Instance  = new StateManager();
    private SurfaceView view = null;

    // Our container to store all our states
    private HashMap<String, StateBase> stateMap = new HashMap<String, StateBase>();
    private StateBase currState = null;
    private StateBase nextState = null;

    private StateManager() {}
    public void Init(SurfaceView _view) { view = _view; }

    public void Update(float _dt)
    {
        // Change state
        if(nextState != currState)
        {
            currState.OnExit();
            nextState.OnEnter(view);
            currState = nextState;
        }

        // Safety Catch
        if(currState == null)
            return;

        currState.Update(_dt);
    }

    public void Render(Canvas _canvas) { currState.Render(_canvas); }

    public void ChangeState(String _nextState)
    {
        nextState = stateMap.get(_nextState);

        // Fail case handle
        if(_nextState == null)
            nextState = currState;
    }

    public void AddState(StateBase _newState) { stateMap.put(_newState.GetName(), _newState); }

    void Start(String _newCurrent)
    {
        if(currState != null || nextState != null)
            return;

        currState = stateMap.get(_newCurrent);
        if(currState != null)
        {
            currState.OnEnter(view);
            nextState = currState;
        }
    }

    String GetCurrentState()
    {
        if(currState == null)
            return "INVALID";

        return currState.GetName();
    }
}
