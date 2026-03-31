package cibertec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegistroEnvioTest {

    @Mock
    private TarificadorService tarificadorService;

    @Mock
    private ZonaService zonaService;

    @InjectMocks
    private RegistroEnvio registroEnvio;

    @Test
    @DisplayName("should apply surcharge when weight exceeds the limit.")
    void shouldApplySurchargeWhenWeightExceedsLimit() {
        String codigoEnvio = "E001";
        double peso = 15.0;
        double dimensiones = 50.0;
        String direccion = "Av. Lima 123";
        String tipoEnvio = "Express";
        double distancia = 100.0;

        when(zonaService.esZonaRestringida(direccion)).thenReturn(false);
        when(tarificadorService.calcularTarifa(peso, distancia, tipoEnvio)).thenReturn(100.0);

        String result = registroEnvio.registrar(codigoEnvio, peso, dimensiones, direccion, tipoEnvio, distancia);

        assertEquals("Tarifa con recargo: 120.0", result);
    }

    @Test
    @DisplayName("should return an error if the zone is restricted.")
    void shouldReturnErrorWhenZoneIsRestricted() {
        String direccion = "Av. Restringida 456";
        when(zonaService.esZonaRestringida(direccion)).thenReturn(true);

        String result = registroEnvio.registrar("E002", 5.0, 30.0, direccion, "Estándar", 50.0);

        assertEquals("No se permiten envíos a esta zona", result);
    }
}
