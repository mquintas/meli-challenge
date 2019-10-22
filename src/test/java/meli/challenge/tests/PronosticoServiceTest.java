package meli.challenge.tests;

import meli.challenge.Constantes;
import meli.challenge.data.InMemoryPronosticoRepository;
import meli.challenge.data.PronosticoRepository;
import meli.challenge.model.Clima;
import meli.challenge.service.PronosticoService;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PronosticoServiceTest {

    @Mock
    private PronosticoRepository pronosticoRepository;

    @InjectMocks
    private PronosticoService pronosticoService;

    @Test
    public void testDiaDeLLuvia() {
        // Given
        when(pronosticoRepository.leer(90)).thenReturn((MockUtil.createMockPronosticoLluvia(90)));
        // When
        pronosticoService.obtenerPronostico(90).getClima();
        // Then
        verify(pronosticoRepository, times(1)).leer(any(Integer.class));
    }

    @Test
    public void testDiaDeSequia() {
        when(pronosticoRepository.leer(2650)).thenReturn((MockUtil.createMockPronosticoSequia(2650)));
        pronosticoService.obtenerPronostico(2650).getClima();
        verify(pronosticoRepository, times(1)).leer(any(Integer.class));
    }

    @Test
    public void testDiaDeOptimo() {
        when(pronosticoRepository.leer(1500)).thenReturn((MockUtil.createMockPronosticoOptimo(1500)));
        pronosticoService.obtenerPronostico(1500).getClima();
        verify(pronosticoRepository, times(1)).leer(any(Integer.class));
    }

    @Test
    public void testDiaClimaNormal() {
        when(pronosticoRepository.leer(3650)).thenReturn((MockUtil.createMockPronosticoNormal(3650)));
        pronosticoService.obtenerPronostico(3650).getClima();
        verify(pronosticoRepository, times(1)).leer(any(Integer.class));
    }

    @Test
    public void testDiaPicoDeLLuvia() {
        when(pronosticoRepository.leer(190)).thenReturn((MockUtil.createMockPronosticoPicoDeLluvia(190)));
        assertEquals(Clima.LLUVIA, pronosticoService.obtenerPronostico(190).getClima());
        Assert.assertEquals(Constantes.PICO_DE_LLUVIA, pronosticoService.obtenerPronostico(190).getPicoDeLluvia());
        verify(pronosticoRepository, times(2)).leer(any(Integer.class));
    }

}
