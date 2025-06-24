package com.banquito.core.clientes.controlador.mapper;

import com.banquito.core.clientes.controlador.dto.ClientesDTO;
import com.banquito.core.clientes.enums.EstadoCliente;
import com.banquito.core.clientes.modelo.Clientes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientesMapper {

    default String estadoClienteToString(EstadoCliente estado) {
        return estado != null ? estado.name() : null;
    }
    
    default EstadoCliente stringToEstadoCliente(String estado) {
        return estado != null ? EstadoCliente.valueOf(estado) : null;
    }

    
    ClientesMapper INSTANCE = Mappers.getMapper(ClientesMapper.class);

    @Mapping(target = "idEntidad", source = "idEntidad", qualifiedByName = "idToPersona")
    @Mapping(target = "clientesSucursales", ignore = true)
    @Mapping(target = "contactosTransaccionalesClientes", ignore = true)
    @Mapping(target = "direccionesClientes", ignore = true)
    @Mapping(target = "representantesEmpresas", ignore = true)
    @Mapping(target = "telefonosClientes", ignore = true)
    Clientes toCliente(ClientesDTO dto);

    @org.mapstruct.Named("idToPersona")
    public static com.banquito.core.clientes.modelo.Persona idToPersona(Integer id) {
        if (id == null) {
            return null;
        }
        com.banquito.core.clientes.modelo.Persona persona = new com.banquito.core.clientes.modelo.Persona();
        persona.setId(id);
        return persona;
    }

    @Mapping(target = "idEntidad", source = "idEntidad", qualifiedByName = "personaToId")
    ClientesDTO toDto(Clientes cliente);

    @org.mapstruct.Named("personaToId")
    public static Integer personaToId(com.banquito.core.clientes.modelo.Persona persona) {
        return persona != null ? persona.getId() : null;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", expression = "java(java.time.Instant.now())")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.Instant.now())")
    @Mapping(target = "estado", expression = "java(com.banquito.core.clientes.enums.EstadoCliente.ACTIVO)")
    @Mapping(target = "version", expression = "java(java.math.BigDecimal.ONE)")
    @Mapping(target = "idEntidad", source = "idEntidad", qualifiedByName = "idToPersona")
    @Mapping(target = "clientesSucursales", ignore = true)
    @Mapping(target = "contactosTransaccionalesClientes", ignore = true)
    @Mapping(target = "direccionesClientes", ignore = true)
    @Mapping(target = "representantesEmpresas", ignore = true)
    @Mapping(target = "telefonosClientes", ignore = true)
    Clientes toNewCliente(ClientesDTO dto);

    
}