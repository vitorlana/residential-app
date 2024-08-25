package org.question1;

public class Circle extends AbstractFigure {

    public Circle(Double raio){
        this.vertices.add(raio);
    }

    @Override
    public Double calculateArea() {
        return this.vertices.get(0) * this.vertices.get(0) * Math.PI;
    }
}
