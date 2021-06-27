package br.com.letscode.starwars.resistence.core.api.usecases;

/**
 * Calcula a quantidade de pontos dos rebeldes
 * 
 * @author Bruno Pinho
 *
 */
public interface CalculatePontosRebeldes {

    /**
     * Calcula a quantidade de pontos de todos os rebeldes
     * 
     * @return quantidade de pontos
     */
    double execute();

    /**
     * Calcula a quantidade de pontos dos rebeldes traidores ou não traidores
     * 
     * @param traidores tipos de rebeldes a serem calculados os pontos
     * @return quantidade de pontos
     */
    double execute(Boolean traidores);

}
