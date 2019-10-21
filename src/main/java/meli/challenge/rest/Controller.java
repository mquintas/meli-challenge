package meli.challenge.rest;

import lombok.extern.log4j.Log4j2;
import meli.challenge.model.Pronostico;
import meli.challenge.service.PronosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Validated
public class Controller implements ClimaApi {

    @Autowired
    PronosticoService service;

    public Pronostico obtenerClima(Integer dia) {

        Pronostico p = new Pronostico(dia);
        log.debug("API request dia: " + dia);
        return service.obtenerPronostico(dia);
    }

}
