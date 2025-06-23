package com.banquito.core.clientes.controlador.dto;
import com.banquito.core.clientes.enums.EstadoGeneralEnum;
import com.banquito.core.clientes.enums.TipoTelefonoEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelefonoClienteDTO {

    private TipoTelefonoEnum tipo;
    private String numero;
    private EstadoGeneralEnum estado;

    public TelefonoClienteDTO() {}

    public TelefonoClienteDTO(TipoTelefonoEnum tipo, String numero, EstadoGeneralEnum estado) {
        this.tipo = tipo;
        this.numero = numero;
        this.estado = estado;
    }

    public TipoTelefonoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefonoEnum tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public EstadoGeneralEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneralEnum estado) {
        this.estado = estado;
    }
}
