package br.com.letscode.starwars.resistence.core.internal.usecases;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.letscode.starwars.resistence.core.api.data.Localizacao;
import br.com.letscode.starwars.resistence.core.api.repository.LocalizacaoRepository;
import br.com.letscode.starwars.resistence.core.api.usecases.CreateLocalizacao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(CreateLocalizacao.class)
public class DefaultCreateLocalizacao implements CreateLocalizacao {

    private final LocalizacaoRepository localizacaoRepository;

    @Override
    public Localizacao execute(@Valid Localizacao localizacao) {
        Optional<Localizacao> existente = localizacaoRepository.findByLatituteAndLongitudeAndNomeAndGalaxiaAndBase(
                localizacao.getLatitute(), localizacao.getLongitude(), localizacao.getNome(), localizacao.getGalaxia(),
                localizacao.getBase());
        if (existente.isPresent()) {
            return existente.get();
        } else {
            try {
                return localizacaoRepository.save(localizacao);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
