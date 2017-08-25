package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

/**
 * Created by Администратор on 02.08.2017.
 */
interface Command {
    public void execute() throws InterruptOperationException;
}
