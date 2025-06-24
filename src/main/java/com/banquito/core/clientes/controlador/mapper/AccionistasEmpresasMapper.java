package com.banquito.core.clientes.controlador.mapper;

import com.banquito.core.clientes.controlador.dto.AccionistasEmpresasDTO;
import com.banquito.core.clientes.enums.EstadoRegistro;
import com.banquito.core.clientes.enums.TipoEntidadParticipe;
import com.banquito.core.clientes.modelo.AccionistasEmpresas;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring") 
public interface AccionistasEmpresasMapper {

    AccionistasEmpresasMapper INSTANCE = Mappers.getMapper(AccionistasEmpresasMapper.class);

    @Mapping(target = "idEmpresa", source = "empresa.id")
    @Mapping(target = "idParticipe", expression = "java(obtenerIdParticipe(entity))")
    @Mapping(target = "tipoEntidadParticipe", source = "tipoEntidadParticipe", qualifiedByName = "stringToTipoEntidad")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "stringToEstado")
    AccionistasEmpresasDTO toDto(AccionistasEmpresas entity);

    @Mapping(target = "empresa", expression = "java(new Empresas(dto.getIdEmpresa()))")
    @Mapping(target = "personaParticipe", expression = "java(dto.getTipoEntidadParticipe() == TipoEntidadParticipe.PERSONA ? new Persona(dto.getIdParticipe()) : null)")
    @Mapping(target = "empresaParticipe", expression = "java(dto.getTipoEntidadParticipe() == TipoEntidadParticipe.EMPRESA ? new Empresas(dto.getIdParticipe()) : null)")
    @Mapping(target = "tipoEntidadParticipe", source = "tipoEntidadParticipe", qualifiedByName = "tipoEntidadToString")
    @Mapping(target = "estado", expression = "java(com.banquito.core.clientes.enums.EstadoRegistro.ACTIVO.name())")
    @Mapping(target = "version", expression = "java(java.math.BigDecimal.ONE)")
    @Mapping(target = "id", ignore = true)
    AccionistasEmpresas toNewEntity(AccionistasEmpresasDTO dto);

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

    default Integer obtenerIdParticipe(AccionistasEmpresas entity) {
        if (entity.getTipoEntidadParticipe().equals("PERSONA") && entity.getPersonaParticipe() != null) {
            return entity.getPersonaParticipe().getId();
        } else if (entity.getTipoEntidadParticipe().equals("EMPRESA") && entity.getEmpresaParticipe() != null) {
            return entity.getEmpresaParticipe().getId();
        } else {
            return null;
        }
    }
}
