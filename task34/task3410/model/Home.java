package com.javarush.task.task34.task3410.model;

import java.awt.*;

/**
 * Created by Администратор on 03.08.2017.
 */
public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawLine(x-width/2, y-width/2, x+width/2, y+width/2);
        graphics.drawLine(x-width/2, y+width/2, x+width/2, y-width/2);
        graphics.drawRect(x-width/2, y-height/2, width, height);
    }
}
