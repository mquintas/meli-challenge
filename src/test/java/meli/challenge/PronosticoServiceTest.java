package meli.challenge;

import meli.challenge.data.PronosticoRepository;
import meli.challenge.model.Clima;
import meli.challenge.service.PronosticoService;
import meli.challenge.tests.MockUtil;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PronosticoServiceTest {

    @Mock
    private PronosticoRepository pronosticoRepository;

    @InjectMocks
    private PronosticoService pronosticoService;

    @Before
    public void setup() throws IOException {

        for(int i = 0 ; i <= 3650 ; i++) {
            when(pronosticoRepository.leer(i)).thenReturn((MockUtil.createMockPronosticoNormal(i)));
        }

        when(pronosticoRepository.leer(90)).thenReturn((MockUtil.createMockPronosticoLluvia(90)));
        when(pronosticoRepository.leer(190)).thenReturn((MockUtil.createMockPronosticoPicoDeLluvia(190)));
        when(pronosticoRepository.leer(1500)).thenReturn((MockUtil.createMockPronosticoOptimo(1500)));
        when(pronosticoRepository.leer(2650)).thenReturn((MockUtil.createMockPronosticoSequia(2650)));
    }


    @Test
    public void testDiaDeLLuvia() {
        assertEquals(Clima.LLUVIA, pronosticoService.obtenerPronostico(90).getClima());
        verify(pronosticoRepository).leer(any(Integer.class));
    }

    @Test
    public void testDiaDeSequia() {
        assertEquals(Clima.SEQUIA, pronosticoService.obtenerPronostico(2650).getClima());
    }

    @Test
    public void testDiaDeOptimo() {
        assertEquals(Clima.OPTIMO, pronosticoService.obtenerPronostico(1500).getClima());
    }

    @Test
    public void testDiaClimaNormal() {
        assertEquals(Clima.NORMAL, pronosticoService.obtenerPronostico(3650).getClima());
    }

    @Test
    public void testDiaPicoDeLLuvia() {
        assertEquals(Clima.LLUVIA, pronosticoService.obtenerPronostico(190).getClima());
        assertEquals("SI", pronosticoService.obtenerPronostico(190).getPicoDeLluvia());
    }



}
