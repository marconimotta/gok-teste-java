
package br.com.gok.templateapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import br.com.gok.templateapi.dto.PlanetDTO;
import br.com.gok.templateapi.util.GeneralUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents the information of a Planet.
 *
 * @author Marconi Motta
 * @since 29 de jan de 2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "planet", uniqueConstraints= {@UniqueConstraint(columnNames= {"name"})})
public class Planet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String climate;
    
    @Column(nullable = false)
    private String terrain;
    
    @Column
    private Long population;

    @Column
    private Long films;
    
    public static Planet builderNew(PlanetDTO planetDTO) {
    		return new Planet(null, planetDTO.getName(), planetDTO.getClimate(), planetDTO.getTerrain(), GeneralUtil.getValidLong(planetDTO.getPopulation(), true), Long.valueOf(planetDTO.getFilms().size()));
    }
    
    public void of(PlanetDTO planetDTO) {
    	this.name = planetDTO.getName();
    	this.climate = planetDTO.getClimate();
    	this.terrain = planetDTO.getTerrain();
    	this.population = GeneralUtil.getValidLong(planetDTO.getPopulation(), true);
    	this.films = Long.valueOf(planetDTO.getFilms().size());
    }
}
