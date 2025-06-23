package com.banquito.core.clientes.controlador.dto;
import com.banquito.core.clientes.enums.EstadoGeneralEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactoTransaccionalClienteDTO {

    private String telefono;
    private String correo;
    private EstadoGeneralEnum estado;

    public ContactoTransaccionalClienteDTO() {}

    public ContactoTransaccionalClienteDTO(String telefono, String correo, EstadoGeneralEnum estado) {
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public EstadoGeneralEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneralEnum estado) {
        this.estado = estado;
    }
}