package com.aluracursos.screenmatch.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {

    public void muestraEjemplo() {

        List<String> nombres = Arrays.asList("Brenda","Luis","Maria","Fernanda","Eric","Genesys");
        //stream permite acoplar mas metodos 

        nombres.stream().sorted().forEach(System.out::println);//Brenda, Eric, Fernanda, Genesys, Luis, Maria

        nombres.stream().sorted().limit(4).forEach(System.out::println);//Brenda, Eric, Fernanda, Genesys
        //limit cuantos aparecen

        nombres.stream().sorted().limit(2).filter(n -> n.startsWith("E")).map(n -> n.toUpperCase()).forEach(System.out::println);//ERIC 
    }



}
