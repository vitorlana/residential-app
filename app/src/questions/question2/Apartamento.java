package org.question2;

public class Apartamento {

    double area;

    double fracaoIdeal;

    public Apartamento(Double area){
        setArea(area);
    }

    public void setFracaoIdeal(double fracaoIdeal) {
        this.fracaoIdeal = fracaoIdeal;
    }

    public double getFracaoIdeal() {
        return fracaoIdeal;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
