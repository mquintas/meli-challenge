package meli.challenge;

import meli.challenge.model.SistemaSolar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Startup.class);

    @Autowired
    SistemaSolar sistemaSolar;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("##################################");
        LOG.info("# Inicializo el Sistema Solar... #");
        LOG.info("##################################");
        sistemaSolar.pronosticar();
    }
}