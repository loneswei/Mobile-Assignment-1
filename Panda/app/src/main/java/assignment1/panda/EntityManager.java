package assignment1.panda;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.LinkedList;

public class EntityManager
{
    public final static EntityManager Instance = new EntityManager();
    private SurfaceView view = null;
    private LinkedList<EntityBase> entityList = new LinkedList<EntityBase>();

    private EntityManager(){}
    public void Init(SurfaceView _view) { view = _view; }

    public void Update(float _dt)
    {
        LinkedList<EntityBase> removalList = new LinkedList<EntityBase>();

        //Update
        for(EntityBase currEntity : entityList)
        {
            currEntity.Update(_dt);

            if(currEntity.isDone())
                removalList.add(currEntity);
        }

        //Remove
        for(EntityBase currEntity : removalList)
            entityList.remove(currEntity);
        removalList.clear();

        //Collision
        for(int i = 0; i < entityList.size(); ++i)
        {
            EntityBase currEntity = entityList.get(i);

            // if currEntity is an instance of Collidable
            if(currEntity instanceof Collidable)
            {
                Collidable first = (Collidable) currEntity;

                for(int j = i + 1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    // if otherEntity is an instance of Collidable
                    if(otherEntity instanceof Collidable)
                    {
                        Collidable second = (Collidable) otherEntity;

                        // Sphere to Sphere collision check
                        if(Collision.sphereToSphere(first.getPosX(), first.getPosY(), first.getRadius(), second.getPosX(), second.getPosY(), second.getRadius()))
                        {
                            first.onHit(second);
                            second.onHit(first);
                        }
                    }
                }
            }

            if(currEntity.isDone())
                removalList.add(currEntity);
        }
        //Remove
        for(EntityBase currEntity : removalList)
            entityList.remove(currEntity);
        removalList.clear();
    }

    public void Render(Canvas _canvas)
    {
        for(EntityBase currEntity : entityList)
            currEntity.Render(_canvas);
    }

    public void AddEntity(EntityBase _newEntity)
    {
        _newEntity.Init(view);
        entityList.add(_newEntity);
    }
}
