package com.banquito.core.clientes.controlador.mapper;

import com.banquito.core.clientes.controlador.dto.PersonaDTO;
import com.banquito.core.clientes.modelo.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    @Mapping(target = "accionistasEmpresas", ignore = true)
    @Mapping(target = "clientes", ignore = true)
    Persona toPersona(PersonaDTO dto);

    PersonaDTO toDto(Persona persona);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaRegistro", expression = "java(java.time.Instant.now())")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.Instant.now())")
    @Mapping(target = "estado", expression = "java(com.banquito.core.clientes.enums.EstadoCliente.ACTIVO)")
    @Mapping(target = "version", expression = "java(java.math.BigDecimal.ONE)")
    @Mapping(target = "accionistasEmpresas", ignore = true)
    @Mapping(target = "clientes", ignore = true)
    Persona toNewPersona(PersonaDTO dto);
}