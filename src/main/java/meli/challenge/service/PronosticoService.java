package meli.challenge.service;

import meli.challenge.data.Storage;
import meli.challenge.model.Pronostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PronosticoService {

    @Autowired
    private Storage storage;

    public Pronostico obtenerPronostico(Integer dia) {

        return storage.leer(dia);

    }

    public void agregarPronostico(Pronostico pronostico) {

        storage.guardar(pronostico);

    }
}
