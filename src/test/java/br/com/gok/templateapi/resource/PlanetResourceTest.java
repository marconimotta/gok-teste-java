package br.com.gok.templateapi.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import br.com.gok.templateapi.clientrequest.PlanetClient;
import br.com.gok.templateapi.domain.Planet;
import br.com.gok.templateapi.dto.PlanetDTO;
import br.com.gok.templateapi.repository.PlanetRepository;
import br.com.gok.templateapi.service.PlanetService;
import br.com.gok.templateapi.service.PlanetServiceTest;

@SpringBootTest
@AutoConfigureMockMvc
public class PlanetResourceTest {

	public static final Long ID = 3L;

	public static final String NAME_PLANET = "Terra";

	public static final String CLIMATE = "cold";

	public static final String TERRAIN = "desert";

	public static final Long MIN_POPULATION = 1000000L;

	public static final Long MAX_POPULATION = 6000000L;

	@Mock
	private PlanetRepository repository;

	@MockBean
	private PlanetService service;

	@Autowired
	private MockMvc mockMvc;

	private Planet planet;

	private PlanetDTO planetDTO;

	@Mock
	private PlanetClient planetClient;

	@BeforeEach
	void setUp() {
		planet = Planet.builder().id(PlanetResourceTest.ID).name(PlanetServiceTest.NAME_PLANET).climate(PlanetResourceTest.CLIMATE)
				.terrain(PlanetResourceTest.TERRAIN).population(PlanetResourceTest.MAX_POPULATION).films(0L).build();
		planetDTO = PlanetDTO.of(planet);
	}

	@Test
	public void whenFindByIdThenReturnOk() throws Exception {
		Mockito.when(service.findById(PlanetResourceTest.ID)).thenReturn(Optional.of(planet));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/template/v1/planets/{id}", 3L).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(PlanetServiceTest.NAME_PLANET)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.climate", Matchers.is(PlanetResourceTest.CLIMATE)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.terrain", Matchers.is(PlanetResourceTest.TERRAIN)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.population", Matchers.is(PlanetResourceTest.MAX_POPULATION.toString()))).andReturn()
				.getResponse();

		Mockito.verify(service, VerificationModeFactory.atLeastOnce()).findById(ArgumentMatchers.anyLong());
	}

	@Test
	public void whenFindAllPlanetsThenReturnASimpleItem() throws Exception {

		Mockito.when(service.listPlanets()).thenReturn(Collections.singletonList(planet));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/template/v1/planets/").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(PlanetServiceTest.NAME_PLANET)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].climate", Matchers.is(PlanetResourceTest.CLIMATE)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].terrain", Matchers.is(PlanetResourceTest.TERRAIN)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].population", Matchers.is(PlanetResourceTest.MAX_POPULATION.toString()))).andReturn()
				.getResponse();

		Mockito.verify(service, VerificationModeFactory.atLeastOnce()).listPlanets();

	}

	@Test
	public void whenSearchPlanetByNameLikeThenReturnOk() throws Exception {
		Mockito.when(service.searchPlanetByNameLike(PlanetServiceTest.NAME_PLANET)).thenReturn(Arrays.asList(planet));

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/template/v1/planets/search/name?name={name}", planet.getName()).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(PlanetServiceTest.NAME_PLANET)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].climate", Matchers.is(PlanetResourceTest.CLIMATE)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].terrain", Matchers.is(PlanetResourceTest.TERRAIN)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].population", Matchers.is(PlanetResourceTest.MAX_POPULATION.toString()))).andReturn()
				.getResponse();

		Mockito.verify(service, VerificationModeFactory.atLeastOnce()).searchPlanetByNameLike(ArgumentMatchers.anyString());
	}

	@Test
	public void whenListPlanetByPopulationThenReturnOk() throws Exception {
		Mockito.when(service.listPlanetsByPopulation(PlanetResourceTest.MIN_POPULATION, PlanetResourceTest.MAX_POPULATION))
				.thenReturn(Arrays.asList(planet));

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/template/v1/planets/filter/population?populationMin={populationMin}&populationMax={populationMax}",
						PlanetResourceTest.MIN_POPULATION, PlanetResourceTest.MAX_POPULATION).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(PlanetServiceTest.NAME_PLANET)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].climate", Matchers.is(PlanetResourceTest.CLIMATE)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].terrain", Matchers.is(PlanetResourceTest.TERRAIN)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].population", Matchers.is(PlanetResourceTest.MAX_POPULATION.toString()))).andReturn()
				.getResponse();

		Mockito.verify(service, VerificationModeFactory.atLeastOnce()).listPlanetsByPopulation(ArgumentMatchers.anyLong(),
				ArgumentMatchers.anyLong());
	}

	@Test
	public void whenListPlanetSwapiStarWarsThenReturnOk() throws Exception {
		Mockito.when(service.listPlanetsAPIStarWars()).thenReturn(Arrays.asList(planetDTO));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/template/v1/planets//swapi/starwars").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is(PlanetServiceTest.NAME_PLANET)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].climate", Matchers.is(PlanetResourceTest.CLIMATE)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].terrain", Matchers.is(PlanetResourceTest.TERRAIN)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].population", Matchers.is(PlanetResourceTest.MAX_POPULATION.toString()))).andReturn()
				.getResponse();

		Mockito.verify(service, VerificationModeFactory.atLeastOnce()).listPlanetsAPIStarWars();
	}

	@Test
	public void whenDeletePlanetThenReturnOk() throws Exception {
		Mockito.when(service.delete(PlanetResourceTest.ID)).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/template/v1/planets/{id}", PlanetResourceTest.ID).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(service, VerificationModeFactory.atLeastOnce()).delete(PlanetResourceTest.ID);
	}

}
