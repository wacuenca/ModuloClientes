package com.banquito.core.clientes.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.clientes.modelo.Clientes;

@Repository
public interface ClientesRepositorio extends JpaRepository<Clientes, Integer> {
    Optional<Clientes> findByTipoAndNumeroIdentificacion(String tipo, String numero);

    boolean existsByEntidadAndTipo(Object entidad, String tipo);

    List<Clientes> findByNombreLikeOrderByNombreAsc(String nombre);
}
