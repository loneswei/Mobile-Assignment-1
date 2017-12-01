package assignment1.panda;

public interface Collidable
{
    String getType();
    void setType(String _Type);
    float getPosX();
    float getPosY();
    float getRadius();

    void onHit(Collidable _other);
}