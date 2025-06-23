package com.banquito.core.clientes.controlador.dto;
import com.banquito.core.clientes.enums.EstadoClienteSucursalEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteSucursalDTO {

    private String codigoSucursal;
    private EstadoClienteSucursalEnum estado;

    public ClienteSucursalDTO() {}

    public ClienteSucursalDTO(String codigoSucursal, EstadoClienteSucursalEnum estado) {
        this.codigoSucursal = codigoSucursal;
        this.estado = estado;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public EstadoClienteSucursalEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoClienteSucursalEnum estado) {
        this.estado = estado;
    }
}
