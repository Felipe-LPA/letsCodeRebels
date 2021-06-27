package br.com.letscode.starwars.resistence.core.internal.usecases;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.letscode.starwars.resistence.core.api.repository.TrocaRepository;
import br.com.letscode.starwars.resistence.core.api.usecases.DeleteTroca;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveTrocas;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(DeleteTroca.class)
public class DefaultDeleteTroca implements DeleteTroca {

    private final TrocaRepository trocaRepository;
    private final RetrieveTrocas retrieveTrocas;
    
    @Override
    public void execute(Long id) {
        retrieveTrocas.execute(id);
        trocaRepository.deleteById(id);
    }

}
