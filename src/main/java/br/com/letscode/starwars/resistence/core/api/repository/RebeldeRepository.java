package br.com.letscode.starwars.resistence.core.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.starwars.resistence.core.api.data.Rebelde;

/**
 * Repositório da classe {@link Rebelde}
 * 
 * @author Bruno Pinho
 *
 */
@Repository
public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {

    Optional<Rebelde> findById(Long id);
}