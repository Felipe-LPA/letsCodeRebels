package br.com.letscode.starwars.resistence.core.internal.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.letscode.starwars.resistence.core.api.data.Troca;
import br.com.letscode.starwars.resistence.core.api.repository.TrocaRepository;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveTrocas;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(RetrieveTrocas.class)
public class DefaultRetrieveTrocas implements RetrieveTrocas {

    private final TrocaRepository trocaRepository;

    @Override
    public List<Troca> execute() {
        return trocaRepository.findAll().stream()
                .filter(troca -> troca.getContraParte() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Troca execute(Long id) {
        Troca troca = trocaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Troca nao encontrado"));
        if (troca.getContraParte() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Troca já realizada");
        }
        return troca;
    }

}
