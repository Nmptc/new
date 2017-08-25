package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import javax.swing.plaf.metal.MetalTheme;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Администратор on 02.08.2017.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new TreeMap<>(Collections.reverseOrder());
    }

    public  void addAmount(int denomination, int count)
    {
        if(denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else
            denominations.put(denomination, count);
    }

    public int getTotalAmount()
    {
        int res = 0;
        for(Map.Entry<Integer, Integer> entry:denominations.entrySet())
            res = res + entry.getKey()*entry.getValue();
        return res;
    }

    public boolean hasMoney()
    {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int sum) {
        return getTotalAmount()>=sum;
    }

    private int getDenominations(Map<Integer, Integer> cmap, Map<Integer, Integer> result, int sum, int limiter)
    {
        Map<Integer, Integer> localMap = new TreeMap<>(Collections.reverseOrder());
        localMap.putAll(cmap);
        int res = sum;
        for(Map.Entry<Integer, Integer> et: localMap.entrySet())
        {
            Integer key = et.getKey();
            if(key <= sum && key <= limiter && et.getValue()>0)
            {
                int quotient = sum / key;
                quotient = Math.min(quotient, et.getValue());
                for(int i = quotient; i>0; --i)
                {
                    localMap.put(key, et.getValue() - i);
                    result.put(key, i);
                    res = getDenominations(localMap, result, sum - i* key, key - 1);
                    if(res == 0) return 0;
                    localMap.put(key, et.getValue() + i);
                    result.remove(key);
                    if(res > (sum - key*i)) res = sum - key*i;
                }
            }
        }

        return res;
    }

    private int getMaxNom()
    {
        int res = 0;
        for(Map.Entry<Integer, Integer> entry:denominations.entrySet())
            if(entry.getKey()>res) res = entry.getKey();
        return res;
    }

    public Map<Integer,Integer> withdrawAmount(int sum) throws NotEnoughMoneyException {
        Map<Integer, Integer> res = new TreeMap<>(Collections.reverseOrder());
        int ost = getDenominations(denominations, res, sum, getMaxNom());
        if(ost!=0) throw new NotEnoughMoneyException();

        for(Map.Entry<Integer, Integer> entry:res.entrySet())
        {
            if(entry.getValue()==denominations.get(entry.getKey()))
                denominations.remove(entry.getKey());
            else
                denominations.put(entry.getKey(), denominations.get(entry.getKey()) - entry.getValue());
        }

        return res;
    }
}
