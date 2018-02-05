package ru.khitrova.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("Hello, world");

        Square s = new Square(2);
        System.out.println("Площадь квадрата со стороной "+s.a+ " равняется "+s.area());

        Rectangle r = new Rectangle(3,5);
        System.out.println("Площадь прямоугольника со сторонами "+ r.a+ " и "+r.b+" равняется "+r.area());


        Point p = new Point(2.5, 0.3);
        Point p2 = new Point(2, 9);
        System.out.println("Расстоение между точками Р1(" + p.x + ";" + p.y + ") и Р2(" + p2.x + ";" + p2.y + ") = " + p.distance(p2));
    }

}

