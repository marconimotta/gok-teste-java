package br.com.gok.templateapi.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.gok.templateapi.dto.PlanetDTO;
import br.com.gok.templateapi.service.PlanetService;

/**
 * Class of access to planet endpoints.
 *
 * @author Marconi Motta
 * @since 30 de jan de 2021
 */
@RequestMapping("/api/template/v1/planets")
@RestController
public class PlanetResource {

	@Autowired
	private PlanetService planetService;

	/**
	 *
	 * Service that lists all registered planets.
	 *
	 * @return List
	 */
	@GetMapping("/")
	public ResponseEntity<?> listAll() {
		final List<PlanetDTO> listPlanets = PlanetDTO.convertToList(planetService.listPlanets());
		return ResponseEntity.ok(listPlanets);
	}

	/**
	 *
	 * Service that lists all registered planets in the maximum and minimum range of the population field.
	 *
	 * @param populationMin
	 * @param populationMax
	 * @return List
	 */
	@GetMapping("/filter/population")
	public ResponseEntity<?> listByPopulationRange(@RequestParam final Long populationMin, @RequestParam final Long populationMax) {
		final List<PlanetDTO> listPlanets = PlanetDTO.convertToList(planetService.listPlanetsByPopulation(populationMin, populationMax));
		return ResponseEntity.ok(listPlanets);
	}

	/**
	 * Service that lists all registered planets filtered by the name field.
	 *
	 * @param name
	 * @return List
	 */
	@GetMapping("/search/name")
	public ResponseEntity<?> searchPlanetsByName(@RequestParam(name = "name") final String name) {
		final List<PlanetDTO> listPlanets = PlanetDTO.convertToList(planetService.searchPlanetByNameLike(name));
		return ResponseEntity.ok(listPlanets);
	}

	/**
	 * Service that lists all the planets in the Swapi Star Wars api.
	 * 
	 * @return List
	 */
	@GetMapping("/swapi/starwars")
	public ResponseEntity<?> listPlanetsSwapiStarwars() {
		return ResponseEntity.ok(planetService.listPlanetsAPIStarWars());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable final Long id) {
		return ResponseEntity.ok(PlanetDTO.of(planetService.findById(id).get()));
	}

	@DeleteMapping("/{id}")
	public void deletePlanet(@PathVariable final Long id) {
		planetService.delete(id);
	}

}
