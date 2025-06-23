/*package com.banquito.core.clientes.controlador.mapper;

import com.banquito.core.clientes.controlador.dto.ClientesDTO;
import com.banquito.core.clientes.modelo.Clientes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientesMapper {
    ClientesMapper INSTANCE = Mappers.getMapper(ClientesMapper.class);

    @Mapping(target = "idEntidad", ignore = true)
    @Mapping(target = "clientesSucursales", ignore = true)
    @Mapping(target = "contactosTransaccionalesClientes", ignore = true)
    @Mapping(target = "direccionesClientes", ignore = true)
    @Mapping(target = "representantesEmpresas", ignore = true)
    @Mapping(target = "telefonosClientes", ignore = true)
    Clientes toCliente(ClientesDTO dto);

    ClientesDTO toDto(Clientes cliente);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", expression = "java(java.time.Instant.now())")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.Instant.now())")
    @Mapping(target = "estado", expression = "java(com.banquito.core.clientes.enums.EstadoCliente.ACTIVO)")
    @Mapping(target = "version", expression = "java(java.math.BigDecimal.ONE)")
    @Mapping(target = "idEntidad", ignore = true)
    @Mapping(target = "clientesSucursales", ignore = true)
    @Mapping(target = "contactosTransaccionalesClientes", ignore = true)
    @Mapping(target = "direccionesClientes", ignore = true)
    @Mapping(target = "representantesEmpresas", ignore = true)
    @Mapping(target = "telefonosClientes", ignore = true)
    Clientes toNewCliente(ClientesDTO dto);
}*/