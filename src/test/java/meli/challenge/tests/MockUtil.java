package meli.challenge.tests;

import meli.challenge.Constantes;
import meli.challenge.model.Clima;
import meli.challenge.model.Pronostico;

public class MockUtil {

    public static Pronostico createMockPronosticoSequia(Integer dia){
        Pronostico mock = new Pronostico(dia);
        mock.setClima(Clima.SEQUIA);
        return mock;
    }

    public static Pronostico createMockPronosticoOptimo(Integer dia){
        Pronostico mock = new Pronostico(dia);
        mock.setClima(Clima.OPTIMO);
        return mock;
    }

    public static Pronostico createMockPronosticoLluvia(Integer dia){
        Pronostico mock = new Pronostico(dia);
        mock.setClima(Clima.LLUVIA);
        return mock;
    }

    public static Pronostico createMockPronosticoPicoDeLluvia(Integer dia){
        Pronostico mock = new Pronostico(200);
        mock.setClima(Clima.LLUVIA);
        mock.setPicoDeLluvia(Constantes.PICO_DE_LLUVIA);
        return mock;
    }

    public static Pronostico createMockPronosticoNormal(Integer dia){
        Pronostico mock = new Pronostico(dia);
        mock.setClima(Clima.NORMAL);
        return mock;
    }
}
