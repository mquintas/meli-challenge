package meli.challenge.data;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import meli.challenge.model.Pronostico;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class InMemoryPronosticoRepository implements PronosticoRepository {

    private Cache<Integer, Pronostico> pronosticoCache;

    public void guardar(Pronostico pronostico) {
        pronosticoCache.put(pronostico.getDia(), pronostico);
    }

    public Pronostico leer(Integer dia) {
        //podria usar un get() si uso una cache con autoloader. y me evito el JOB de cargar la cache al inicializar.
        return pronosticoCache.getIfPresent(dia);
    }

    @PostConstruct
    private void init() {
        pronosticoCache =  CacheBuilder.newBuilder().build();
    }

}
