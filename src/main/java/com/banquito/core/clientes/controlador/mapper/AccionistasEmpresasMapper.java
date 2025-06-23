/*package com.banquito.core.clientes.controlador.mapper;

import com.banquito.core.clientes.controlador.dto.AccionistasEmpresasDTO;
import com.banquito.core.clientes.enums.EstadoRegistro;
import com.banquito.core.clientes.enums.TipoEntidadParticipe;
import com.banquito.core.clientes.modelo.AccionistasEmpresas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EmpresaMapper.class})
public interface AccionistasEmpresasMapper {
    AccionistasEmpresasMapper INSTANCE = Mappers.getMapper(AccionistasEmpresasMapper.class);

    @Mapping(target = "idEmpresa", source = "idEmpresa.id")
    @Mapping(target = "idParticipe", source = "idParticipe.id")
    @Mapping(target = "tipoEntidadParticipe", source = "tipoEntidadParticipe", qualifiedByName = "stringToTipoEntidad")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "stringToEstado")
    AccionistasEmpresasDTO toDto(AccionistasEmpresas entity);

    @Mapping(target = "idEmpresa", ignore = true)
    @Mapping(target = "idParticipe", ignore = true)
    @Mapping(target = "tipoEntidadParticipe", source = "tipoEntidadParticipe", qualifiedByName = "tipoEntidadToString")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "estadoToString")
    AccionistasEmpresas toEntity(AccionistasEmpresasDTO dto);

    @Named("stringToTipoEntidad")
    default TipoEntidadParticipe stringToTipoEntidad(String tipo) {
        return TipoEntidadParticipe.valueOf(tipo);
    }

    @Named("tipoEntidadToString")
    default String tipoEntidadToString(TipoEntidadParticipe tipo) {
        return tipo.name();
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
    @Mapping(target = "version", expression = "java(BigDecimal.ONE)")
    @Mapping(target = "idEmpresa", expression = "java(new Empresas(dto.getIdEmpresa()))")
    @Mapping(target = "idParticipe", expression = "java(new Empresas(dto.getIdParticipe()))")
    @Mapping(target = "tipoEntidadParticipe", source = "tipoEntidadParticipe", qualifiedByName = "tipoEntidadToString")
    @Mapping(target = "estado", expression = "java(com.banquito.core.clientes.enums.EstadoRegistro.ACTIVO.name())")
    AccionistasEmpresas toNewEntity(AccionistasEmpresasDTO dto);
}*/
