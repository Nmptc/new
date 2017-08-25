package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Администратор on 02.08.2017.
 */
class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        String[] sumParameter = ConsoleHelper.getValidTwoDigits(currency);
        manipulator.addAmount(Integer.parseInt(sumParameter[0]), Integer.parseInt(sumParameter[1]));
        int sum = Integer.parseInt(sumParameter[0])*Integer.parseInt(sumParameter[1]);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currency));
    }
}
