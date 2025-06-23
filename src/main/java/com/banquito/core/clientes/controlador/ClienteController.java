package com.banquito.core.clientes.controlador;

import com.banquito.core.clientes.servicio.ClienteService;
import com.banquito.core.clientes.controlador.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // ========== ENDPOINTS PARA PERSONAS ==========

    @PostMapping("/personas")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaDTO crearPersona(@RequestBody PersonaDTO personaDTO) {
        log.info("Creando nueva persona con identificacion: {}", personaDTO.getNumeroIdentificacion());
        return clienteService.crearPersona(personaDTO);
    }

    @GetMapping("/personas/{tipo}/{numero}")
    public PersonaDTO obtenerPersona(
            @PathVariable String tipo,
            @PathVariable String numero) {
        log.info("Obteniendo persona con identificacion: {} {}", tipo, numero);
        return clienteService.obtenerPersona(tipo, numero);
    }

    @GetMapping("/personas/buscar")
    public List<PersonaDTO> buscarPersonas(
            @RequestParam String nombre) {
        log.info("Buscando personas con nombre: {}", nombre);
        return clienteService.buscarPersonas(nombre);
    }

    @PutMapping("/personas/{id}")
    public PersonaDTO actualizarPersona(
            @PathVariable Integer id,
            @RequestBody PersonaDTO personaDTO) {
        log.info("Actualizando persona ID: {}", id);
        return clienteService.actualizarPersona(id, personaDTO);
    }

    // ========== ENDPOINTS PARA EMPRESAS ==========

    @PostMapping("/empresas")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresasDTO crearEmpresa(@RequestBody EmpresasDTO empresasDTO) {
        log.info("Creando nueva empresa con identificacion: {}", empresasDTO.getNumeroIdentificacion());
        return clienteService.crearEmpresa(empresasDTO);
    }

    @GetMapping("/empresas/{tipo}/{numero}")
    public EmpresasDTO obtenerEmpresa(
            @PathVariable String tipo,
            @PathVariable String numero) {
        log.info("Obteniendo empresa con identificacion: {} {}", tipo, numero);
        return clienteService.obtenerEmpresa(tipo, numero);
    }

    @GetMapping("/empresas/buscar/razon")
    public List<EmpresasDTO> buscarEmpresasPorRazon(
            @RequestParam String razonSocial) {
        log.info("Buscando empresas por razón social: {}", razonSocial);
        return clienteService.buscarEmpresasPorRazon(razonSocial);
    }

    @PutMapping("/empresas/{id}")
    public EmpresasDTO actualizarEmpresa(
            @PathVariable Integer id,
            @RequestBody EmpresasDTO empresasDTO) {
        log.info("Actualizando empresa ID: {}", id);
        return clienteService.actualizarEmpresa(id, empresasDTO);
    }

    // ========== ENDPOINTS PARA CLIENTES ==========

    @PostMapping("/clientes/persona/{idPersona}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientesDTO crearClientePersona(
            @PathVariable Integer idPersona,
            @RequestBody ClientesDTO clientesDTO) {
        log.info("Creando cliente desde persona ID: {}", idPersona);
        return clienteService.crearClientePersona(idPersona, clientesDTO);
    }

    @GetMapping("/clientes/{id}")
    public ClientesDTO obtenerCliente(@PathVariable Integer id) {
        log.info("Obteniendo cliente ID: {}", id);
        return clienteService.obtenerCliente(id);
    }

    @GetMapping("/clientes/buscar")
    public List<ClientesDTO> buscarClientes(
            @RequestParam String nombre) {
        log.info("Buscando clientes por nombre: {}", nombre);
        return clienteService.buscarClientes(nombre);
    }

    @PutMapping("/clientes/{id}")
    public ClientesDTO actualizarCliente(
            @PathVariable Integer id,
            @RequestBody ClientesDTO clientesDTO) {
        log.info("Actualizando cliente ID: {}", id);
        return clienteService.actualizarCliente(id, clientesDTO);
    }

}