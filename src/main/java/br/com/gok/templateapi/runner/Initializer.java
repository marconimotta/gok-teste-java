/*
 * Projeto: PROMPT
 *
 * Tipo: Initializer
 *
 * Marca Registrada (c) 1996-2005 CESAR
 * Rua Bione, nº 220, Cais do Apolo, Bairro do Recife Antigo, Recife-PE
 * CEP: 50030390, BRASIL, Fone: +55 81 3425.4700
 * Fax: +55 81 3425.4701
 * Todos os direitos reservados.
 */

package br.com.gok.templateapi.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import br.com.gok.templateapi.service.PlanetService;

/**
 * Class that performs essential methods for the API to work.
 *
 * @author Marconi Motta
 * @since 30 de jan de 2021
 */

@Component
public class Initializer implements CommandLineRunner {

	@Autowired
	PlanetService planetService;

	/**
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(final String... args) throws Exception {
		planetService.importPlanetStarWars();
	}

}
