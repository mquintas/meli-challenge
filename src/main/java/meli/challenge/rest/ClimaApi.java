package meli.challenge.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import meli.challenge.model.Pronostico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Api(value = "clima")
@RequestMapping(path = "/api")
public interface ClimaApi {

    @ApiOperation(value = "", nickname = "Obtener Clima", notes = "Devuelve el clima de un dia en particular.",
                  response = Pronostico.class, responseContainer = "List" ,tags={ "Clima", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clima", response = Pronostico.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ResponseStatus.class)
    })
    @RequestMapping(value = "/clima", produces = {"application/json"}, method = RequestMethod.GET)
    Pronostico obtenerClima(@RequestParam(value="dia")
                            @Min(value= 0, message = "Solo se pronostica hacia el futuro. Ingrese un dia mayor a Cero.")
                            @Max(value= 3650, message = "Solo se pronostica 10 años (3650) dias. Ingrese un dia menor.")
                            Integer dia);

}
