package meli.challenge.tests;

import meli.challenge.model.Clima;
import meli.challenge.model.Pronostico;
import meli.challenge.rest.PronosticoController;
import meli.challenge.service.PronosticoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PronosticoControllerTest {

    @Mock
    private PronosticoService service;

    private PronosticoController controller;

    private Random random;

    @Before
    public void setup() {
        controller = new PronosticoController(service);
        random = new Random();
    }

    @Test
    public void testRandomRequest() {

        Integer dia = random.nextInt(3650);

        when(service.obtenerPronostico(any(Integer.class))).thenReturn(MockUtil.createMockPronosticoLluvia(dia));

        Pronostico pronostico = controller.obtenerClima(dia);

        verify(service).obtenerPronostico(any(Integer.class));
        assertThat(controller).isNotNull();
        assertThat(pronostico).isNotNull();
        assertThat(pronostico.getDia()).isNotNull();
        assertThat(pronostico.getDia()).isEqualTo(dia);
        assertThat(pronostico.getClima()).isEqualTo(Clima.LLUVIA);
    }

}
