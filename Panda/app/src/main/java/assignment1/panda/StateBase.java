package assignment1.panda;

import android.graphics.Canvas;
import android.view.SurfaceView;

// This is done by Wong Shih Wei
interface StateBase
{
    String GetName();

    void OnEnter(SurfaceView _view);
    void OnExit();
    void Update(float _dt);
    void Render(Canvas _canvas);
}
