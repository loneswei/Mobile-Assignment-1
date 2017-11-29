package assignment1.panda;

public class Collision
{
    public static boolean sphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        float xVec = x2 - x1;
        float yVec = y2 - y1;
        float distSquared = xVec * xVec + yVec * yVec;

        float rSquared = (radius1 + radius2) * (radius1 + radius2);

        if(distSquared > rSquared)
            return false;
        else
            return true;
    }
}
