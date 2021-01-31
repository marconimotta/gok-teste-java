package br.com.gok.templateapi.clientrequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.gok.templateapi.dto.PlanetStarWarsRequestDTO;

/**
 * Request interface for accessing Swapi StarWars WS
 *
 * @author Marconi Motta
 * @since 30 de jan de 2021
 */
@FeignClient(url = "https://swapi.dev/api", name = "swapi-star-wars")
public interface PlanetClient {

	@GetMapping(value = "/planets/")
	PlanetStarWarsRequestDTO listPlanetsStarWars(@RequestParam Long page);

}
