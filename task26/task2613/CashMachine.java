package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by Администратор on 01.08.2017.
 */
public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().toString().substring(8).replace("/",".") + ".resources.";

    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        try {
            CommandExecutor.execute(Operation.LOGIN);
        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
            return;
        }
        Operation operation = null;
        do {
            try {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } catch (InterruptOperationException e) {
                ConsoleHelper.printExitMessage();
                operation = Operation.EXIT;
            }
        } while (!operation.equals(Operation.EXIT));
    }
}
