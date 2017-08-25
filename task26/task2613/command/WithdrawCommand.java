package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Администратор on 02.08.2017.
 */
class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        String curr = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curr);
        ConsoleHelper.writeMessage(res.getString("before"));
        int sum;
        while (true)
        {
            sum = 0;
            try {
                sum = Integer.parseInt(ConsoleHelper.readString());
            }
            catch (NumberFormatException ex)
            {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                continue;
            }

            if(sum <= 0)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }

            if(manipulator.isAmountAvailable(sum))
            {
                Map<Integer, Integer> amountMap = null;
                try {
                    amountMap = manipulator.withdrawAmount(sum);
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    continue;
                }

                for(Map.Entry<Integer, Integer> entry:amountMap.entrySet())
                {
                    ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                }
                break;
            }
            else
            {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                continue;
            }
        }

        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, curr));
    }
}
