package assignment1.panda;

// This is done by Wong Shih Wei.
public class Collision
{
    public static boolean sphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        // Check the distance between 2 points
        float xVec = x2 - x1;
        float yVec = y2 - y1;
        float distSquared = xVec * xVec + yVec * yVec;
        // Check the combined radius
        float rSquared = (radius1 + radius2) * (radius1 + radius2);

        // If distance is less than combined radius, collision is true.
        return (distSquared < rSquared);
    }
}
