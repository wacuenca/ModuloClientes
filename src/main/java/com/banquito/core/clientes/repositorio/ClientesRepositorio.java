package com.banquito.core.clientes.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.clientes.modelo.Clientes;

@Repository
public interface ClientesRepositorio extends JpaRepository<Clientes, Integer> {

    

    List<Clientes> findByNombreLikeOrderByNombreAsc(String nombre);
}
