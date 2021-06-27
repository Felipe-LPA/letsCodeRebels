package br.com.letscode.starwars.resistence.core.api.usecases;

import java.util.List;

import br.com.letscode.starwars.resistence.core.api.data.Item;

/**
 * UseCase responsável por buscar itens
 * 
 * @author Bruno Pinho
 *
 */
public interface RetrieveItens {

    /**
     * Busca todos os itens
     * 
     * @return Lista contendo todos os itens
     */
    List<Item> execute();

    /**
     * Busca item pelo ID
     * 
     * @param id do item a ser buscado
     * @return Item
     */
    Item execute(Long id);

    /**
     * Busca item pelo nome
     * 
     * @param nome do item a ser buscado
     * @return Item
     */
    Item execute(String nome);

    /**
     * Atachando um item
     * 
     * @param item a ser atachado
     * @return item atachado
     */
    Item atachItem(Item item);

}
