package assignment1.panda;

// This is done by Wong Shih Wei.
public interface Collidable
{
    float getPosX();
    float getPosY();
    float getRadius();

    void onHit(Collidable _other);
}