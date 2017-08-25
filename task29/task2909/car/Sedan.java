package com.javarush.task.task29.task2909.car;

/**
 * Created by Администратор on 07.08.2017.
 */
public class Sedan extends Car {
    public Sedan(int numberOfPassengers) {
        super(1, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SEDAN_SPEED;
    }
}
