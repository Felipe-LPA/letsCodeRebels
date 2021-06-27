package br.com.letscode.starwars.resistence.core.internal.usecases;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.letscode.starwars.resistence.core.api.data.Item;
import br.com.letscode.starwars.resistence.core.api.repository.ItemRepository;
import br.com.letscode.starwars.resistence.core.api.usecases.RetrieveItens;
import br.com.letscode.starwars.resistence.utils.MessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@ConditionalOnSingleCandidate(RetrieveItens.class)
@Slf4j
public class DefaultRetrieveItens implements RetrieveItens {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> execute() {
        return itemRepository.findAll();
    }

    @Override
    public Item execute(Long id) {
        logger.info("ID: {}", id);
        if(id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id do item é nulo");
        }
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item nao encontrado"));
    }

    @Override
    public Item execute(String nome) {
        logger.info("Nome: {}", nome);
        if(nome == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nome do item é nulo");
        }
        return itemRepository.findByNome(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item nao encontrado"));
    }
    
    public Item atachItem(Item item) {
        logger.info("Atachando o item: {}", item == null ? "null" : MessageMapper.toJson(item));
        try {
            if(item.getId() != null) {                
                return this.execute(item.getId());
            } else {
                return this.execute(item.getNome());                
            }
        } catch (ResponseStatusException e) {
            return this.execute(item.getNome());            
        }
    }

}
