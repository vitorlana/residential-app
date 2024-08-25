package org.question1;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Square square = new Square(2.0);
        Triangle triangle = new Triangle(2.0,2.0);
        Retangle rectangle = new Retangle(2.0,3.0);
        Circle circle = new Circle(2.0);

        ArrayList<AbstractFigure> list = new ArrayList<AbstractFigure>();
        list.add(triangle);
        list.add(square);
        list.add(circle);
        list.add(rectangle);

        for(AbstractFigure each: list) {
            System.out.println(each.calculateArea());
        }





    }
}