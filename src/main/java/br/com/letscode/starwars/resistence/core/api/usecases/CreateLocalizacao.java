package br.com.letscode.starwars.resistence.core.api.usecases;

import br.com.letscode.starwars.resistence.core.api.data.Localizacao;

/**
 * Cria uma localização
 * 
 * @author Bruno Pinho
 *
 */
public interface CreateLocalizacao {

    /**
     * Cria uma nova localizacao
     * 
     * @param localizacao a ser criada
     * @return localizacao criada
     */
    Localizacao execute(Localizacao localizacao);
}
