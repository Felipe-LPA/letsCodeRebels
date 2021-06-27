package br.com.letscode.starwars.resistence.backend.internal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.backend.api.controller.TrocaController;
import br.com.letscode.starwars.resistence.core.api.data.Troca;
import br.com.letscode.starwars.resistence.core.api.usecases.CreateTroca;
import br.com.letscode.starwars.resistence.core.api.usecases.DeleteTroca;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveTrocas;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(TrocaController.class)
public class DefaultTrocaController implements TrocaController {

    private final RetrieveTrocas retrieveTrocas;
    private final DeleteTroca deleteTroca;
    private final CreateTroca createTroca;

    @Override
    public List<Troca> retrieveAll() {
        return retrieveTrocas.execute();
    }

    @Override
    public Troca retrieveById(Long id) {
        return retrieveTrocas.execute(id);
    }

    @Override
    public Troca createTroca(@Valid Troca troca) {
        return createTroca.execute(troca);
    }

    @Override
    public void deleteTroca(Long id) {
        deleteTroca.execute(id);
    }

    @Override
    public Troca doTroca(Long id, Long rebeldedId) {
        return createTroca.execute(id, rebeldedId);
    }

}
