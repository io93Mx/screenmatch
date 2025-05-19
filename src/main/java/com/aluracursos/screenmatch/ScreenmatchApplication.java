package com.aluracursos.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aluracursos.screenmatch.service.ConsumoAPI;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	//mi API KEY
    //http://www.omdbapi.com/?t=matrix&apikey=464d1cb3

	@Override
	public void run(String... args) throws Exception {
		//esto es por default pero no nos sirve ahorita
		
		//throw new UnsupportedOperationException("Unimplemented method 'run'");
		
		//aparece al principio
		System.out.println("Hola mundo desde Spring");

		var consumoAPI = new ConsumoAPI();
		//metodo de consumoAPI.java, espera una String
		var json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&apikey=464d1cb3");
		//consultar otra API, imagen de cafe aleatoria, aunque ya no esta disopponible
		//var json = consumoAPI.obtenerDatos("http://www.coffe.alexflipnote.dev/random.json");
		System.out.println(json);
	}

}
