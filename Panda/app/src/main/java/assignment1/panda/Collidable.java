package assignment1.panda;

public interface Collidable
{
    float getPosX();
    float getPosY();
    float getRadius();

    void onHit(Collidable _other);
}