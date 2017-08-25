package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
Составить цепочку слов
*/
public class Solution {
    private static int depth = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bFileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
        bufferedReader.close();
        bufferedReader = null;

        ArrayList<String> arl = new ArrayList<>();
        while (bFileReader.ready())
        {
            String[] split = bFileReader.readLine().split(" ");
            for(String st: split)
            {
                if(!st.isEmpty()&&!st.equals(" "))
                {
                    arl.add(st);
                }
            }
        }
        bFileReader.close();
        bFileReader = null;
        String[] words = arl.toArray(new String[arl.size()]);
        arl = null;

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(char c, String[] words, int ldepth)
    {
        if(words.length == 1)
        {
            String st = words[0];
            if(st.substring(0,1).toLowerCase().toCharArray()[0] == c)
                return new StringBuilder(st);

            return new StringBuilder();
        }

        for(int i = 0; i < words.length; ++i)
        {
            String st = words[i];
            if(st.substring(0,1).toLowerCase().toCharArray()[0] == c)
            {
                String[] newArray = new String[words.length - 1];
                for(int j = 0; j <i; ++ j)
                {
                    newArray[j] = words[j];
                }

                for(int j = i + 1; j < words.length; ++j)
                {
                    newArray[j-1] = words[j];
                }

                StringBuilder tmp = getLine(st.substring(st.length() - 1).toLowerCase().toCharArray()[0], newArray, ldepth + 1);
                if(!tmp.toString().isEmpty())
                {
                    tmp.insert(0, " ");
                    tmp.insert(0, st);
                    return tmp;
                }
            }
        }

        return new StringBuilder();
    }

    public static StringBuilder getLine(String... words) {
        if(words==null||words.length==0)
            return new StringBuilder();

        if(words.length==1)
            return new StringBuilder(words[0]);

        StringBuilder res = new StringBuilder();
        int cdepth = 0;

        for(int i = 0; i < words.length; ++i) {
            String st = words[i];
            String[] newArray = new String[words.length - 1];
            for (int j = 0; j < i; ++j) {
                newArray[j] = words[j];
            }

            for (int j = i + 1; j < words.length; ++j) {
                newArray[j - 1] = words[j];
            }

            StringBuilder tmp = getLine(st.substring(st.length()-1).toLowerCase().toCharArray()[0], newArray, 1);
            if (!tmp.toString().isEmpty()) {
                tmp.insert(0, " ");
                tmp.insert(0, st);
                return tmp;
            }

        }

        return new StringBuilder();
    }
}
