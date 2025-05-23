package com.aluracursos.screenmatch.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

public class Principal {

    private Scanner teclado = new Scanner(System.in);

    //crear instancia de consumo API
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private final String URL_BASE = "http://www.omdbapi.com/?t="; //final determina que es una constante
    private final String API_KEY = "&apikey=464d1cb3";

    private ConvierteDatos conversor = new ConvierteDatos();


    public void muestraElMenu() {

        System.out.println("Escribe el nombre de la serie que desea buscar");

        //buscalos datos generales de la serie
        var nombreSerie = teclado.nextLine();
        //Tambien podria ser, String nombreSerie = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);

        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
			json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
			var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}

		temporadas.forEach(System.out::println);


    }

}
