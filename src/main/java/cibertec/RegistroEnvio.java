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
        return "";
    }
}
