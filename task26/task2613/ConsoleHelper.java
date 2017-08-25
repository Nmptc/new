package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by Администратор on 01.08.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().toString().substring(8).replace("/",".") + ".resources.common_en");
    
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }
    
    public static String readString() throws InterruptOperationException {
        try
        {
            String st = bis.readLine();
            if(st.equalsIgnoreCase("exit")) throw new InterruptOperationException();
            return st;
        }
        catch (InterruptOperationException ex)
        {
            writeMessage(res.getString("the.end"));
            throw ex;
        }
        catch (Exception ex)
        {
            
        }
        
        return "";
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
        while (true) {
            String st = readString();
            if (st.length() == 3) return st.toUpperCase();
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));

        String[] input;
        while (true) {
            input = readString().split(" ");

            int nominal = 0;
            int total = 0;
            try {
                nominal = Integer.parseInt(input[0]);
                total = Integer.parseInt(input[1]);
            }
            catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (nominal <= 0 || total <= 0) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return input;
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation ret = null;
        writeMessage(res.getString("choose.operation"));
        while (true)
        {
            try {
                int nominal = Integer.parseInt(readString());
                ret = Operation.getAllowableOperationByOrdinal(nominal);
                break;
            }
            catch (InterruptOperationException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                System.out.println(res.getString("invalid.data"));
            }
        }
        return ret;
    }

    public static void printExitMessage()
    {
        writeMessage(res.getString("the.end"));
    }
}