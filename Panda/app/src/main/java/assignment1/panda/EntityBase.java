package assignment1.panda;

import android.graphics.Canvas;
import android.view.SurfaceView;

public interface EntityBase
{
    boolean isDone();
    void setIsDone(boolean _isDone);

    void Init(SurfaceView _view);
    void Update(float _dt);
    void Render(Canvas _canvas);
}