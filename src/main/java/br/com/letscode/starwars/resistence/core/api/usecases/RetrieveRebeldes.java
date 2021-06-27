package br.com.letscode.starwars.resistence.core.api.usecases;

import java.util.List;

import br.com.letscode.starwars.resistence.core.api.data.Rebelde;

/**
 * UseCase responsável por buscar rebeldes
 * 
 * @author Bruno Pinho
 *
 */
public interface RetrieveRebeldes {

    /**
     * Busca todos os rebeldes
     * 
     * @return Lista contendo todos os rebeldes
     */
    List<Rebelde> execute();

    /**
     * Busca os rebeldes pelo tipo - Traidores ou não traidores
     * 
     * @param traidores Buscar os traidores ?
     * @return Rebeldes traidores ou não traidores, dependendo do parametro em
     *         questão
     */
    List<Rebelde> execute(Boolean traidores);

    /**
     * Busca rebelde pelo ID
     * 
     * @param id do rebelde a ser buscado
     * @return Rebelde
     */
    Rebelde execute(Long id);

}
