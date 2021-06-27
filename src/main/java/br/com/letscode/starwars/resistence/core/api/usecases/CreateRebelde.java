package br.com.letscode.starwars.resistence.core.api.usecases;

import br.com.letscode.starwars.resistence.core.api.data.Localizacao;
import br.com.letscode.starwars.resistence.core.api.data.Rebelde;

/**
 * UseCase responsável por adicionar um novo rebelde para a rebelião
 * 
 * @author Bruno Pinho
 *
 */
public interface CreateRebelde {

    Rebelde execute(Rebelde rebelde);


    /**
     * Atualiza uma localizacao de um rebelde
     * 
     * @param localizacao atualizada
     * @param id          do rebelde
     * @return localizacao atualizada do rebelde
     */
    Localizacao execute(Localizacao localizacao, long id);
}
