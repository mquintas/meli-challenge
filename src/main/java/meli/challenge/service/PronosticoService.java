package meli.challenge.service;

import meli.challenge.data.PronosticoRepository;
import meli.challenge.model.Pronostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PronosticoService {

    private PronosticoRepository repository;

    @Autowired
    public PronosticoService(PronosticoRepository pronosticoRepository) {
        this.repository = pronosticoRepository;
    }

    public Pronostico obtenerPronostico(Integer dia) {
        return repository.leer(dia);
    }

    public void agregarPronostico(Pronostico pronostico) {
        repository.guardar(pronostico);
    }
}
