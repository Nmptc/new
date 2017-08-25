package com.javarush.task.task25.task2506;

/**
 * Created by Администратор on 07.08.2017.
 */
public class LoggingStateThread extends Thread
{
    Thread target;

    public LoggingStateThread(Thread target) {
        this.target = target;
    }

    public void run()
    {
        State cstate, pstate=null;
        while (true)
        {
            cstate = target.getState();
            if(!cstate.equals(pstate))
            {
                System.out.println(cstate);
                pstate = cstate;
            }
            if(cstate == State.TERMINATED)
                break;
        }
    }
}
