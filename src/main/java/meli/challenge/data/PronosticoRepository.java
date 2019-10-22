package meli.challenge.data;

import meli.challenge.model.Pronostico;

public interface PronosticoRepository {

    void guardar(Pronostico pronostico);

    Pronostico leer(Integer dia);

}
