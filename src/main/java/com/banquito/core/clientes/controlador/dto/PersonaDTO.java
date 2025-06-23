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
public class PersonaDTO {
    private Integer id;
    private Integer accionistasEmpresas;
    private Integer clientes;
    private TipoIdentificacion tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombre;
    private String genero; 
    private LocalDate fechaNacimiento;
    private String estadoCivil;
    private String nivelEstudio;
    private String correoElectronico;
    private Instant fechaRegistro;
    private Instant fechaActualizacion;
    private EstadoCliente estado;
    private BigDecimal version;
}