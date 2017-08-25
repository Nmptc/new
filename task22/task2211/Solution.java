package com.javarush.task.task22.task2211;

import java.io.*;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        InputStream reader = new FileInputStream(args[0]);
        OutputStream writer = new FileOutputStream(args[1]);
        while (reader.available() > 0) {
            byte[] buff = new byte[1000];
            int count = reader.read(buff);
            String line = new String(buff, 0, count, "UTF-8");
            buff = line.getBytes("WINDOWS-1251");
            writer.write(buff, 0, buff.length);
        }
        reader.close();
        writer.close();
    }
}
