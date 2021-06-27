package br.com.letscode.starwars.resistence.backend.internal.controller;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.backend.api.controller.ReportController;
import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.core.api.usecases.CalculateMediaItens;
import br.com.letscode.starwars.resistence.core.api.usecases.CalculateMediaRebeldes;
import br.com.letscode.starwars.resistence.core.api.usecases.CalculatePontosRebeldes;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(ReportController.class)
public class DefaultReportController implements ReportController {

    private final CalculateMediaRebeldes calculateMediaRebeldes;
    private final CalculatePontosRebeldes calculatePontosRebeldes;
    private final CalculateMediaItens calculateMediaItens;

    @Override
    public double traidores() {
        return calculateMediaRebeldes.execute(true);
    }

    @Override
    public double rebeldes() {
        return calculateMediaRebeldes.execute(false);
    }

    @Override
    public Map<Item, Long> mediaItens() {
        return calculateMediaItens.execute();
    }

    @Override
    public double pontosRebeldes(Boolean traidores) {
        return calculatePontosRebeldes.execute(traidores);
    }

}
