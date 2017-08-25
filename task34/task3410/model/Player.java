package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by Администратор on 03.08.2017.
 */
public class Player extends CollisionObject implements Movable{
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        Polygon polygon = new Polygon();
        polygon.addPoint(x, y - height/2);
        polygon.addPoint(x + width/2, y + height/2);
        polygon.addPoint(x - width/2, y + height/2);
        graphics.fillPolygon(polygon);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
