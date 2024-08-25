package org.question1;

public class Triangle extends AbstractFigure {

    public Triangle(Double side, Double hight){
        this.vertices.add(side);
        this.vertices.add(hight);
    }

    @Override
    public Double calculateArea() {
        return this.vertices.get(0) * this.vertices.get(1) * 0.5;
    }
}
