package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Администратор on 09.08.2017.
 */
public class MyThread extends Thread {
    private static AtomicInteger currentPriority = new AtomicInteger(MIN_PRIORITY - 1);

    public MyThread() {
        currentPriority.incrementAndGet();
        if(currentPriority.get()>MAX_PRIORITY)
            currentPriority.set(MIN_PRIORITY);
        if(getThreadGroup()!=null&&getThreadGroup().getMaxPriority()<currentPriority.get()) {
            setPriority(getThreadGroup().getMaxPriority());
        }
        else
            setPriority(currentPriority.get());
    }

    public MyThread(Runnable target) {
        super(target);

        currentPriority.incrementAndGet();
        if(currentPriority.get()>MAX_PRIORITY)
            currentPriority.set(MIN_PRIORITY);
        if(getThreadGroup()!=null&&getThreadGroup().getMaxPriority()<currentPriority.get())
            setPriority(getThreadGroup().getMaxPriority());
        else
            setPriority(currentPriority.get());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);

        currentPriority.incrementAndGet();
        if(currentPriority.get()>MAX_PRIORITY)
            currentPriority.set(MIN_PRIORITY);
        if(getThreadGroup()!=null&&getThreadGroup().getMaxPriority()<currentPriority.get())
            setPriority(getThreadGroup().getMaxPriority());
        else
            setPriority(currentPriority.get());
    }

    public MyThread(String name) {
        super(name);

        currentPriority.incrementAndGet();
        if(currentPriority.get()>MAX_PRIORITY)
            currentPriority.set(MIN_PRIORITY);
        if(getThreadGroup()!=null&&getThreadGroup().getMaxPriority()<currentPriority.get())
            setPriority(getThreadGroup().getMaxPriority());
        else
            setPriority(currentPriority.get());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);

        currentPriority.incrementAndGet();
        if(currentPriority.get()>MAX_PRIORITY)
            currentPriority.set(MIN_PRIORITY);
        if(group.getMaxPriority()<currentPriority.get())
            setPriority(group.getMaxPriority());
        else
            setPriority(currentPriority.get());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);

        currentPriority.incrementAndGet();
        if(currentPriority.get()>MAX_PRIORITY)
            currentPriority.set(MIN_PRIORITY);
        if(getThreadGroup()!=null&&getThreadGroup().getMaxPriority()<currentPriority.get())
            setPriority(getThreadGroup().getMaxPriority());
        else
            setPriority(currentPriority.get());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);

        currentPriority.incrementAndGet();
        if(currentPriority.get()>MAX_PRIORITY)
            currentPriority.set(MIN_PRIORITY);
        if(group.getMaxPriority()<currentPriority.get())
            setPriority(group.getMaxPriority());
        else
            setPriority(currentPriority.get());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);

        currentPriority.incrementAndGet();
        if(currentPriority.get()>MAX_PRIORITY)
            currentPriority.set(MIN_PRIORITY);
        if(group.getMaxPriority()<currentPriority.get())
            setPriority(group.getMaxPriority());
        else
            setPriority(currentPriority.get());
    }
}
