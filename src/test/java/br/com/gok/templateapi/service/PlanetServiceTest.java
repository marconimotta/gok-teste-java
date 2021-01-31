package br.com.gok.templateapi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.gok.templateapi.clientrequest.PlanetClient;
import br.com.gok.templateapi.domain.Planet;
import br.com.gok.templateapi.dto.PlanetDTO;
import br.com.gok.templateapi.repository.PlanetRepository;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

	public static final String NAME_PLANET = "Terra";

	public static final String CLIMATE = "cold";

	public static final String TERRAIN = "desert";

	public static final Long MAX_POPULATION = 6000000L;

	public static final Long MIN_POPULATION = 1000000L;

	@Mock
	private PlanetRepository repository;

	@Mock
	private PlanetClient planetClient;

	@InjectMocks
	private PlanetService service;

	private Planet planet;

	private PlanetDTO planetDTO;

	@BeforeEach
	void setUp() {
		planet = Planet.builder().id(1L).name(PlanetServiceTest.NAME_PLANET).climate(PlanetServiceTest.CLIMATE).terrain(PlanetServiceTest.TERRAIN)
				.population(60000000000L).films(0L).build();
		planetDTO = PlanetDTO.builder().name(PlanetServiceTest.NAME_PLANET).climate(PlanetServiceTest.CLIMATE).terrain(PlanetServiceTest.TERRAIN)
				.population("60000000000").films(Collections.emptyList()).build();
	}

	@Test
	void whenFindByIdOk() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(planet));

		final Planet actual = service.findById(1L).get();

		Assertions.assertEquals(planet.getName(), actual.getName());
		Assertions.assertEquals(planet.getClimate(), actual.getClimate());
		Assertions.assertEquals(planet.getTerrain(), actual.getTerrain());
		Assertions.assertEquals(planet.getPopulation(), actual.getPopulation());
		Mockito.verify(repository, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
	}

	@Test
	void whenFindByIdThenReturnEmpty() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

		Assertions.assertFalse(service.findById(1L).isPresent());
		Mockito.verify(repository, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
	}

	@Test
	void whenListAllOk() {
		Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(planet));

		final List<Planet> actual = service.listPlanets();

		Assertions.assertEquals(1, actual.size());
		Mockito.verify(repository, Mockito.atLeastOnce()).findAll();

	}

	@Test
	void whenFindByPopulationOk() {
		Mockito.when(repository.findByPopulationBetween(PlanetServiceTest.MIN_POPULATION, PlanetServiceTest.MAX_POPULATION))
				.thenReturn(Collections.singletonList(planet));

		final List<Planet> actual = service.listPlanetsByPopulation(PlanetServiceTest.MIN_POPULATION, PlanetServiceTest.MAX_POPULATION);

		Assertions.assertEquals(1, actual.size());
		Mockito.verify(repository, Mockito.atLeastOnce()).findByPopulationBetween(PlanetServiceTest.MIN_POPULATION, PlanetServiceTest.MAX_POPULATION);

	}

	@Test
	void whenFindByNameLikeOk() {
		Mockito.when(repository.findByNameLike(PlanetServiceTest.NAME_PLANET)).thenReturn(Collections.singletonList(planet));

		final List<Planet> actual = service.searchPlanetByNameLike(PlanetServiceTest.NAME_PLANET);

		Assertions.assertEquals(1, actual.size());
		Mockito.verify(repository, Mockito.atLeastOnce()).findByNameLike(PlanetServiceTest.NAME_PLANET);

	}

	@Test
	void whenFindByNameLikeNotFound() {
		Mockito.when(repository.findByNameLike(PlanetServiceTest.NAME_PLANET)).thenReturn(null);

		Assertions.assertNull(service.searchPlanetByNameLike(PlanetServiceTest.NAME_PLANET));
		Mockito.verify(repository, Mockito.atLeastOnce()).findByNameLike(PlanetServiceTest.NAME_PLANET);
	}

	@Test
	void whenSaveOk() {
		Mockito.when(repository.save(ArgumentMatchers.any(Planet.class))).thenReturn(planet);

		final Planet actual = service.insert(planetDTO);

		Assertions.assertEquals(planet, actual);
		Mockito.verify(repository, Mockito.atLeastOnce()).save(ArgumentMatchers.any(Planet.class));
	}

	@Test
	void whenAlterPlaceOk() {
		final String editedName = "Lorem Ipsum";
		final PlanetDTO planetDTOTeste = planetDTO;
		planetDTOTeste.setName(editedName);
		Mockito.when(repository.save(ArgumentMatchers.any(Planet.class))).thenReturn(planet);

		final Planet edited = service.update(planetDTOTeste, planet);

		Assertions.assertNotNull(edited);
		Assertions.assertEquals(editedName, edited.getName());
		Assertions.assertEquals(planet.getClimate(), edited.getClimate());
		Assertions.assertEquals(planet.getTerrain(), edited.getTerrain());
		Assertions.assertEquals(planet.getPopulation(), edited.getPopulation());
	}

}
