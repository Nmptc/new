package com.javarush.task.task40.task4004;

import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        int counter = 0;
        for(int i = 0; i < polygon.size() - 1; ++i)
        {
            Point point1 = polygon.get(i);
            if(point.x == point1.x&&point.y == point1.y) return true;

            int j = i==polygon.size()-1?0:i+1;
            Point point2 = polygon.get(j);

            if(point1.x == point2.x)
            {
                if (point1.y == point.y&&(point.x - point1.x)*(point.x - point1.x)<=0) counter++;
                continue;
            }

            if(point1.y == point2.y)
            {
                if (point1.y >= point.y) counter++;
                continue;
            }

            double a = ((double) point1.y - (double)point2.y)/((double)point1.x - ((double)point2.x));
            double b = (double)point1.y - a*point1.x;

            double yl = a*point.x + b;
            if(yl>point.y&&(yl - point1.y)*(yl - point2.y)<=0) counter++;
        }
        return counter%2!=0;
    }

}

