package com.javarush.task.task34.task3410.model;

import java.awt.*;
import java.io.ObjectInputStream;

/**
 * Created by Администратор on 03.08.2017.
 */
public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        switch (direction) {
            case LEFT: {
                return (getX() - FIELD_CELL_SIZE == gameObject.getX() && gameObject.getY() == this.getY()) ? true : false;
            }
            case RIGHT: {
                return (getX() + FIELD_CELL_SIZE == gameObject.getX() && gameObject.getY() == this.getY()) ? true : false;
            }
            case DOWN: {
                return (gameObject.getX() == this.getX() && getY() + FIELD_CELL_SIZE == gameObject.getY()) ? true : false;
            }
            case UP: {
                return (gameObject.getX() == this.getX() && getY() - FIELD_CELL_SIZE == gameObject.getY()) ? true : false;
            }
            default:
                return false;
        }
    }
}
