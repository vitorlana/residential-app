package org.question1;

public class Retangle extends AbstractFigure {

    public Retangle(Double side, Double width){
        this.vertices.add(side);
        this.vertices.add(width);
    }

    @Override
    public Double calculateArea() {
        return this.vertices.get(0) * this.vertices.get(1);
    }
}
