package com.banquito.core.clientes.controlador.mapper;

import com.banquito.core.clientes.controlador.dto.EmpresasDTO;
import com.banquito.core.clientes.modelo.Empresas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") 
public interface EmpresaMapper {
    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);

    @Mapping(target = "accionistasDeEstaEmpresa", ignore = true)
    @Mapping(target = "clientes", ignore = true)
    @Mapping(target = "representantesEmpresas", ignore = true)
    Empresas toEmpresa(EmpresasDTO dto);

    @Mapping(target = "representantesEmpresas", ignore = true)
    @Mapping(target = "clientes", ignore = true)
    EmpresasDTO toDto(Empresas empresa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaRegistro", expression = "java(java.time.Instant.now())")
    @Mapping(target = "fechaActualizacion", expression = "java(java.time.Instant.now())")
    @Mapping(target = "estado", expression = "java(com.banquito.core.clientes.enums.EstadoCliente.ACTIVO)")
    @Mapping(target = "version", expression = "java(java.math.BigDecimal.ONE)")
    @Mapping(target = "accionistasDeEstaEmpresa", ignore = true)
    @Mapping(target = "clientes", ignore = true)
    @Mapping(target = "representantesEmpresas", ignore = true)
    Empresas toNewEmpresa(EmpresasDTO dto);
}