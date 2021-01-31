package br.com.gok.templateapi.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents the answer to the WS Call list of Planets from the Star Wars SWAPI API.
 *
 * @author Marconi Motta
 * @since 30 de jan de 2021
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PlanetStarWarsRequestDTO {
	
	private Long count;
	
	private String next;
	
	private String previous;
	
	private List<PlanetDTO> results;
	
}
