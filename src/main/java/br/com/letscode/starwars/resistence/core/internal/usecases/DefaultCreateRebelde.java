package br.com.letscode.starwars.resistence.core.internal.usecases;

import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.letscode.starwars.resistence.core.api.data.Localizacao;
import br.com.letscode.starwars.resistence.core.api.data.Rebelde;
import br.com.letscode.starwars.resistence.core.api.repository.RebeldeRepository;
import br.com.letscode.starwars.resistence.core.api.usecases.CreateLocalizacao;
import br.com.letscode.starwars.resistence.core.api.usecases.CreateRebelde;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveItens;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveLocalizacoes;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(CreateRebelde.class)
public class DefaultCreateRebelde implements CreateRebelde {

    private final RebeldeRepository rebeldeRepository;
    private final RetrieveRebeldes retrieveRebeldes;
    private final RetrieveItens retrieveItens;
    private final RetrieveLocalizacoes retrieveLocalizacoes;
    private final CreateLocalizacao createLocalizacao;
    
    @Override
    public Rebelde execute(Rebelde rebelde) {
        if(rebelde.getLocalizacao().getId() != null) {            
            retrieveLocalizacoes.execute(rebelde.getLocalizacao().getId())
                    .ifPresent(rebelde::setLocalizacao);
        } else {
            rebelde.setLocalizacao(createLocalizacao.execute(rebelde.getLocalizacao()));
        }
        rebelde.setInventario(rebelde.getInventario().stream()
                .map(retrieveItens::atachItem)
                .collect(Collectors.toList()));
        return rebeldeRepository.save(rebelde);
    }

    @Override
    public Localizacao execute(Localizacao localizacao, @NotNull long id) {
        Rebelde rebelde = retrieveRebeldes.execute(id);
        rebelde.setLocalizacao(localizacao);
        return this.execute(rebelde).getLocalizacao();
    }

}
