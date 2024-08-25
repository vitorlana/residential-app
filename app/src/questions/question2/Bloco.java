package org.question2;

import java.util.ArrayList;

public class Bloco {

    ArrayList<Apartamento> apartamentos;

    double despesastotais;

    public void setApartamentos(ArrayList<Apartamento> apartamentos) {
        this.apartamentos = apartamentos;

        for (Apartamento apartamento : apartamentos)
        {
            this.setFracaoIdealPorApartamento(apartamento);
        }

    }

    public void setFracaoIdealPorApartamento(Apartamento apartamento){
        apartamento.setFracaoIdeal(apartamento.getArea()/this.getAreaTotalAparamentos());
    }

    public double getAreaTotalAparamentos(){
        double total = 0;
        for (Apartamento apartamento : this.apartamentos)
        {
            total += apartamento.getArea(); //tinha invertido o =+ ai nao estava somando o total
        }
        return total;
    }

    public void setDespesastotais(double despesastotais) {
        this.despesastotais = despesastotais;
    }

    public double getValorDoCondominio(Apartamento apartamento){
        return this.getDespesastotais() * apartamento.getFracaoIdeal();
    }


    public double getDespesastotais() {
        return despesastotais;
    }
}
