
package br.com.gok.templateapi.dto;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import br.com.gok.templateapi.domain.Planet;
import br.com.gok.templateapi.util.GeneralUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that performs transitions and treatments between Model and Json of the Planet class.
 *
 * @author Marconi Motta
 * @since 29 de jan de 2021
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PlanetDTO {

	private Long id;

	@NotNull
	private String name;
	
	@NotNull
	private String climate;

	@NotNull
	private String terrain;

	@NotNull
	private String population;

	@NotNull
	private List<String> films;
	
	private Long quantityFilms;
	
	public static PlanetDTO of(Planet planet) {
		return new PlanetDTO(planet.getId(), planet.getName(), planet.getClimate(), planet.getTerrain(), GeneralUtil.getValidString(planet.getPopulation(), true), null, planet.getFilms());
	}
	
	public static List<PlanetDTO> convertToList(final List<Planet> listPlanets) {
		return listPlanets.stream().map(PlanetDTO::of).collect(Collectors.toList());
	}

}
