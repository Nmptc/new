package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Администратор on 02.08.2017.
 */
public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        Pattern pattern = Pattern.compile("\\D");
        ConsoleHelper.writeMessage(res.getString("before"));
        String cnumber = "";
        while (true) {
            String cpin = "";
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            while (true) {
                cnumber = ConsoleHelper.readString();
                if (cnumber.length() != 12) {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    continue;
                }
                Matcher matcher = pattern.matcher(cnumber);
                if (matcher.find()) {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    continue;
                }
                break;
            }

            ConsoleHelper.writeMessage("Enter card pin");
            while (true) {
                cpin = ConsoleHelper.readString();
                if (cpin.length() != 4) {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    continue;
                }
                Matcher matcher = pattern.matcher(cpin);
                if (matcher.find()) {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    continue;
                }
                break;
            }


            if(!validCreditCards.containsKey(cnumber)||!validCreditCards.getString(cnumber).equals(cpin))
            {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cnumber));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }

            break;
        }

        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cnumber));
    }
}
