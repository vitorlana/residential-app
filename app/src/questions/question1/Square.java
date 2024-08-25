package org.question1;

public class Square extends AbstractFigure {

    public Square(Double side){
        this.vertices.add(side);
    }

    @Override
    public Double calculateArea() {
        return this.vertices.get(0) * this.vertices.get(0);
    }
}
