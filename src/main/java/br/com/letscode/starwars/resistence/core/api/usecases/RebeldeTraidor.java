package br.com.letscode.starwars.resistence.core.api.usecases;

/**
 * UseCase que verifica status de traicao e realiza denuncias
 * 
 * @author Bruno Pinho
 *
 */
public interface RebeldeTraidor {

    /**
     * Faz uma denuncia de que esse é um rebelde traidor
     * 
     * @param id do rebelde
     * @return Se ele é ou não um traidor
     */
    boolean denunciar(long id);

    /**
     * Verifica se o rebelde é um traidor
     * 
     * @param id do rebelde
     * @return Se ele é ou não um traidor
     */
    boolean status(long id);
}
