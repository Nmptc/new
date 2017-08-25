package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by Администратор on 03.08.2017.
 */
public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.orange);
        graphics.fillRect(x-width/2, y-height/2, width, height);
    }
}
