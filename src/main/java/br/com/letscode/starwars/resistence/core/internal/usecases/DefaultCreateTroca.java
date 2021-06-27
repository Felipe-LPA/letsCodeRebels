package br.com.letscode.starwars.resistence.core.internal.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.core.api.data.Rebelde;
import br.com.letscode.starwars.resistence.core.api.data.Troca;
import br.com.letscode.starwars.resistence.core.api.repository.TrocaRepository;
import br.com.letscode.starwars.resistence.core.api.usecases.CreateRebelde;
import br.com.letscode.starwars.resistence.core.api.usecases.CreateTroca;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveItens;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveTrocas;
import br.com.letscode.starwars.resistence.utils.MessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(CreateTroca.class)
@Slf4j
public class DefaultCreateTroca implements CreateTroca {

    private final TrocaRepository trocaRepository;
    private final RetrieveTrocas retrieveTrocas;
    private final RetrieveRebeldes retrieveRebeldes;
    private final RetrieveItens retrieveItens;
    private final CreateRebelde createRebelde;

    @Override
    public Troca execute(Troca troca) {
        validate(troca);
        troca.setContraParte(null);
        return trocaRepository.save(troca);
    }

    @Override
    @Transactional
    public Troca execute(Long id, Long rebeldeId) {
        Troca troca = retrieveTrocas.execute(id);
        Rebelde rebelde = retrieveRebeldes.execute(rebeldeId);

        validate(troca);
        validateContraParte(troca, rebelde);
        processTrocaIndividual(troca.getProcura(), troca.getOferta(), retrieveRebeldes.execute(troca.getParte()));
        processTrocaIndividual(troca.getOferta(), troca.getProcura(), rebelde);
        return trocaRepository.save(troca.setContraParte(rebelde.getId()));
    }

    private void processTrocaIndividual(List<Item> recebendo, List<Item> enviando, Rebelde rebelde) {
        if (rebelde.isTraidor()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Traidor não pode efetuar troca");
        }
        if (!rebelde.getInventario().removeAll(enviando)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Rebelde não possui os itens para efetuar a troca");
        }
        rebelde.getInventario().addAll(recebendo);
        createRebelde.execute(rebelde);
    }

    private void validateContraParte(Troca troca, Rebelde rebelde) {
        if (rebelde.isTraidor()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Troca inválida. Traidor não pode aceitar uma oferta");
        }

        if (!hasItens(rebelde, troca.getProcura())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Troca inválida. Rebelde não possui os itens necessários para efetuar a troca");
        }
    }

    private void validate(Troca troca) {
        logger.info("Validando a troca: {}", MessageMapper.toJson(troca));
        Rebelde parte = retrieveRebeldes.execute(troca.getParte());
        if (parte.isTraidor()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Troca inválida. Criador da oferta é um traidor");
        }
        atachItens(troca);
        if (!hasItens(parte, troca.getOferta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Troca inválida. Rebelde que criou a troca não possui mais os itens necessários para efetua-la");
        }
        Long pontoOferta = troca.getOferta().stream().mapToLong(Item::getPonto).sum();
        Long pontoProcura = troca.getProcura().stream().mapToLong(Item::getPonto).sum();
        logger.info("Pontos oferta: {} e pontos procura: {}", pontoOferta, pontoProcura);
        if (pontoOferta.compareTo(pontoProcura) != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Troca inválida. Pontos divergentes");
        }
    }

    private Boolean hasItens(Rebelde rebelde, List<Item> itens) {
        logger.info("Validando os itens {} do rebelde {}", MessageMapper.toJson(itens), MessageMapper.toJson(rebelde));
        if (rebelde.getInventario().containsAll(itens)) {
            logger.info("Contem individualmente os itens");
            List<Item> rebelItens = new ArrayList<>();
            rebelItens.addAll(rebelde.getInventario());
            return itens.stream().map(rebelItens::remove).reduce(Boolean.TRUE, Boolean::logicalAnd);
        } else {
            logger.error("Não contém individualmente os itens");
            return Boolean.FALSE;
        }
    }

    private void atachItens(Troca troca) {
        troca.setOferta(troca.getOferta().stream().map(retrieveItens::atachItem).collect(Collectors.toList()));
        troca.setProcura(troca.getProcura().stream().map(retrieveItens::atachItem).collect(Collectors.toList()));
    }

}
