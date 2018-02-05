package ru.khitrova.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("Hello, world");

        Point p = new Point(1, 1);

        Point p2 = new Point(6, 1);


        System.out.println("Расстоение между точками Р1(" + p.x + ";" + p.y + ") и Р2(" + p2.x + ";" + p2.y + ") = " + p.distance(p2));
    }

}

