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
public class ClienteService {

    private final PersonaRepositorio personaRepo;
    private final EmpresasRepositorio empresaRepo;
    private final ClientesRepositorio clienteRepo;

    public ClienteService(PersonaRepositorio personaRepo,
                         EmpresasRepositorio empresaRepo,
                         ClientesRepositorio clienteRepo) {
        this.personaRepo = personaRepo;
        this.empresaRepo = empresaRepo;
        this.clienteRepo = clienteRepo;
    }

    // ========== MÉTODOS PARA PERSONAS ==========

    @Transactional
    public Persona crearPersona(Persona persona) {
        try {
            log.info("Creando persona: {} {}", persona.getTipoIdentificacion(), persona.getNumeroIdentificacion());

            if (personaRepo.existsByTipoAndNumeroIdentificacion(
                    persona.getTipoIdentificacion(), persona.getNumeroIdentificacion())) {
                throw new CreacionException("Persona ya existe", 1101);
            }

            persona.setFechaRegistro(Instant.now());
            persona.setFechaActualizacion(Instant.now());
            persona.setEstado("ACTIVO");
            persona.setVersion(BigDecimal.ONE);

            return personaRepo.save(persona);
        } catch (CreacionException e) {
            log.error("Error crear persona: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear persona", e);
            throw new CreacionException("Error al crear persona", 1199);
        }
    }

    public Persona obtenerPersona(String tipo, String numero) {
        log.info("Obteniendo persona: {} {}", tipo, numero);
        return personaRepo.findByTipoAndNumeroIdentificacion(tipo, numero)
                .orElseThrow(() -> new NotFoundException("Persona no encontrada", 3101));
    }

    public List<Persona> buscarPersonas(String nombre) {
        log.info("Buscando personas: {}", nombre);
        List<Persona> personas = personaRepo.findByNombreLikeOrderByNombreAsc("%" + nombre + "%");

        if (personas.isEmpty()) {
            throw new NotFoundException("No se encontraron personas", 3102);
        }

        return personas.stream().limit(100).toList();
    }

    @Transactional
    public Persona actualizarPersona(Integer id, Persona datos) {
        try {
            log.info("Actualizando persona ID: {}", id);

            Persona persona = personaRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Persona no encontrada", 3103));

            persona.setNombre(datos.getNombre());
            persona.setGenero(datos.getGenero());
            persona.setFechaNacimiento(datos.getFechaNacimiento());
            persona.setEstadoCivil(datos.getEstadoCivil());
            persona.setNivelEstudio(datos.getNivelEstudio());
            persona.setCorreoElectronico(datos.getCorreoElectronico());
            persona.setFechaActualizacion(Instant.now());

            return personaRepo.save(persona);
        } catch (NotFoundException e) {
            log.error("Error actualizar persona: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al actualizar persona", e);
            throw new ActualizacionException("Error al actualizar persona", 2199);
        }
    }

    // ========== MÉTODOS PARA EMPRESAS ==========

    @Transactional
    public Empresas crearEmpresa(Empresas empresa) {
        try {
            log.info("Creando empresa: {} {}", empresa.getTipoIdentificacion(), empresa.getNumeroIdentificacion());

            if (empresaRepo.existsByTipoAndNumeroIdentificacion(
                    empresa.getTipoIdentificacion(), empresa.getNumeroIdentificacion())) {
                throw new CreacionException("Empresa ya existe", 1201);
            }

            empresa.setFechaRegistro(Instant.now());
            empresa.setFechaActualizacion(Instant.now());
            empresa.setEstado("ACTIVO");
            empresa.setVersion(BigDecimal.ONE);

            return empresaRepo.save(empresa);
        } catch (CreacionException e) {
            log.error("Error crear empresa: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear empresa", e);
            throw new CreacionException("Error al crear empresa", 1299);
        }
    }

    public Empresas obtenerEmpresa(String tipo, String numero) {
        log.info("Obteniendo empresa: {} {}", tipo, numero);
        return empresaRepo.findByTipoAndNumeroIdentificacion(tipo, numero)
                .orElseThrow(() -> new NotFoundException("Empresa no encontrada", 3201));
    }

    public List<Empresas> buscarEmpresasPorRazon(String razonSocial) {
        log.info("Buscando empresas: {}", razonSocial);
        List<Empresas> empresas = empresaRepo.findByRazonSocialLikeOrderByRazonSocialAsc("%" + razonSocial + "%");

        if (empresas.isEmpty()) {
            throw new NotFoundException("No se encontraron empresas", 3202);
        }

        return empresas.stream().limit(100).toList();
    }

    public List<Empresas> buscarEmpresasPorNombre(String nombreComercial) {
        log.info("Buscando empresas: {}", nombreComercial);
        List<Empresas> empresas = empresaRepo.findByNombreComercialLikeOrderByNombreComercialAsc("%" + nombreComercial + "%");

        if (empresas.isEmpty()) {
            throw new NotFoundException("No se encontraron empresas", 3203);
        }

        return empresas.stream().limit(100).toList();
    }

    @Transactional
    public Empresas actualizarEmpresa(Integer id, Empresas datos) {
        try {
            log.info("Actualizando empresa ID: {}", id);

            Empresas empresa = empresaRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Empresa no encontrada", 3204));

            empresa.setNombreComercial(datos.getNombreComercial());
            empresa.setRazonSocial(datos.getRazonSocial());
            empresa.setTipo(datos.getTipo());
            empresa.setCorreoElectronico(datos.getCorreoElectronico());
            empresa.setSectorEconomico(datos.getSectorEconomico());
            empresa.setFechaActualizacion(Instant.now());

            return empresaRepo.save(empresa);
        } catch (NotFoundException e) {
            log.error("Error actualizar empresa: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al actualizar empresa", e);
            throw new ActualizacionException("Error al actualizar empresa", 2299);
        }
    }

    // ========== MÉTODOS PARA CLIENTES ==========

    @Transactional
    public Clientes crearClientePersona(Integer idPersona, Clientes cliente) {
        try {
            log.info("Creando cliente desde persona ID: {}", idPersona);

            Persona persona = personaRepo.findById(idPersona)
                    .orElseThrow(() -> new NotFoundException("Persona no encontrada", 3104));

            if (clienteRepo.existsByEntidadAndTipo(persona, "PERSONA")) {
                throw new CreacionException("Persona ya es cliente", 1301);
            }

            cliente.setTipoEntidad("PERSONA");
            //cliente.setIdEntidad(persona);
            cliente.setNombre(persona.getNombre());
            cliente.setTipoIdentificacion(persona.getTipoIdentificacion());
            cliente.setNumeroIdentificacion(persona.getNumeroIdentificacion());
            cliente.setFechaCreacion(Instant.now());
            cliente.setFechaActualizacion(Instant.now());
            cliente.setEstado("ACTIVO");
            cliente.setVersion(BigDecimal.ONE);

            return clienteRepo.save(cliente);
        } catch (NotFoundException | CreacionException e) {
            log.error("Error crear cliente persona: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear cliente persona", e);
            throw new CreacionException("Error al crear cliente persona", 1399);
        }
    }

    @Transactional
    public Clientes crearClienteEmpresa(Integer idEmpresa, Clientes cliente) {
        try {
            log.info("Creando cliente desde empresa ID: {}", idEmpresa);

            Empresas empresa = empresaRepo.findById(idEmpresa)
                    .orElseThrow(() -> new NotFoundException("Empresa no encontrada", 3205));

            if (clienteRepo.existsByEntidadAndTipo(empresa, "EMPRESA")) {
                throw new CreacionException("Empresa ya es cliente", 1302);
            }

            cliente.setTipoEntidad("EMPRESA");
            //cliente.setIdEntidad(empresa);
            cliente.setNombre(empresa.getRazonSocial());
            cliente.setTipoIdentificacion(empresa.getTipoIdentificacion());
            cliente.setNumeroIdentificacion(empresa.getNumeroIdentificacion());
            cliente.setFechaCreacion(Instant.now());
            cliente.setFechaActualizacion(Instant.now());
            cliente.setEstado("ACTIVO");
            cliente.setVersion(BigDecimal.ONE);

            return clienteRepo.save(cliente);
        } catch (NotFoundException | CreacionException e) {
            log.error("Error crear cliente empresa: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear cliente empresa", e);
            throw new CreacionException("Error al crear cliente empresa", 1398);
        }
    }

    public Clientes obtenerCliente(Integer id) {
        log.info("Obteniendo cliente ID: {}", id);
        return clienteRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado", 3301));
    }

    public Clientes obtenerCliente(String tipo, String numero) {
        log.info("Obteniendo cliente: {} {}", tipo, numero);
        return clienteRepo.findByTipoAndNumeroIdentificacion(tipo, numero)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado", 3302));
    }

    public List<Clientes> buscarClientes(String nombre) {
        log.info("Buscando clientes: {}", nombre);
        List<Clientes> clientes = clienteRepo.findByNombreLikeOrderByNombreAsc("%" + nombre + "%");

        if (clientes.isEmpty()) {
            throw new NotFoundException("No se encontraron clientes", 3303);
        }

        return clientes.stream().limit(100).toList();
    }

    @Transactional
    public Clientes actualizarCliente(Integer id, Clientes datos) {
        try {
            log.info("Actualizando cliente ID: {}", id);

            Clientes cliente = clienteRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Cliente no encontrado", 3304));

            cliente.setTipoCliente(datos.getTipoCliente());
            cliente.setSegmento(datos.getSegmento());
            cliente.setCanalAfiliacion(datos.getCanalAfiliacion());
            cliente.setComentarios(datos.getComentarios());
            cliente.setEstado(datos.getEstado());
            cliente.setFechaActualizacion(Instant.now());

            return clienteRepo.save(cliente);
        } catch (NotFoundException e) {
            log.error("Error actualizar cliente: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al actualizar cliente", e);
            throw new ActualizacionException("Error al actualizar cliente", 2399);
        }
    }
}