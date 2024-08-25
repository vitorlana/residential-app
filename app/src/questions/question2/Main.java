package org.question2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        Condominio condominio = new Condominio();
        Bloco bloco = new Bloco();
        Apartamento ap1 =  new Apartamento(70.0);
        Apartamento ap2 = new Apartamento(100.0);


        ArrayList<Apartamento>  apartamentos = new ArrayList<Apartamento>();
        apartamentos.add(ap1);
        apartamentos.add(ap2);

        bloco.setApartamentos(apartamentos);
        bloco.setDespesastotais(10000.0);

        ArrayList<Bloco> blocos = new ArrayList<Bloco>();
        blocos.add(bloco);


         System.out.println(bloco.getValorDoCondominio(ap1));
         System.out.println(bloco.getValorDoCondominio(ap2));
    }
}
