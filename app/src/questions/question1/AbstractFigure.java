package org.question1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class AbstractFigure {


    ArrayList<Double> vertices = new ArrayList<>();

    public void setVertices(ArrayList<Double> vertices) {
        this.vertices = vertices;
    }

    public abstract Double calculateArea();
}
