package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Администратор on 02.08.2017.
 */
class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        System.out.println(res.getString("exit.question.y.n"));
        String string = ConsoleHelper.readString();
        if(string.equals("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
}
