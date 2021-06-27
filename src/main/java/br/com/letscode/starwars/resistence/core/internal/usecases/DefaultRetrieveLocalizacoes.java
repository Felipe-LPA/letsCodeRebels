package br.com.letscode.starwars.resistence.core.internal.usecases;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.letscode.starwars.resistence.core.api.data.Localizacao;
import br.com.letscode.starwars.resistence.core.api.repository.LocalizacaoRepository;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveLocalizacoes;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(RetrieveLocalizacoes.class)
public class DefaultRetrieveLocalizacoes implements RetrieveLocalizacoes {

    private final RetrieveRebeldes retrieveRebeldes;
    private final LocalizacaoRepository localizacaoRepository;

    @Override
    public Localizacao executeByRebeldeId(long id) {
        return retrieveRebeldes.execute(id).getLocalizacao();
    }

    @Override
    public Optional<Localizacao> execute(long id) {
        return localizacaoRepository.findById(id);
    }

}
