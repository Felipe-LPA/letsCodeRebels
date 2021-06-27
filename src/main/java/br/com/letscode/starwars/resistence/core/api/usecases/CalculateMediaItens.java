package br.com.letscode.starwars.resistence.core.api.usecases;

import java.util.Map;

import br.com.letscode.starwars.resistence.core.api.data.Item;

/**
 * Calcula quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por
 * rebelde).
 * 
 * @author Bruno Pinho
 *
 */
public interface CalculateMediaItens {

    Map<Item, Long> execute();
}
