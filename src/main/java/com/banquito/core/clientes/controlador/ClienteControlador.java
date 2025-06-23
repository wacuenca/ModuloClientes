package com.banquito.core.clientes.controlador;

import com.banquito.core.clientes.excepcion.*;
import com.banquito.core.clientes.modelo.*;
import com.banquito.core.clientes.servicio.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteControlador {

    private final ClienteService clienteService;

    public ClienteControlador(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // ========== ENDPOINTS PARA PERSONAS ==========

    @PostMapping("/personas")
    public ResponseEntity<?> crearPersona(@RequestBody Persona persona) {
        try {
            log.info("Creando nueva persona");
            Persona nuevaPersona = clienteService.crearPersona(persona);
            return ResponseEntity.ok(nuevaPersona);
        } catch (CreacionException e) {
            log.error("Error al crear persona: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al crear persona", e);
            return ResponseEntity.internalServerError().body("Error al crear persona");
        }
    }

    @GetMapping("/personas/{tipo}/{numero}")
    public ResponseEntity<?> obtenerPersona(
            @PathVariable String tipo, 
            @PathVariable String numero) {
        try {
            log.info("Obteniendo persona con identificacion: {} {}", tipo, numero);
            Persona persona = clienteService.obtenerPersona(tipo, numero);
            return ResponseEntity.ok(persona);
        } catch (NotFoundException e) {
            log.error("Persona no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al obtener persona", e);
            return ResponseEntity.internalServerError().body("Error al obtener persona");
        }
    }

    @GetMapping("/personas/buscar")
    public ResponseEntity<?> buscarPersonas(@RequestParam String nombre) {
        try {
            log.info("Buscando personas con nombre: {}", nombre);
            List<Persona> personas = clienteService.buscarPersonas(nombre);
            return ResponseEntity.ok(personas);
        } catch (NotFoundException e) {
            log.warn("No se encontraron personas: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar personas", e);
            return ResponseEntity.internalServerError().body("Error al buscar personas");
        }
    }

    @PutMapping("/personas/{id}")
    public ResponseEntity<?> actualizarPersona(
            @PathVariable Integer id, 
            @RequestBody Persona persona) {
        try {
            log.info("Actualizando persona con ID: {}", id);
            Persona personaActualizada = clienteService.actualizarPersona(id, persona);
            return ResponseEntity.ok(personaActualizada);
        } catch (NotFoundException e) {
            log.error("Persona no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ActualizacionException e) {
            log.error("Error al actualizar persona: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al actualizar persona", e);
            return ResponseEntity.internalServerError().body("Error al actualizar persona");
        }
    }

    // ========== ENDPOINTS PARA EMPRESAS ==========

    @PostMapping("/empresas")
    public ResponseEntity<?> crearEmpresa(@RequestBody Empresas empresa) {
        try {
            log.info("Creando nueva empresa");
            Empresas nuevaEmpresa = clienteService.crearEmpresa(empresa);
            return ResponseEntity.ok(nuevaEmpresa);
        } catch (CreacionException e) {
            log.error("Error al crear empresa: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al crear empresa", e);
            return ResponseEntity.internalServerError().body("Error al crear empresa");
        }
    }

    @GetMapping("/empresas/{tipo}/{numero}")
    public ResponseEntity<?> obtenerEmpresa(
            @PathVariable String tipo, 
            @PathVariable String numero) {
        try {
            log.info("Obteniendo empresa con identificacion: {} {}", tipo, numero);
            Empresas empresa = clienteService.obtenerEmpresa(tipo, numero);
            return ResponseEntity.ok(empresa);
        } catch (NotFoundException e) {
            log.error("Empresa no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al obtener empresa", e);
            return ResponseEntity.internalServerError().body("Error al obtener empresa");
        }
    }

    @GetMapping("/empresas/buscar/razon")
    public ResponseEntity<?> buscarEmpresasPorRazon(@RequestParam String razonSocial) {
        try {
            log.info("Buscando empresas por razón social: {}", razonSocial);
            List<Empresas> empresas = clienteService.buscarEmpresasPorRazon(razonSocial);
            return ResponseEntity.ok(empresas);
        } catch (NotFoundException e) {
            log.warn("No se encontraron empresas: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar empresas", e);
            return ResponseEntity.internalServerError().body("Error al buscar empresas");
        }
    }

    @GetMapping("/empresas/buscar/nombre")
    public ResponseEntity<?> buscarEmpresasPorNombre(@RequestParam String nombreComercial) {
        try {
            log.info("Buscando empresas por nombre comercial: {}", nombreComercial);
            List<Empresas> empresas = clienteService.buscarEmpresasPorNombre(nombreComercial);
            return ResponseEntity.ok(empresas);
        } catch (NotFoundException e) {
            log.warn("No se encontraron empresas: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar empresas", e);
            return ResponseEntity.internalServerError().body("Error al buscar empresas");
        }
    }

    @PutMapping("/empresas/{id}")
    public ResponseEntity<?> actualizarEmpresa(
            @PathVariable Integer id, 
            @RequestBody Empresas empresa) {
        try {
            log.info("Actualizando empresa con ID: {}", id);
            Empresas empresaActualizada = clienteService.actualizarEmpresa(id, empresa);
            return ResponseEntity.ok(empresaActualizada);
        } catch (NotFoundException e) {
            log.error("Empresa no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ActualizacionException e) {
            log.error("Error al actualizar empresa: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al actualizar empresa", e);
            return ResponseEntity.internalServerError().body("Error al actualizar empresa");
        }
    }

    // ========== ENDPOINTS PARA CLIENTES ==========

    @PostMapping("/personas/{idPersona}/cliente")
    public ResponseEntity<?> crearClientePersona(
            @PathVariable Integer idPersona,
            @RequestBody Clientes cliente) {
        try {
            log.info("Creando cliente desde persona ID: {}", idPersona);
            Clientes nuevoCliente = clienteService.crearClientePersona(idPersona, cliente);
            return ResponseEntity.ok(nuevoCliente);
        } catch (NotFoundException e) {
            log.error("Persona no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (CreacionException e) {
            log.error("Error al crear cliente: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al crear cliente", e);
            return ResponseEntity.internalServerError().body("Error al crear cliente");
        }
    }

    @PostMapping("/empresas/{idEmpresa}/cliente")
    public ResponseEntity<?> crearClienteEmpresa(
            @PathVariable Integer idEmpresa,
            @RequestBody Clientes cliente) {
        try {
            log.info("Creando cliente desde empresa ID: {}", idEmpresa);
            Clientes nuevoCliente = clienteService.crearClienteEmpresa(idEmpresa, cliente);
            return ResponseEntity.ok(nuevoCliente);
        } catch (NotFoundException e) {
            log.error("Empresa no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (CreacionException e) {
            log.error("Error al crear cliente: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al crear cliente", e);
            return ResponseEntity.internalServerError().body("Error al crear cliente");
        }
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable Integer id) {
        try {
            log.info("Obteniendo cliente con ID: {}", id);
            Clientes cliente = clienteService.obtenerCliente(id);
            return ResponseEntity.ok(cliente);
        } catch (NotFoundException e) {
            log.error("Cliente no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al obtener cliente", e);
            return ResponseEntity.internalServerError().body("Error al obtener cliente");
        }
    }

    @GetMapping("/clientes/{tipo}/{numero}")
    public ResponseEntity<?> obtenerCliente(
            @PathVariable String tipo, 
            @PathVariable String numero) {
        try {
            log.info("Obteniendo cliente con identificacion: {} {}", tipo, numero);
            Clientes cliente = clienteService.obtenerCliente(tipo, numero);
            return ResponseEntity.ok(cliente);
        } catch (NotFoundException e) {
            log.error("Cliente no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al obtener cliente", e);
            return ResponseEntity.internalServerError().body("Error al obtener cliente");
        }
    }

    @GetMapping("/clientes/buscar")
    public ResponseEntity<?> buscarClientes(@RequestParam String nombre) {
        try {
            log.info("Buscando clientes con nombre: {}", nombre);
            List<Clientes> clientes = clienteService.buscarClientes(nombre);
            return ResponseEntity.ok(clientes);
        } catch (NotFoundException e) {
            log.warn("No se encontraron clientes: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error inesperado al buscar clientes", e);
            return ResponseEntity.internalServerError().body("Error al buscar clientes");
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> actualizarCliente(
            @PathVariable Integer id, 
            @RequestBody Clientes cliente) {
        try {
            log.info("Actualizando cliente con ID: {}", id);
            Clientes clienteActualizado = clienteService.actualizarCliente(id, cliente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (NotFoundException e) {
            log.error("Cliente no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (ActualizacionException e) {
            log.error("Error al actualizar cliente: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error inesperado al actualizar cliente", e);
            return ResponseEntity.internalServerError().body("Error al actualizar cliente");
        }
    }
}