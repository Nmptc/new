package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bFileReader;
        try {
            bFileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
            bufferedReader.close();
            bufferedReader = null;

            HashSet<String> prevWords = new HashSet<>();
            while (bFileReader.ready())
            {
                String[] split = bFileReader.readLine().split(" ");
                for(String st: split)
                {
                    if(st.isEmpty() || st.equals(" "))
                        continue;

                    String rev = new StringBuilder(st).reverse().toString();
                    for(String st2: prevWords)
                    {
                        if(st2.equals(rev))
                        {
                            boolean pairFound = false;
                            for(Pair pair: result)
                            {
                                if(pair.first.equals(st)&&pair.second.equals(st2)||pair.second.equals(st)&&pair.first.equals(st2))
                                {
                                    pairFound = true;
                                    break;
                                }
                            }

                            if(!pairFound)
                            {
                                Pair pair = new Pair();
                                pair.first = st2;
                                pair.second = st;
                                result.add(pair);
                            }

                            break;
                        }
                    }

                    if(!prevWords.contains(st))
                        prevWords.add(st);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Pair pair: result)
            System.out.println(pair);
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
