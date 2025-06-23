package com.banquito.core.clientes.servicio;

import com.banquito.core.clientes.excepcion.*;
import com.banquito.core.clientes.modelo.*;
import com.banquito.core.clientes.repositorio.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class AccionistaRepresentanteService {

    private final AccionistasEmpresasRepositorio accionistaRepo;
    private final RepresentantesEmpresasRepositorio representanteRepo;
    private final EmpresasRepositorio empresaRepo;
    private final PersonaRepositorio personaRepo;
    private final ClientesRepositorio clienteRepo;

    public AccionistaRepresentanteService(AccionistasEmpresasRepositorio accionistaRepo,
                                        RepresentantesEmpresasRepositorio representanteRepo,
                                        EmpresasRepositorio empresaRepo,
                                        PersonaRepositorio personaRepo,
                                        ClientesRepositorio clienteRepo) {
        this.accionistaRepo = accionistaRepo;
        this.representanteRepo = representanteRepo;
        this.empresaRepo = empresaRepo;
        this.personaRepo = personaRepo;
        this.clienteRepo = clienteRepo;
    }

    @Transactional
    public AccionistasEmpresas agregarAccionista(Integer idEmpresa, Integer idParticipe, BigDecimal participacion, String tipo) {
        try {
            log.info("Agregando accionista {} a empresa {}", idParticipe, idEmpresa);
            
            Empresas empresa = empresaRepo.findById(idEmpresa)
                    .orElseThrow(() -> new NotFoundException("Empresa no encontrada", 3206));
            
            // Validar que el participe sea cliente
            if ("PERSONA".equals(tipo)) {
                if (!clienteRepo.existsByEntidadAndTipo(personaRepo.findById(idParticipe)
                        .orElseThrow(() -> new NotFoundException("Persona no encontrada", 3105)), "PERSONA")) {
                    throw new CreacionException("Persona debe ser cliente", 1401);
                }
            } else if ("EMPRESA".equals(tipo)) {
                if (!clienteRepo.existsByEntidadAndTipo(empresaRepo.findById(idParticipe)
                        .orElseThrow(() -> new NotFoundException("Empresa no encontrada", 3207)), "EMPRESA")) {
                    throw new CreacionException("Empresa debe ser cliente", 1402);
                }
            } else {
                throw new CreacionException("Tipo de participe inválido", 1403);
            }
            
            if (accionistaRepo.existsByEmpresaAndParticipe(empresa, idParticipe)) {
                throw new CreacionException("Accionista ya existe", 1404);
            }
            
            AccionistasEmpresas accionista = new AccionistasEmpresas();
            accionista.setIdEmpresa(empresa);
           // accionista.setIdParticipe(idParticipe);
            accionista.setParticipacion(participacion);
            accionista.setTipoEntidadParticipe(tipo);
            accionista.setEstado("ACTIVO");
            accionista.setFechaActualizacion(Instant.now());
            accionista.setVersion(BigDecimal.ONE);
            
            return accionistaRepo.save(accionista);
        } catch (NotFoundException | CreacionException e) {
            log.error("Error agregar accionista: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al agregar accionista", e);
            throw new CreacionException("Error al agregar accionista", 1499);
        }
    }

    @Transactional
    public AccionistasEmpresas actualizarAccionista(Integer id, BigDecimal participacion) {
        try {
            log.info("Actualizando accionista ID: {}", id);
            
            AccionistasEmpresas accionista = accionistaRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Accionista no encontrado", 3401));
            
            accionista.setParticipacion(participacion);
            accionista.setFechaActualizacion(Instant.now());
            
            return accionistaRepo.save(accionista);
        } catch (NotFoundException e) {
            log.error("Error actualizar accionista: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al actualizar accionista", e);
            throw new ActualizacionException("Error al actualizar accionista", 2499);
        }
    }

    @Transactional
    public void cambiarEstadoAccionista(Integer id, String estado) {
        try {
            log.info("Cambiando estado accionista ID: {}", id);
            
            AccionistasEmpresas accionista = accionistaRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Accionista no encontrado", 3402));
            
            if (!List.of("ACTIVO", "INACTIVO").contains(estado)) {
                throw new ActualizacionException("Estado inválido", 2401);
            }
            
            accionista.setEstado(estado);
            accionista.setFechaActualizacion(Instant.now());
            
            accionistaRepo.save(accionista);
        } catch (NotFoundException | ActualizacionException e) {
            log.error("Error cambiar estado accionista: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al cambiar estado accionista", e);
            throw new ActualizacionException("Error al cambiar estado accionista", 2498);
        }
    }

    public List<AccionistasEmpresas> listarAccionistasActivos(Integer idEmpresa) {
        log.info("Listando accionistas activos empresa ID: {}", idEmpresa);
        
        Empresas empresa = empresaRepo.findById(idEmpresa)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada", 3208));
        
        List<AccionistasEmpresas> accionistas = accionistaRepo.findByEmpresaAndEstado(empresa, "ACTIVO");
        
        if (accionistas.isEmpty()) {
            throw new NotFoundException("No se encontraron accionistas", 3403);
        }
        
        return accionistas;
    }

    // ========== MÉTODOS PARA REPRESENTANTES ==========

    @Transactional
    public RepresentantesEmpresas agregarRepresentante(Integer idEmpresa, Integer idCliente, String rol) {
        try {
            log.info("Agregando representante {} a empresa {}", idCliente, idEmpresa);
            
            Empresas empresa = empresaRepo.findById(idEmpresa)
                    .orElseThrow(() -> new NotFoundException("Empresa no encontrada", 3209));
            
            Clientes cliente = clienteRepo.findById(idCliente)
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado", 3305));
            
            if (!"PERSONA".equals(cliente.getTipoEntidad())) {
                throw new CreacionException("Representante debe ser persona", 1501);
            }
            
            if (representanteRepo.existsByEmpresaAndCliente(empresa, idCliente)) {
                throw new CreacionException("Representante ya existe", 1502);
            }
            
            RepresentantesEmpresas representante = new RepresentantesEmpresas();
            representante.setIdEmpresa(empresa);
            //representante.setIdCliente(idCliente);
            representante.setRol(rol);
            representante.setFechaAsignacion(Instant.now());
            representante.setEstado("ACTIVO");
            representante.setVersion(BigDecimal.ONE);
            
            return representanteRepo.save(representante);
        } catch (NotFoundException | CreacionException e) {
            log.error("Error agregar representante: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al agregar representante", e);
            throw new CreacionException("Error al agregar representante", 1599);
        }
    }

    @Transactional
    public RepresentantesEmpresas actualizarRepresentante(Integer id, String rol) {
        try {
            log.info("Actualizando representante ID: {}", id);
            
            RepresentantesEmpresas representante = representanteRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Representante no encontrado", 3501));
            
            representante.setRol(rol);
           // representante.setFechaActualizacion(Instant.now());
            
            return representanteRepo.save(representante);
        } catch (NotFoundException e) {
            log.error("Error actualizar representante: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al actualizar representante", e);
            throw new ActualizacionException("Error al actualizar representante", 2599);
        }
    }

    @Transactional
    public void cambiarEstadoRepresentante(Integer id, String estado) {
        try {
            log.info("Cambiando estado representante ID: {}", id);
            
            RepresentantesEmpresas representante = representanteRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Representante no encontrado", 3502));
            
            if (!List.of("ACTIVO", "INACTIVO").contains(estado)) {
                throw new ActualizacionException("Estado inválido", 2501);
            }
            
            representante.setEstado(estado);
            //representante.setFechaActualizacion(Instant.now());
            
            representanteRepo.save(representante);
        } catch (NotFoundException | ActualizacionException e) {
            log.error("Error cambiar estado representante: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al cambiar estado representante", e);
            throw new ActualizacionException("Error al cambiar estado representante", 2598);
        }
    }

    public List<RepresentantesEmpresas> listarRepresentantesActivos(Integer idEmpresa) {
        log.info("Listando representantes activos empresa ID: {}", idEmpresa);
        
        Empresas empresa = empresaRepo.findById(idEmpresa)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada", 3210));
        
        List<RepresentantesEmpresas> representantes = representanteRepo.findByEmpresaAndEstado(empresa, "ACTIVO");
        
        if (representantes.isEmpty()) {
            throw new NotFoundException("No se encontraron representantes", 3503);
        }
        
        return representantes;
    }
}