package com.banquito.core.clientes.controlador.mapper;

import com.banquito.core.clientes.controlador.dto.RepresentanteEmpresaDTO;
import com.banquito.core.clientes.enums.EstadoRegistro;
import com.banquito.core.clientes.enums.RolRepresentante;
import com.banquito.core.clientes.modelo.RepresentantesEmpresas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RepresentanteEmpresaMapper {
    RepresentanteEmpresaMapper INSTANCE = Mappers.getMapper(RepresentanteEmpresaMapper.class);

    @Mapping(target = "idEmpresa", source = "empresa.id")
    @Mapping(target = "idCliente", source = "cliente.id")
    @Mapping(target = "rol", source = "rol", qualifiedByName = "stringToRol")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "stringToEstado")
    RepresentanteEmpresaDTO toDto(RepresentantesEmpresas entity);

    @Mapping(target = "empresa", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "rol", source = "rol", qualifiedByName = "rolToString")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "estadoToString")
    RepresentantesEmpresas toEntity(RepresentanteEmpresaDTO dto);

    @Named("stringToRol")
    default RolRepresentante stringToRol(String rol) {
        return RolRepresentante.valueOf(rol);
    }

    @Named("rolToString")
    default String rolToString(RolRepresentante rol) {
        return rol.name();
    }

    @Named("stringToEstado")
    default EstadoRegistro stringToEstado(String estado) {
        return EstadoRegistro.valueOf(estado);
    }

    @Named("estadoToString")
    default String estadoToString(EstadoRegistro estado) {
        return estado.name();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaAsignacion", expression = "java(java.time.Instant.now())")
    @Mapping(target = "estado", expression = "java(com.banquito.core.clientes.enums.EstadoRegistro.ACTIVO.name())")
    @Mapping(target = "version", expression = "java(java.math.BigDecimal.ONE)")
    @Mapping(target = "empresa", expression = "java(new Empresas(dto.getIdEmpresa()))")
    @Mapping(target = "cliente", expression = "java(new Clientes(dto.getIdCliente()))")
    RepresentantesEmpresas toNewEntity(RepresentanteEmpresaDTO dto);
}
