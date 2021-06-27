package br.com.letscode.starwars.resistence.core.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.starwars.resistence.core.api.data.Localizacao;

/**
 * Repositório da classe {@link Localizacao}
 * 
 * @author Bruno Pinho
 *
 */
@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

    Optional<Localizacao> findById(Long id);

    Optional<Localizacao> findByLatituteAndLongitudeAndNomeAndGalaxiaAndBase(double latitute, double longitude,
            String nome, String galaxia, String base);

}