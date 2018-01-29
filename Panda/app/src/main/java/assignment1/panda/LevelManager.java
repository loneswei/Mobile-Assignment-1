package assignment1.panda;

// This is done by Wong Shih Wei.
public class LevelManager
{
    public final static LevelManager Instance = new LevelManager();

    private int selectedLevel = 0;

    private LevelManager() {}

    public int GetSelectedLevel() { return selectedLevel; }
    public void SetSelectedLevel(int _selectedLevel) { selectedLevel = _selectedLevel; }
}
