

package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosEpisodio(@JsonAlias("Title") String titulo,
@JsonAlias("Episode") Integer numeroEpisodio,
@JsonAlias("imdbRating") String evalucion,
@JsonAlias("Released") String fechaDeLanzamiento
) {

    //http://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=464d1cb3

    





}