package meli.challenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pronostico {

    private int dia;
    private Clima clima;
    private String picoDeLluvia;

    public Pronostico(int dia) {
        this.dia = dia;
        this.clima = Clima.NORMAL;
    }

}
