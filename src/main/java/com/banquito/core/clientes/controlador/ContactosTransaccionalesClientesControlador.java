package com.banquito.core.clientes.controlador;

import com.banquito.core.clientes.controlador.dto.ContactoTransaccionalClienteDTO;
import com.banquito.core.clientes.enums.EstadoGeneralEnum;
import com.banquito.core.clientes.excepcion.*;
import com.banquito.core.clientes.servicio.ContactosTransaccionalesClientesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
public class ContactosTransaccionalesClientesControlador {

    private final ContactosTransaccionalesClientesService contactoService;

    public ContactosTransaccionalesClientesControlador(ContactosTransaccionalesClientesService contactoService) {
        this.contactoService = contactoService;
    }

    @PostMapping("/{idCliente}/contactos")
    public ResponseEntity<?> crearContacto(
            @PathVariable Integer idCliente,
            @RequestBody ContactoTransaccionalClienteDTO dto) {
        try {
            log.info("Creando contacto para cliente ID: {}", idCliente);
            ContactoTransaccionalClienteDTO contactoCreado = contactoService.crearContacto(idCliente, dto);
            return ResponseEntity.ok(contactoCreado);
        } catch (NotFoundException e) {
            log.error("Cliente no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ValidacionException e) {
            log.error("Validación errónea: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al crear contacto", e);
            return ResponseEntity.internalServerError().body("Error al crear contacto");
        }
    }

    @GetMapping("/contactos/{idContacto}")
    public ResponseEntity<?> obtenerContacto(@PathVariable Integer idContacto) {
        try {
            log.info("Obteniendo contacto ID: {}", idContacto);
            ContactoTransaccionalClienteDTO contacto = contactoService.obtenerContacto(idContacto);
            return ResponseEntity.ok(contacto);
        } catch (NotFoundException e) {
            log.error("Contacto no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al obtener contacto", e);
            return ResponseEntity.internalServerError().body("Error al obtener contacto");
        }
    }

    @GetMapping("/{idCliente}/contactos")
    public ResponseEntity<?> listarContactosPorCliente(@PathVariable Integer idCliente) {
        try {
            log.info("Listando contactos para cliente ID: {}", idCliente);
            List<ContactoTransaccionalClienteDTO> contactos = contactoService.listarContactosPorCliente(idCliente);
            return ResponseEntity.ok(contactos);
        } catch (NotFoundException e) {
            log.error("Cliente o contactos no encontrados: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al listar contactos", e);
            return ResponseEntity.internalServerError().body("Error al listar contactos");
        }
    }

    @PutMapping("/contactos/{idContacto}")
    public ResponseEntity<?> actualizarContacto(
            @PathVariable Integer idContacto,
            @RequestBody ContactoTransaccionalClienteDTO dto) {
        try {
            log.info("Actualizando contacto ID: {}", idContacto);
            ContactoTransaccionalClienteDTO actualizado = contactoService.actualizarContacto(idContacto, dto);
            return ResponseEntity.ok(actualizado);
        } catch (NotFoundException e) {
            log.error("Contacto no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ValidacionException e) {
            log.error("Validación errónea: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al actualizar contacto", e);
            return ResponseEntity.internalServerError().body("Error al actualizar contacto");
        }
    }

    @PatchMapping("/contactos/{idContacto}/estado")
    public ResponseEntity<?> cambiarEstadoContacto(
            @PathVariable Integer idContacto,
            @RequestParam EstadoGeneralEnum estado) {
        try {
            log.info("Cambiando estado de contacto ID: {} a {}", idContacto, estado);
            contactoService.cambiarEstadoContacto(idContacto, estado);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            log.error("Contacto no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ValidacionException e) {
            log.error("Validación errónea: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al cambiar estado de contacto", e);
            return ResponseEntity.internalServerError().body("Error al cambiar estado de contacto");
        }
    }
}
