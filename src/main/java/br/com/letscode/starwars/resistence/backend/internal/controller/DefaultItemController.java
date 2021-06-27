package br.com.letscode.starwars.resistence.backend.internal.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.starwars.resistence.backend.api.controller.ItemController;
import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveItens;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(ItemController.class)
public class DefaultItemController implements ItemController {
    
    private final RetrieveItens retrieveItens;
    
    @Override
    public List<Item> retrieveAll() {
        return retrieveItens.execute();
    }

    @Override
    public Item retrieveById(Long id) {
        return retrieveItens.execute(id);
    }


}
