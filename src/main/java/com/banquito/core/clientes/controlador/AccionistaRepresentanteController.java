package com.banquito.core.clientes.controlador;

import com.banquito.core.clientes.servicio.AccionistaRepresentanteService;
import com.banquito.core.clientes.controlador.dto.*;
import com.banquito.core.clientes.enums.EstadoRegistro;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accionistas-representantes")
public class AccionistaRepresentanteController {

    private final AccionistaRepresentanteService servicio;

    public AccionistaRepresentanteController(AccionistaRepresentanteService servicio) {
        this.servicio = servicio;
    }

    // ========== ENDPOINTS PARA ACCIONISTAS ==========

    @PostMapping("/accionistas")
    @ResponseStatus(HttpStatus.CREATED)
    public AccionistasEmpresasDTO crearAccionista(@RequestBody AccionistasEmpresasDTO accionistaDTO) {
        try {
            log.info("Creando nuevo accionista para empresa ID: {}", accionistaDTO.getIdEmpresa());
            return servicio.agregarAccionista(accionistaDTO);
        } catch (Exception e) {
            log.error("Error al crear accionista: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/accionistas/{id}")
    public AccionistasEmpresasDTO actualizarAccionista(
            @PathVariable Integer id, 
            @RequestBody AccionistasEmpresasDTO accionistaDTO) {
        try {
            log.info("Actualizando accionista ID: {}", id);
            return servicio.actualizarAccionista(id, accionistaDTO);
        } catch (Exception e) {
            log.error("Error al actualizar accionista ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @PatchMapping("/accionistas/{id}/estado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cambiarEstadoAccionista(
            @PathVariable Integer id,
            @RequestParam EstadoRegistro estado) {
        try {
            log.info("Cambiando estado de accionista ID: {} a {}", id, estado);
            servicio.cambiarEstadoAccionista(id, estado);
        } catch (Exception e) {
            log.error("Error al cambiar estado de accionista ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @GetMapping("/empresas/{idEmpresa}/accionistas")
    public List<AccionistasEmpresasDTO> listarAccionistasActivos(
            @PathVariable Integer idEmpresa) {
        try {
            log.info("Listando accionistas activos para empresa ID: {}", idEmpresa);
            return servicio.listarAccionistasActivos(idEmpresa);
        } catch (Exception e) {
            log.error("Error al listar accionistas para empresa ID {}: {}", idEmpresa, e.getMessage());
            throw e;
        }
    }

    // ========== ENDPOINTS PARA REPRESENTANTES ==========

    @PostMapping("/representantes")
    @ResponseStatus(HttpStatus.CREATED)
    public RepresentanteEmpresaDTO crearRepresentante(@RequestBody RepresentanteEmpresaDTO representanteDTO) {
        try {
            log.info("Creando nuevo representante para empresa ID: {}", representanteDTO.getIdEmpresa());
            return servicio.agregarRepresentante(representanteDTO);
        } catch (Exception e) {
            log.error("Error al crear representante: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/representantes/{id}")
    public RepresentanteEmpresaDTO actualizarRepresentante(
            @PathVariable Integer id,
            @RequestBody RepresentanteEmpresaDTO representanteDTO) {
        try {
            log.info("Actualizando representante ID: {}", id);
            return servicio.actualizarRepresentante(id, representanteDTO);
        } catch (Exception e) {
            log.error("Error al actualizar representante ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @PatchMapping("/representantes/{id}/estado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cambiarEstadoRepresentante(
            @PathVariable Integer id,
            @RequestParam EstadoRegistro estado) {
        try {
            log.info("Cambiando estado de representante ID: {} a {}", id, estado);
            servicio.cambiarEstadoRepresentante(id, estado);
        } catch (Exception e) {
            log.error("Error al cambiar estado de representante ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    // @GetMapping("/empresas/{idEmpresa}/representantes")
    // public List<RepresentanteEmpresaDTO> listarRepresentantesActivos(
    //         @PathVariable Integer idEmpresa) {
    //     try {
    //         log.info("Listando representantes activos para empresa ID: {}", idEmpresa);
    //         return servicio.listarRepresentantesActivos(idEmpresa);
    //     } catch (Exception e) {
    //         log.error("Error al listar representantes para empresa ID {}: {}", idEmpresa, e.getMessage());
    //         throw e;
    //     }
    // }
}