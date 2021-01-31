
package br.com.gok.templateapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.gok.templateapi.domain.Planet;

/**
 * Access interface to the repository of the Planet table.
 *
 * @author Marconi Motta
 * @since 29 de jan de 2021
 */

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

	List<Planet> findByNameLike(String name);

	List<Planet> findByPopulationBetween(Long populationMin, Long populationMax);

	Optional<Planet> findByName(String name);

}
