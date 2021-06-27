package br.com.letscode.starwars.resistence.core.internal.usecases;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.core.api.data.Rebelde;
import br.com.letscode.starwars.resistence.core.api.usecases.CalculatePontosRebeldes;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(CalculatePontosRebeldes.class)
public class DefaultCalculatePontosRebeldes implements CalculatePontosRebeldes {

    private final RetrieveRebeldes retrieveRebeldes;

    @Override
    public double execute() {
        return retrieveRebeldes.execute().stream()
                .mapToDouble(this::calculaPontoIndividual)
                .sum();    
    }

    @Override
    public double execute(Boolean traidores) {
        if (traidores == null) {
            return this.execute();
        }
        return retrieveRebeldes.execute(traidores).stream()
                .mapToDouble(this::calculaPontoIndividual)
                .sum();
    }

    private double calculaPontoIndividual(Rebelde rebelde) {
        return rebelde.getInventario().stream()
                .mapToDouble(Item::getPonto)
                .sum();
    }

}
