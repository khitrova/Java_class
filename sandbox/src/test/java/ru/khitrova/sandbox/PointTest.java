package ru.khitrova.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTest {

    @Test
    public void testDistance(){
        Point p = new Point(2.5,0.3);
        Point p1 = new Point(2,9);
        Assert.assertEquals(p.distance(p1), 8.714355971613736);
    }

    @Test
    public void testDistanceZero(){
        Point p = new Point(0,0);
        Point p1 = new Point(0,0);
        Assert.assertEquals(p.distance(p1),0.0);
    }

    @Test
    public void testDistanceSimple(){
        Point p = new Point(1,1);
        Point p1 = new Point(1,9);
        Assert.assertEquals(p.distance(p1), 8.0);
    }
}
