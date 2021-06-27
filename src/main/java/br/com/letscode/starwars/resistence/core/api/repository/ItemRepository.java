package br.com.letscode.starwars.resistence.core.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.starwars.resistence.core.api.data.Item;

/**
 * Repositório da classe {@link Item}
 * 
 * @author Bruno Pinho
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);
    
    Optional<Item> findByNome(String nome);
}