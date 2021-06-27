package br.com.letscode.starwars.resistence.core.api.usecases;

import java.util.List;

import br.com.letscode.starwars.resistence.core.api.data.Troca;

/**
 * UseCase responsável por buscar trocas
 * 
 * @author Bruno Pinho
 *
 */
public interface RetrieveTrocas {

    /**
     * Busca todos as trocas ainda não realizadas
     * 
     * @return Lista contendo todos as trocas
     */
    List<Troca> execute();

    /**
     * Busca troca pelo ID
     * 
     * @param id da troca a ser buscado
     * @return Troca
     */
    Troca execute(Long id);

}
