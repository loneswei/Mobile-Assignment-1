package assignment1.panda;

import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;

public class AudioManager
{
    public final static AudioManager Instance = new AudioManager();

    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    private AudioManager() {}

    public void Init(SurfaceView _view) {  view = _view; }

    public void PlayAudio(int _id)
    {
        if(Game.Instance.getIsPaused())
            return;
        if(audioMap.containsKey(_id))
        {
            MediaPlayer curr = audioMap.get(_id);
            curr.reset();
            curr.start();
        }
        MediaPlayer newAudio = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id, newAudio);
        newAudio.start();
    }

    public boolean IsPlaying(int _id)
    {
        if(!audioMap.containsKey(_id))
            return false;

        return audioMap.get(_id).isPlaying();
    }
}