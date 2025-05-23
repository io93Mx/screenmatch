package com.aluracursos.screenmatch;

// import java.security.Principal;
// import java.util.ArrayList;
// import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aluracursos.screenmatch.principal.Principal;

// import com.aluracursos.screenmatch.model.DatosEpisodio;
// import com.aluracursos.screenmatch.model.DatosSerie;
// import com.aluracursos.screenmatch.model.DatosTemporadas;
// import com.aluracursos.screenmatch.service.ConsumoAPI;
// import com.aluracursos.screenmatch.service.ConvierteDatos;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	//mi API KEY de OMDB
    //http://www.omdbapi.com/?t=matrix&apikey=464d1cb3

	@Override
	public void run(String... args) throws Exception {		

		// //aparece al principio
		System.out.println("\n Hola mundo desde Spring \n");

		Principal principal = new Principal();
		principal.muestraElMenu();

	}		

		// var consumoAPI = new ConsumoAPI();
		// //metodo de consumoAPI.java, espera una String
		// var json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&apikey=464d1cb3");
		
		//consultar otra API, imagen de cafe aleatoria, aunque ya no esta disopponible
		//var json1 = consumoAPI.obtenerDatos("http://www.coffe.alexflipnote.dev/random.json");
		
		// System.out.println(json + "\n");
		// //System.out.println(json1);
		
		// ConvierteDatos conversor = new ConvierteDatos();
		// var datos = conversor.obtenerDatos(json, DatosSerie.class);
		// System.out.println(datos + "\n");

		// //convertir los datos del episodio
		// json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=464d1cb3");
		// DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
		// System.out.println(episodios + "\n");

		//lista de episodios
		// List<DatosTemporadas> temporadas = new ArrayList<>();
		// for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
		// 	json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&Season=" + i + "&apikey=464d1cb3");
		// 	var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
		// 	temporadas.add(datosTemporadas);

		// }

		// temporadas.forEach(System.out::println);

	


	

}
