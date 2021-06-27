package br.com.letscode.starwars.resistence.core.internal.usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.core.api.usecases.CalculateMediaItens;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveRebeldes;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(CalculateMediaItens.class)
public class DefaultCalculateMediaItens implements CalculateMediaItens {

    private final RetrieveRebeldes retrieveRebeldes;

    @Override
    public Map<Item, Long> execute() {
        List<Item> itens = new ArrayList<>();
        retrieveRebeldes.execute().stream().forEach(rebelde -> itens.addAll(rebelde.getInventario()));
        return itens.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting()));
    }

}
