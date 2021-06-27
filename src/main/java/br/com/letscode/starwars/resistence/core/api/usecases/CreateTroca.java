package br.com.letscode.starwars.resistence.core.api.usecases;

import br.com.letscode.starwars.resistence.core.api.data.Troca;

/**
 * UseCase responsável por criar trocas
 * 
 * @author Bruno Pinho
 *
 */
public interface CreateTroca {

    /**
     * Cria uma troca
     * 
     * @param troca a ser criada
     * @return troca criada
     */
    Troca execute(Troca troca);

    /**
     * Efetua uma troca
     * 
     * @param id        da troca
     * @param rebeldeId Id do rebelde
     * @return Troca efetuada
     */
    Troca execute(Long id, Long rebeldeId);
}
