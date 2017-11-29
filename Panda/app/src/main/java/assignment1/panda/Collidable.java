package assignment1.panda;

public interface Collidable
{
    String getType();
    float getPosX();
    float getPosY();
    float getRadius();

    void onHit(Collidable _other);
}