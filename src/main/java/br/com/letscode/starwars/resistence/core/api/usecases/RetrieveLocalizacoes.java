package br.com.letscode.starwars.resistence.core.api.usecases;

import java.util.Optional;

import br.com.letscode.starwars.resistence.core.api.data.Localizacao;

/**
 * UseCase responsável por buscar lozalizacoes
 * 
 * @author Bruno Pinho
 *
 */
public interface RetrieveLocalizacoes {

    /**
     * Busca uma localizacao pelo Id de um rebelde
     * 
     * @param id do rebelde
     * @return Localizacao do rebelde
     */
    Localizacao executeByRebeldeId(long id);

    /**
     * Encontra uma localização pelo Id dela
     * 
     * @param id da localizacao
     * @return localizacao
     */
    Optional<Localizacao> execute(long id);
}
