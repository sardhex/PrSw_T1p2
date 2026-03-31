package cibertec;

import java.time.LocalDate;

public class RegistroEnvio {
    private TarificadorService tarificadorService;
    private ZonaService zonaService;

    public RegistroEnvio(TarificadorService tarificadorService, ZonaService zonaService) {
        this.tarificadorService = tarificadorService;
        this.zonaService = zonaService;
    }

    public String registrar(String codigoEnvio, double peso, double dimensiones, String direccion, String tipoEnvio,
            double distancia) {
        if (zonaService.esZonaRestringida(direccion)) {
            return "No se permiten envíos a esta zona";
        }
        double tarifaFinal = calcularTarifaConRecargo(peso, distancia, tipoEnvio);
        return "Tarifa con recargo: " + tarifaFinal;
    }

    private double calcularTarifaConRecargo(double peso, double distancia, String tipoEnvio) {
        double tarifa = tarificadorService.calcularTarifa(peso, distancia, tipoEnvio);
        if (peso > 10) {
            tarifa *= 1.2;
        }
        return tarifa;
    }
}
