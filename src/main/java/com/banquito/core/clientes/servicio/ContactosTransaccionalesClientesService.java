package com.banquito.core.clientes.servicio;

import com.banquito.core.clientes.controlador.dto.ContactoTransaccionalClienteDTO;
import com.banquito.core.clientes.enums.EstadoGeneralEnum;
import com.banquito.core.clientes.excepcion.NotFoundException;
import com.banquito.core.clientes.excepcion.ValidacionException;
import com.banquito.core.clientes.modelo.Clientes;
import com.banquito.core.clientes.modelo.ContactosTransaccionalesClientes;
import com.banquito.core.clientes.repositorio.ClientesRepositorio;
import com.banquito.core.clientes.repositorio.ContactosTransaccionalesClientesRepositorio;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactosTransaccionalesClientesService {

    private final ContactosTransaccionalesClientesRepositorio contactoRepo;
    private final ClientesRepositorio clienteRepo;

    public ContactosTransaccionalesClientesService(ContactosTransaccionalesClientesRepositorio contactoRepo,
                                                   ClientesRepositorio clienteRepo) {
        this.contactoRepo = contactoRepo;
        this.clienteRepo = clienteRepo;
    }

    @Transactional
    public ContactoTransaccionalClienteDTO crearContacto(Integer idCliente, ContactoTransaccionalClienteDTO dto) {
        Clientes cliente = clienteRepo.findById(idCliente)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado", 4001));

        ContactosTransaccionalesClientes entidad = new ContactosTransaccionalesClientes();
        entidad.setIdCliente(cliente);
        entidad.setTelefono(dto.getTelefono());
        entidad.setCorreoElectronico(dto.getCorreo());
        entidad.setEstado(dto.getEstado().name());
        entidad.setFechaCreacion(Instant.now());
        entidad.setFechaActualizacion(Instant.now());
        entidad.setVersion(BigDecimal.ONE);

        var guardado = contactoRepo.save(entidad);
        return mapToDTO(guardado);
    }


    public ContactoTransaccionalClienteDTO obtenerContacto(Integer idContacto) {
        var entidad = contactoRepo.findById(idContacto)
                .orElseThrow(() -> new NotFoundException("Contacto no encontrado", 4002));

        return mapToDTO(entidad);
    }

    public List<ContactoTransaccionalClienteDTO> listarContactosPorCliente(Integer idCliente) {
        clienteRepo.findById(idCliente)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado", 4003));

        Optional<ContactosTransaccionalesClientes> contactos = contactoRepo.findById(idCliente);
        if (contactos.isEmpty()) {
            throw new NotFoundException("No hay contactos para este cliente", 4004);
        }
        return contactos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional
    public ContactoTransaccionalClienteDTO actualizarContacto(Integer idContacto, ContactoTransaccionalClienteDTO dto) {
        var entidad = contactoRepo.findById(idContacto)
                .orElseThrow(() -> new NotFoundException("Contacto no encontrado", 4005));

        entidad.setTelefono(dto.getTelefono());
        entidad.setCorreoElectronico(dto.getCorreo());
        entidad.setEstado(dto.getEstado().name());
        entidad.setFechaActualizacion(Instant.now());

        var actualizado = contactoRepo.save(entidad);
        return mapToDTO(actualizado);
    }

    @Transactional
    public void cambiarEstadoContacto(Integer idContacto, EstadoGeneralEnum nuevoEstado) {
        if (nuevoEstado == null) {
            throw new ValidacionException("Estado no puede ser nulo", 4401);
        }
        var entidad = contactoRepo.findById(idContacto)
                .orElseThrow(() -> new NotFoundException("Contacto no encontrado", 4006));

        entidad.setEstado(nuevoEstado.name());
        entidad.setFechaActualizacion(Instant.now());

        contactoRepo.save(entidad);
    }

    private ContactoTransaccionalClienteDTO mapToDTO(ContactosTransaccionalesClientes entidad) {
        return ContactoTransaccionalClienteDTO.builder()
                .telefono(entidad.getTelefono())
                .correo(entidad.getCorreoElectronico())
                .estado(EstadoGeneralEnum.valueOf(entidad.getEstado()))
                .build();
    }
}
