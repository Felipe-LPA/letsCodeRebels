package br.com.letscode.starwars.resistence.core.internal.usecases;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.letscode.starwars.resistence.core.api.usecases.CalculateMediaRebeldes;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(CalculateMediaRebeldes.class)
@Slf4j
public class DefaultCalculateMediaRebeldes implements CalculateMediaRebeldes {

    private final RetrieveRebeldes retrieveRebeldes;

    @Override
    public double execute(boolean traidores) {
        double total = retrieveRebeldes.execute().size();
        double parcial = retrieveRebeldes.execute(traidores).size();
        logger.info("Parcial: {} / Total: {} / Porcentagem: {}", parcial, total, parcial / total);
        return parcial / total;
    }

}
