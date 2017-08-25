package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Администратор on 10.08.2017.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()) return true;
        String fname = f.getName().toLowerCase();
        return fname.endsWith(".htm")||fname.endsWith(".html");
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
