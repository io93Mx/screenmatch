package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//ignora los datos que no serializamos
@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosSerie(@JsonAlias ("Title") String titulo, 
@JsonAlias ("totalSeasons") Integer totalDeTemporadas, 
@JsonAlias ("imdbRating")String evaluacion
//@JsonAlias ("") sirve para leer 
//@JsonProperty (""), sirve para leer y escribir una propiedad
) {

}
