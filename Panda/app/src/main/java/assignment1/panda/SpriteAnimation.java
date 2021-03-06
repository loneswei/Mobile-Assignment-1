package assignment1.panda;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

// This is done by Wong Shih Wei
public class SpriteAnimation
{
    private int col = 0;
    private int width = 0;
    private int height = 0;

    private Bitmap bmp = null;

    private int currentFrame = 0;
    private int startFrame = 0;
    private int endFrame = 0;

    private float timePerFrame = 0.0f;
    private float time = 0.0f;

    public SpriteAnimation(Bitmap _bmp, int _row, int _col, int _fps)
    {
        bmp = _bmp;
        col = _col;

        width = bmp.getWidth() / _col;
        height = bmp.getHeight() / _row;

        timePerFrame = 1.0f / (float) _fps;

        endFrame = _col * _row;
    }

    public void Update(float _dt)
    {
        time += _dt;
        if(time > timePerFrame)
        {
            ++currentFrame;
            // Reset the sprite animation frame when animation is done.
            if(currentFrame >= endFrame)
            {
                GameSystem.Instance.SetIsShowSprite(false);
                currentFrame = startFrame;
            }
            time = 0.0f;
        }
    }

    public void Render(Canvas _canvas, int _x, int _y)
    {
        int frameX = currentFrame % col;
        int frameY = currentFrame / col;
        int srcX = frameX * width;
        int srcY = frameY * height;

        _x -= 0.5f * width;
        _y -= 0.5f * height;

        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(_x, _y, _x + width, _y + height);
        _canvas.drawBitmap(bmp, src, dst, null);
    }

    public void SetAnimationFrames(int _start, int _end)
    {
        time = 0.0f;
        currentFrame = _start;
        startFrame = _start;
        endFrame = _end;
    }
    
    public int GetHeight() { return height; }

    public int GetWidth() { return width; }
}
