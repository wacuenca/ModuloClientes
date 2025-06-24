package com.banquito.core.clientes.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.clientes.modelo.Persona;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByTipoIdentificacionAndNumeroIdentificacion(String tipo, String numeroIdentificacion);

    boolean existsByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);

    List<Persona> findByNombreLikeOrderByNombreAsc(String nombre);

}
