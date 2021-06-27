package br.com.letscode.starwars.resistence.core.api.usecases;

/**
 * Calcula a media de rebeldes traidores e não traidores
 * 
 * @author Bruno Pinho
 *
 */
public interface CalculateMediaRebeldes {

    /**
     * Calcula a media de rebeldes
     * 
     * @param traidores rebeldes traidores?
     * @return resultado da media
     */
    double execute(boolean traidores);

}
