package br.com.letscode.starwars.resistence.core.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.starwars.resistence.core.api.data.Troca;

/**
 * Repositório da classe {@link Troca}
 * 
 * @author Bruno Pinho
 *
 */
@Repository
public interface TrocaRepository extends JpaRepository<Troca, Long> {

    Optional<Troca> findById(Long id);
    
}