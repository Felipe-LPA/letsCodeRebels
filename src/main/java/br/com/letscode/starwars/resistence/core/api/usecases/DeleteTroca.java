package br.com.letscode.starwars.resistence.core.api.usecases;

/**
 * UseCase responsável por deletar troca
 * 
 * @author Bruno Pinho
 *
 */
public interface DeleteTroca {

    /**
     * Deleta troca pelo ID
     * 
     * @param id da troca a ser buscado
     * @return Troca
     */
    void execute(Long id);

}
