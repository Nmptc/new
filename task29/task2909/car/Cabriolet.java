package com.javarush.task.task29.task2909.car;

import com.javarush.task.task29.task2909.car.Car;

import java.util.Calendar;

/**
 * Created by Администратор on 07.08.2017.
 */
public class Cabriolet extends Car {
    public Cabriolet(int numberOfPassengers) {
        super(2, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_CABRIOLET_SPEED;
    }
}
