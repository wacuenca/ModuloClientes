package com.banquito.core.clientes.servicio;

import com.banquito.core.clientes.controlador.dto.*;
import com.banquito.core.clientes.controlador.mapper.*;
import com.banquito.core.clientes.enums.EstadoRegistro;
import com.banquito.core.clientes.excepcion.*;
import com.banquito.core.clientes.modelo.*;
import com.banquito.core.clientes.repositorio.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccionistaRepresentanteService {

    private final AccionistasEmpresasRepositorio accionistaRepo;
    private final RepresentantesEmpresasRepositorio representanteRepo;
    private final EmpresasRepositorio empresaRepo;
    private final ClientesRepositorio clienteRepo;
    private final AccionistasEmpresasMapper accionistaMapper;
    private final RepresentanteEmpresaMapper representanteMapper;

    public AccionistaRepresentanteService(AccionistasEmpresasRepositorio accionistaRepo,
                                        RepresentantesEmpresasRepositorio representanteRepo,
                                        EmpresasRepositorio empresaRepo,
                                        ClientesRepositorio clienteRepo,
                                        AccionistasEmpresasMapper accionistaMapper,
                                        RepresentanteEmpresaMapper representanteMapper) {
        this.accionistaRepo = accionistaRepo;
        this.representanteRepo = representanteRepo;
        this.empresaRepo = empresaRepo;
        this.clienteRepo = clienteRepo;
        this.accionistaMapper = accionistaMapper;
        this.representanteMapper = representanteMapper;
    }

    @Transactional
    public AccionistasEmpresasDTO agregarAccionista(AccionistasEmpresasDTO accionistaDTO) {
        try {
            if (!empresaRepo.existsById(accionistaDTO.getIdEmpresa())) {
                throw new NotFoundException("Empresa no encontrada", 3206);
            }
            
            // if (accionistaDTO.getTipoEntidadParticipe() == TipoEntidadParticipe.PERSONA) {
            //     if (!clienteRepo.existsByEntidadAndTipo(accionistaDTO.getIdParticipe(), "PERSONA")) {
            //         throw new CreacionException("Persona debe ser cliente", 1401);
            //     }
            // } else if (accionistaDTO.getTipoEntidadParticipe() == TipoEntidadParticipe.EMPRESA) {
            //     if (!clienteRepo.existsByEntidadAndTipo(accionistaDTO.getIdParticipe(), "EMPRESA")) {
            //         throw new CreacionException("Empresa debe ser cliente", 1402);
            //     }
            // }
            
            if (accionistaRepo.existsByEmpresaIdAndEmpresaParticipeId(accionistaDTO.getIdEmpresa(), accionistaDTO.getIdParticipe())) {
                throw new CreacionException("Accionista ya existe", 1404);
            }
            
            AccionistasEmpresas accionista = accionistaMapper.toNewEntity(accionistaDTO);
            accionista.setFechaCreacion(Instant.now());
            accionista.setFechaUltimaModificacion(Instant.now());
            return accionistaMapper.toDto(accionistaRepo.save(accionista));
            
        } catch (CreacionException | NotFoundException e) {
            log.error("Error al agregar accionista: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al agregar accionista", e);
            throw new CreacionException("Error al agregar accionista", 1499);
        }
    }

    @Transactional
    public AccionistasEmpresasDTO actualizarAccionista(Integer id, AccionistasEmpresasDTO accionistaDTO) {
        try {
            AccionistasEmpresas accionista = accionistaRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Accionista no encontrado", 3401));
            
            accionista.setParticipacion(accionistaDTO.getParticipacion());
            accionista.setFechaUltimaModificacion(Instant.now());
            
            return accionistaMapper.toDto(accionistaRepo.save(accionista));
            
        } catch (NotFoundException e) {
            log.error("Error al actualizar accionista: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al actualizar accionista", e);
            throw new ActualizacionException("Error al actualizar accionista", 2499);
        }
    }

    @Transactional
    public void cambiarEstadoAccionista(Integer id, EstadoRegistro estado) {
        try {
            AccionistasEmpresas accionista = accionistaRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Accionista no encontrado", 3402));
            
            accionista.setEstado(estado.name());
            accionista.setFechaUltimaModificacion(Instant.now());
            accionistaRepo.save(accionista);
            
        } catch (NotFoundException e) {
            log.error("Error al cambiar estado accionista: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al cambiar estado accionista", e);
            throw new ActualizacionException("Error al cambiar estado accionista", 2498);
        }
    }

    public List<AccionistasEmpresasDTO> listarAccionistasActivos(Integer idEmpresa) {
        try {
            if (!empresaRepo.existsById(idEmpresa)) {
                throw new NotFoundException("Empresa no encontrada", 3208);
            }
            
            return accionistaRepo.findByEmpresaIdAndEstado(idEmpresa, EstadoRegistro.ACTIVO.name())
                    .stream()
                    .map(accionistaMapper::toDto)
                    .collect(Collectors.toList());
            
        } catch (NotFoundException e) {
            log.error("Error al listar accionistas: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al listar accionistas", e);
            throw new ActualizacionException("Error al listar accionistas", 2497);
        }
    }

    @Transactional
    public RepresentanteEmpresaDTO agregarRepresentante(RepresentanteEmpresaDTO representanteDTO) {
        try {
            if (!empresaRepo.existsById(representanteDTO.getIdEmpresa())) {
                throw new NotFoundException("Empresa no encontrada", 3209);
            }
            
            Clientes cliente = clienteRepo.findById(representanteDTO.getIdCliente())
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado", 3305));
            
            if (!"PERSONA".equals(cliente.getTipoEntidad())) {
                throw new CreacionException("Representante debe ser persona", 1501);
            }
            
            // if (representanteRepo.existsByEmpresaAndCliente(representanteDTO.getIdEmpresa(), representanteDTO.getIdCliente())) {
            //     throw new CreacionException("Representante ya existe", 1502);
            // }
            
            RepresentantesEmpresas representante = representanteMapper.toNewEntity(representanteDTO);
            representante.setFechaCreacion(Instant.now());
            representante.setFechaUltimaModificacion(Instant.now());
            return representanteMapper.toDto(representanteRepo.save(representante));
            
        } catch (CreacionException | NotFoundException e) {
            log.error("Error al agregar representante: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al agregar representante", e);
            throw new CreacionException("Error al agregar representante", 1599);
        }
    }

    @Transactional
    public RepresentanteEmpresaDTO actualizarRepresentante(Integer id, RepresentanteEmpresaDTO representanteDTO) {
        try {
            RepresentantesEmpresas representante = representanteRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Representante no encontrado", 3501));
            
            representante.setRol(representanteDTO.getRol().name());
            representante.setFechaUltimaModificacion(Instant.now());
            
            return representanteMapper.toDto(representanteRepo.save(representante));
            
        } catch (NotFoundException e) {
            log.error("Error al actualizar representante: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al actualizar representante", e);
            throw new ActualizacionException("Error al actualizar representante", 2599);
        }
    }

    @Transactional
    public void cambiarEstadoRepresentante(Integer id, EstadoRegistro estado) {
        try {
            RepresentantesEmpresas representante = representanteRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Representante no encontrado", 3502));
            
            representante.setEstado(estado.name());
            representante.setFechaUltimaModificacion(Instant.now());
            representanteRepo.save(representante);
            
        } catch (NotFoundException e) {
            log.error("Error al cambiar estado representante: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al cambiar estado representante", e);
            throw new ActualizacionException("Error al cambiar estado representante", 2598);
        }
    }

    // public List<RepresentanteEmpresaDTO> listarRepresentantesActivos(Integer idEmpresa) {
    //     try {
    //         if (!empresaRepo.existsById(idEmpresa)) {
    //             throw new NotFoundException("Empresa no encontrada", 3210);
    //         }
            
    //         // return representanteRepo.findByEmpresaAndEstado(idEmpresa, EstadoRegistro.ACTIVO.name())
    //         //         .stream()
    //         //         .map(representanteMapper::toDto)
    //         //         .collect(Collectors.toList());
            
    //     } catch (NotFoundException e) {
    //         log.error("Error al listar representantes: {}", e.getMessage());
    //         throw e;
    //     } catch (Exception e) {
    //         log.error("Error inesperado al listar representantes", e);
    //         throw new ActualizacionException("Error al listar representantes", 2597);
    //     }
    // }
}