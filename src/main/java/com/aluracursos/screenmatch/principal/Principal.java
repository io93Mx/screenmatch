package com.aluracursos.screenmatch.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

public class Principal {

    private Scanner teclado = new Scanner(System.in);

    ///crear instancia de consumo API
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private final String URL_BASE = "http://www.omdbapi.com/?t="; ///final determina que es una constante
    private final String API_KEY = "&apikey=464d1cb3";

    private ConvierteDatos conversor = new ConvierteDatos();



    public void muestraElMenu() {

        System.out.println("Escribe el nombre de la serie que desea buscar");

        ///buscalos datos generales de la serie
        var nombreSerie = teclado.nextLine();
        ///Tambien podria ser, String nombreSerie = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);

        ///System.out.println(json);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        ///busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
			json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
			var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
			temporadas.add(datosTemporadas);
		}
		temporadas.forEach(System.out::println);



        ///mostrar solo el titulo de los episodios para las temporadas

        for (int i = 0; i < datos.totalDeTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for (int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }


        ///expresion lamba, menos lineas de codigo  
        ///temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));



        ///convertir las informaciones a una lista de tipo: Datosepidsodio

        List<DatosEpisodio> datosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(Collectors.toList());
        ///collect es editable, to list no se edita
        ///List<DatosEpisodio> datosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).toList();



        ///top 5 episodios
        System.out.println("Top 5 mejores episodios");

        datosEpisodios.stream()
        .filter(e -> !e.evalucion().equalsIgnoreCase("N/A"))
        .peek(e -> System.out.println("Primer filtro (N/A)" + e))
        .sorted(Comparator.comparing(DatosEpisodio::evalucion).reversed())
        .peek(e -> System.out.println("Segundo filtro de ordenacion (M>m)" + e))
        .map(e -> e.titulo().toUpperCase())
        .limit(5)
        .peek(e -> System.out.println("Tercer filtro mayusculas (m/M)" + e))
        .forEach(System.out::println);

        ///datosEpisodios.stream() permite aplicar operaciones funcionales
        /// .filter(e -> !e.evalucion().equalsIgnoreCase("N/A")) filtra todos los que sean diferentes a N/A
        /// .sorted(Comparator.comparing(DatosEpisodio::evalucion).reversed()) ordena los datosEpisodio por evalucion de menor a mayor, con reversed es de mayor a menor
        /// .limit(5) despues de ordenar, se limita a los primeros 5 elementos
        /// .forEach(System.out::println); imprime cada elemento, tambien es igual a
        /// x -> System.out.println(x); para cada X, imprime X
        
        ///¿Es una lambda, .forEach(System.out::println); ?
        ///No exactamente, no es una lambda, pero es muy similar. Es una referencia a método (en inglés: method reference), que es una forma más concisa de escribir una lambda que simplemente llama a un método existente.



        ///convirtiendo los datos a una lista del tipo episodio

        List<Episodio> episodios = temporadas.stream()
        .flatMap(t -> t.episodios().stream()
        .map(d -> new Episodio(t.numero(),d)))
        .collect(Collectors.toList());

        episodios.forEach(System.out::println);



        ///busqueda de episodios a partir de x año

        System.out.println("Indica desde que año dseas buscar los episodios");
        var fecha = teclado.nextInt();
        teclado.nextLine();

        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
        ///desde la fecha del usuario, del mes y dia 1

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
        .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda)).forEach(e -> {
            System.out.println(
                "Temporada " + e.getTemporada() +
                " Episodio " + e.getTitulo() +
                " Fecha de Lanzamiento " + e.getFechaDeLanzamiento().format(dtf));
            System.out.println("yuyuyu");
        });
        ///.forEach(e -> {}) es para varias instrucciones
        ///puede ser (e -> System.out.println());



        ///busca episodios por un pedazo de titulo

        System.out.println("Escribe el titulo del episodio que quieres ver");
        var pedazoTitulo = teclado.nextLine();
        ///error
        ///episodios.stream().filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo).toUpperCase()).findFirst();
        ///opcional, puede o no contener un episodio
        Optional<Episodio> episodioBuscado = episodios.stream().filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
        .findFirst();
        ///episodios.stream().filter(e -> e.getTitulo()) 
        ///Para cada episodio traer el episodio.titulo, traeer el titulo del episodio
        ///no imprime, debe haber un sout
        if (episodioBuscado.isPresent()) {
            System.out.println(" Episodio encontrado");
            System.out.println(" Los datos son: " + episodioBuscado.get());///trae todos los datos
            ///episodioBuscado.getTitulo() trae solo el titulo
        } else {
            System.out.println("Episodio no encontrado");
        }



        ///Mapeando
        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
        .filter(e -> e.getEvaluacion() > 0.0)
        .collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evaluacionesPorTemporada);
        ///.filter(e -> e.getEvaluacion() > 0.0) filtra las que son mayores de 0.0



        ///genera estadisticas prestablecidas
        DoubleSummaryStatistics est = episodios.stream()
        .filter(e -> e.getEvaluacion() > 0.0)
        .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        ///System.out.println(est);//todas las estadisticas (count, sum, min, average, max)
        System.out.println("La media de las evaluciones es " + est.getAverage());
        System.out.println("El episodio mejor evaluado es " + est.getMax());
        System.out.println("El peor evaluado es " + est.getMin());







    }

}
