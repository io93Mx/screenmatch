package com.aluracursos.screenmatch.service;

//I es de interface
public interface IConvierteDatos {

    //metodo para obtener datos, tipos de datos genericos <>
    <T> T obtenerDatos(String json, Class <T> clase);

}
