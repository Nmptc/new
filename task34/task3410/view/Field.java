package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.*;
import com.javarush.task.task34.task3410.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;

/**
 * Created by Администратор on 03.08.2017.
 */
public class Field extends JPanel implements EventListener {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, view.getWidth(), view.getHeight());
        for(GameObject gameObject:view.getGameObjects().getAll())
            gameObject.draw(g);
    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public void restart() {

    }

    @Override
    public void startNextLevel() {

    }

    @Override
    public void levelCompleted(int level) {

    }

    public  void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            switch (e.getKeyCode())
            {
                case VK_LEFT:
                {
                    eventListener.move(Direction.LEFT);
                    break;
                }
                case VK_RIGHT:
                {
                    eventListener.move(Direction.RIGHT);
                    break;
                }
                case VK_UP:
                {
                    eventListener.move(Direction.UP);
                    break;
                }
                case VK_DOWN:
                {
                    eventListener.move(Direction.DOWN);
                    break;
                }
                case VK_R:
                {
                    eventListener.restart();
                    break;
                }
            }
        }
    }
}
