package com.banquito.core.clientes.controlador.dto;

import com.banquito.core.clientes.enums.EstadoRegistro;
import com.banquito.core.clientes.enums.TipoEntidadParticipe;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class AccionistasEmpresasDTO {
    private Integer id;

    private Integer idEmpresa;
    private Integer idParticipe;

    private BigDecimal participacion;
    private TipoEntidadParticipe tipoEntidadParticipe;
    private EstadoRegistro estado;
    private Instant fechaCreacion;
    private Instant fechaUltimaModificacion;
    private BigDecimal version;

}
