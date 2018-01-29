package assignment1.panda;

import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;

// This is done by Wong Shih Wei.
public class AudioManager
{
    public final static AudioManager Instance = new AudioManager();

    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    private AudioManager() {}

    public void Init(SurfaceView _view) {  view = _view; }

    // PlayAudio() is used to play sounds.
    public void PlayAudio(int _id)
    {
        // If pause, then don't play sounds.
        if(GameSystem.Instance.GetIsPaused())
            return;

        // Find the specific sound and play it.
        if(audioMap.containsKey(_id))
        {
            MediaPlayer curr = audioMap.get(_id);
            curr.reset();
            curr.start();
        }

        // If the specifc sound is not inside the map, put into map and start playing.
        MediaPlayer newAudio = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id, newAudio);
        newAudio.start();
    }

    // IsPlaying() is used to check if a specific sound is playing but it's not used.
    public boolean IsPlaying(int _id)
    {
        if(!audioMap.containsKey(_id))
            return false;

        return audioMap.get(_id).isPlaying();
    }
}