package br.com.letscode.starwars.resistence.core.internal.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.letscode.starwars.resistence.core.api.data.Rebelde;
import br.com.letscode.starwars.resistence.core.api.repository.RebeldeRepository;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(RetrieveRebeldes.class)
public class DefaultRetrieveRebeldes implements RetrieveRebeldes {

    private final RebeldeRepository rebeldeRepository;

    @Override
    public List<Rebelde> execute() {
        return rebeldeRepository.findAll();
    }

    @Override
    public Rebelde execute(Long id) {
        return rebeldeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rebelde nao encontrado"));
    }

    @Override
    public List<Rebelde> execute(Boolean traidores) {
        if(traidores == null) {
            return execute();
        }
        return execute().stream()
                .filter(rebelde -> rebelde.isTraidor() == traidores.booleanValue())
                .collect(Collectors.toList());
    }

}
