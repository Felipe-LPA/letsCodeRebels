package br.com.letscode.starwars.resistence.core.internal.usecases;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.letscode.starwars.resistence.core.api.usecases.RebeldeTraidor;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(RebeldeTraidor.class)
public class DefaultRebeldeTraidor implements RebeldeTraidor {

    private final RetrieveRebeldes retrieveRebeldes;
    
    @Override
    public boolean denunciar(long id) {
        return retrieveRebeldes.execute(id).denunciar();
    }

    @Override
    public boolean status(long id) {
        return retrieveRebeldes.execute(id).isTraidor();
    }
}
