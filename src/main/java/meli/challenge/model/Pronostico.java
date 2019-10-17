package meli.challenge.model;

import lombok.Data;

@Data
public class Pronostico {

    private int dia;
    private Clima clima;

    public Pronostico(int dia) {
        this.dia = dia;
        this.clima = Clima.NORMAL;
    }

}
