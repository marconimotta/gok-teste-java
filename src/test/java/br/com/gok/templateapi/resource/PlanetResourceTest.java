package br.com.gok.templateapi.resource;

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
import br.com.gok.templateapi.repository.PlanetRepository;
import br.com.gok.templateapi.service.PlanetService;
import br.com.gok.templateapi.service.PlanetServiceTest;

@SpringBootTest
@AutoConfigureMockMvc
public class PlanetResourceTest {

	public static final String NAME_PLANET = "Terra";

	public static final String CLIMATE = "cold";

	public static final String TERRAIN = "desert";

	public static final Long MAX_POPULATION = 6000000L;

	public static final Long MIN_POPULATION = 1000000L;

	@Mock
	private PlanetRepository repository;

	@MockBean
	private PlanetService service;

	@Autowired
	private MockMvc mockMvc;

	private Planet planet;

	@Mock
	private PlanetClient planetClient;

	@BeforeEach
	void setUp() {
		planet = Planet.builder().id(3L).name(PlanetServiceTest.NAME_PLANET).climate(PlanetResourceTest.CLIMATE).terrain(PlanetResourceTest.TERRAIN)
				.population(PlanetResourceTest.MAX_POPULATION).films(0L).build();
	}

	@Test
	public void whenFindByIdThenReturnOk() throws Exception {
		Mockito.when(service.findById(planet.getId())).thenReturn(Optional.of(planet));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/template/v1/planets/{id}", 3L).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(PlanetServiceTest.NAME_PLANET)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.climate", Matchers.is(PlanetResourceTest.CLIMATE)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.terrain", Matchers.is(PlanetResourceTest.TERRAIN)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.population", Matchers.is(PlanetResourceTest.MAX_POPULATION.toString()))).andReturn()
				.getResponse();

		Mockito.verify(service, VerificationModeFactory.atLeastOnce()).findById(ArgumentMatchers.anyLong());
	}

}
