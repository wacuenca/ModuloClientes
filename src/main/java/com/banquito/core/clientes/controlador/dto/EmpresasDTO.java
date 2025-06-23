package com.banquito.core.clientes.controlador.dto;

import com.banquito.core.clientes.enums.EstadoCliente;
import com.banquito.core.clientes.enums.TipoIdentificacion;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
public class EmpresasDTO {
    private Integer id;
    private Integer accionistasEmpresas;
    private Integer clientes;
    private Integer representantesEmpresas;
    private TipoIdentificacion tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombreComercial;
    private String razonSocial;
    private String tipo; 
    private LocalDate fechaConstitucion;
    private String correoElectronico;
    private String sectorEconomico; 
    private Instant fechaRegistro;
    private Instant fechaActualizacion;
    private EstadoCliente estado;
    private BigDecimal version;
}