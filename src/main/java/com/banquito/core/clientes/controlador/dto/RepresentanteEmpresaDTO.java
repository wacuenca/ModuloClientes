package com.banquito.core.clientes.controlador.dto;

import com.banquito.core.clientes.enums.EstadoRegistro;
import com.banquito.core.clientes.enums.RolRepresentante;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class RepresentanteEmpresaDTO {
    private Integer id;

    private Integer idEmpresa;
    private Integer idCliente;

    private RolRepresentante rol;
    private Instant fechaAsignacion;
    private Instant fechaCreacion;
    private Instant fechaUltimaModificacion;
    private EstadoRegistro estado;
    private BigDecimal version;
}