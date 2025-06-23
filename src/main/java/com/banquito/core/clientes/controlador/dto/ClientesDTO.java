package com.banquito.core.clientes.controlador.dto;

import com.banquito.core.clientes.enums.EstadoCliente;
import com.banquito.core.clientes.enums.TipoEntidad;
import com.banquito.core.clientes.enums.TipoIdentificacion;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class ClientesDTO {
    private Integer id;
    private TipoEntidad tipoEntidad;
    private Integer idEntidad;
    private String nombre;
    private String nacionalidad; 
    private TipoIdentificacion tipoIdentificacion;
    private String numeroIdentificacion;
    private String tipoCliente; 
    private String segmento; 
    private String canalAfiliacion;
    private Instant fechaCreacion;
    private Instant fechaActualizacion;
    private String comentarios;
    private EstadoCliente estado;
    private BigDecimal version;
}