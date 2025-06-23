package com.banquito.core.clientes.controlador;

import com.banquito.core.clientes.excepcion.*;
import com.banquito.core.clientes.modelo.*;
import com.banquito.core.clientes.servicio.AccionistaRepresentanteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/empresas")
public class AccionistaRepresentanteControlador {

    private final AccionistaRepresentanteService accionistaRepresentanteService;

    public AccionistaRepresentanteControlador(AccionistaRepresentanteService accionistaRepresentanteService) {
        this.accionistaRepresentanteService = accionistaRepresentanteService;
    }


    @PostMapping("/{idEmpresa}/accionistas/{idParticipe}")
    public ResponseEntity<?> agregarAccionista(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idParticipe,
            @RequestParam BigDecimal participacion,
            @RequestParam String tipo) {
        try {
            log.info("Agregando accionista {} a empresa {}", idParticipe, idEmpresa);
            AccionistasEmpresas accionista = accionistaRepresentanteService.agregarAccionista(
                    idEmpresa, idParticipe, participacion, tipo);
            return ResponseEntity.ok(accionista);
        } catch (NotFoundException e) {
            log.error("Recurso no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (CreacionException e) {
            log.error("Error al agregar accionista: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al agregar accionista", e);
            return ResponseEntity.internalServerError().body("Error al agregar accionista");
        }
    }

    @PutMapping("/accionistas/{id}")
    public ResponseEntity<?> actualizarAccionista(
            @PathVariable Integer id,
            @RequestParam BigDecimal participacion) {
        try {
            log.info("Actualizando accionista con ID: {}", id);
            AccionistasEmpresas accionista = accionistaRepresentanteService.actualizarAccionista(id, participacion);
            return ResponseEntity.ok(accionista);
        } catch (NotFoundException e) {
            log.error("Accionista no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ActualizacionException e) {
            log.error("Error al actualizar accionista: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al actualizar accionista", e);
            return ResponseEntity.internalServerError().body("Error al actualizar accionista");
        }
    }

    @PatchMapping("/accionistas/{id}/estado")
    public ResponseEntity<?> cambiarEstadoAccionista(
            @PathVariable Integer id,
            @RequestParam String estado) {
        try {
            log.info("Cambiando estado de accionista ID: {}", id);
            accionistaRepresentanteService.cambiarEstadoAccionista(id, estado);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            log.error("Accionista no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ActualizacionException e) {
            log.error("Error al cambiar estado: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al cambiar estado", e);
            return ResponseEntity.internalServerError().body("Error al cambiar estado");
        }
    }

    @GetMapping("/{idEmpresa}/accionistas")
    public ResponseEntity<?> listarAccionistasActivos(@PathVariable Integer idEmpresa) {
        try {
            log.info("Listando accionistas activos de empresa ID: {}", idEmpresa);
            List<AccionistasEmpresas> accionistas = accionistaRepresentanteService.listarAccionistasActivos(idEmpresa);
            return ResponseEntity.ok(accionistas);
        } catch (NotFoundException e) {
            log.error("Empresa no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al listar accionistas", e);
            return ResponseEntity.internalServerError().body("Error al listar accionistas");
        }
    }

    // ========== ENDPOINTS PARA REPRESENTANTES ==========

    @PostMapping("/{idEmpresa}/representantes/{idCliente}")
    public ResponseEntity<?> agregarRepresentante(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idCliente,
            @RequestParam String rol) {
        try {
            log.info("Agregando representante {} a empresa {}", idCliente, idEmpresa);
            RepresentantesEmpresas representante = accionistaRepresentanteService.agregarRepresentante(
                    idEmpresa, idCliente, rol);
            return ResponseEntity.ok(representante);
        } catch (NotFoundException e) {
            log.error("Recurso no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (CreacionException e) {
            log.error("Error al agregar representante: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al agregar representante", e);
            return ResponseEntity.internalServerError().body("Error al agregar representante");
        }
    }

    @PutMapping("/representantes/{id}")
    public ResponseEntity<?> actualizarRepresentante(
            @PathVariable Integer id,
            @RequestParam String rol) {
        try {
            log.info("Actualizando representante con ID: {}", id);
            RepresentantesEmpresas representante = accionistaRepresentanteService.actualizarRepresentante(id, rol);
            return ResponseEntity.ok(representante);
        } catch (NotFoundException e) {
            log.error("Representante no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ActualizacionException e) {
            log.error("Error al actualizar representante: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al actualizar representante", e);
            return ResponseEntity.internalServerError().body("Error al actualizar representante");
        }
    }

    @PatchMapping("/representantes/{id}/estado")
    public ResponseEntity<?> cambiarEstadoRepresentante(
            @PathVariable Integer id,
            @RequestParam String estado) {
        try {
            log.info("Cambiando estado de representante ID: {}", id);
            accionistaRepresentanteService.cambiarEstadoRepresentante(id, estado);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            log.error("Representante no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ActualizacionException e) {
            log.error("Error al cambiar estado: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al cambiar estado", e);
            return ResponseEntity.internalServerError().body("Error al cambiar estado");
        }
    }

    @GetMapping("/{idEmpresa}/representantes")
    public ResponseEntity<?> listarRepresentantesActivos(@PathVariable Integer idEmpresa) {
        try {
            log.info("Listando representantes activos de empresa ID: {}", idEmpresa);
            List<RepresentantesEmpresas> representantes = accionistaRepresentanteService.listarRepresentantesActivos(idEmpresa);
            return ResponseEntity.ok(representantes);
        } catch (NotFoundException e) {
            log.error("Empresa no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al listar representantes", e);
            return ResponseEntity.internalServerError().body("Error al listar representantes");
        }
    }
}
