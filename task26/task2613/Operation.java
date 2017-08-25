package com.javarush.task.task26.task2613;

import java.util.regex.Pattern;

/**
 * Created by Администратор on 01.08.2017.
 */
public enum Operation{LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i)
    {
        Operation ret = null;
        switch (i)
        {
            case 0:
            {
                throw new IllegalArgumentException();
            }
            case 1:
            {
                ret = INFO;
                break;
            }
            case 2:
            {
                ret = DEPOSIT;
                break;
            }
            case 3:
            {
                ret = WITHDRAW;
                break;
            }
            case 4:
            {
                ret = EXIT;
                break;
            }
            default:
            {
                throw new IllegalArgumentException();
            }
        }

        return ret;
    }
}
