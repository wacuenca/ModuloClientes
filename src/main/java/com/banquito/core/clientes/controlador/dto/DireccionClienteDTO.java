package com.banquito.core.clientes.controlador.dto;
import com.banquito.core.clientes.enums.EstadoGeneralEnum;
import com.banquito.core.clientes.enums.TipoDireccionEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DireccionClienteDTO {

    private TipoDireccionEnum tipo;
    private String linea1;
    private String linea2;
    private String codigoPostal;
    private String codigoGeografico;
    private EstadoGeneralEnum estado;

    public DireccionClienteDTO() {}

    public DireccionClienteDTO(TipoDireccionEnum tipo, String linea1, String linea2, String codigoPostal, String codigoGeografico, EstadoGeneralEnum estado) {
        this.tipo = tipo;
        this.linea1 = linea1;
        this.linea2 = linea2;
        this.codigoPostal = codigoPostal;
        this.codigoGeografico = codigoGeografico;
        this.estado = estado;
    }

    public TipoDireccionEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoDireccionEnum tipo) {
        this.tipo = tipo;
    }

    public String getLinea1() {
        return linea1;
    }

    public void setLinea1(String linea1) {
        this.linea1 = linea1;
    }

    public String getLinea2() {
        return linea2;
    }

    public void setLinea2(String linea2) {
        this.linea2 = linea2;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCodigoGeografico() {
        return codigoGeografico;
    }

    public void setCodigoGeografico(String codigoGeografico) {
        this.codigoGeografico = codigoGeografico;
    }

    public EstadoGeneralEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneralEnum estado) {
        this.estado = estado;
    }
}