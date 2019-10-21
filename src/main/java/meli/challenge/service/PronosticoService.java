package meli.challenge.service;

import meli.challenge.data.PronosticoRepository;
import meli.challenge.model.Pronostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PronosticoService {

    private PronosticoRepository pronosticoRepository;

    @Autowired
    public PronosticoService(PronosticoRepository pronosticoRepository) {
        this.pronosticoRepository = pronosticoRepository;
    }

    public Pronostico obtenerPronostico(Integer dia) {

        return pronosticoRepository.leer(dia);

    }

    public void agregarPronostico(Pronostico pronostico) {

        pronosticoRepository.guardar(pronostico);

    }
}
