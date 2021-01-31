package br.com.gok.templateapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.gok.templateapi.clientrequest.PlanetClient;
import br.com.gok.templateapi.domain.Planet;
import br.com.gok.templateapi.dto.PlanetDTO;
import br.com.gok.templateapi.dto.PlanetStarWarsRequestDTO;
import br.com.gok.templateapi.repository.PlanetRepository;

/**
 * Class of service and business related to the features that involve Planet.
 *
 * @author Marconi Motta
 * @since 29 de jan de 2021
 */

@Service
public class PlanetService {

	@Autowired
	private PlanetRepository repository;

	@Autowired
	private PlanetClient client;

	public Planet insert(@NotNull final PlanetDTO planetDTO) {
		return repository.save(Planet.builderNew(planetDTO));
	}

	public Planet update(@NotNull final PlanetDTO planetDTO, @NotNull final Planet planet) {
		planet.of(planetDTO);
		return repository.save(planet);
	}

	public void delete(@NotNull final Long id) {
		repository.deleteById(id);
	}

	@Transactional
	public void importPlanetStarWars() {
		listPlanetsAPIStarWars().forEach(planetDTO -> {
			final Optional<Planet> planet = repository.findByName(planetDTO.getName());
			if(planet.isPresent()) {
				update(planetDTO, planet.get());
			} else {
				insert(planetDTO);
			}
		});
	}

	public List<PlanetDTO> listPlanetsAPIStarWars() {
		Long page = 1L;
		PlanetStarWarsRequestDTO planetRequest;
		final List<PlanetDTO> listPlanets = new ArrayList<>();
		do {
			planetRequest = client.listPlanetsStarWars(page);
			listPlanets.addAll(planetRequest.getResults());
			page++;
		} while(Objects.nonNull(planetRequest.getNext()));
		return listPlanets;
	}

	public List<Planet> listPlanetsByPopulation(final Long populationMin, final Long populationMax) {
		return repository.findByPopulationBetween(populationMin, populationMax);
	}

	public List<Planet> listPlanets() {
		return repository.findAll();
	}

	public List<Planet> searchPlanetByNameLike(final String name) {
		return repository.findByNameLike(name);
	}

	public Optional<Planet> findById(@NotNull final Long id) {
		return repository.findById(id);
	}

}
