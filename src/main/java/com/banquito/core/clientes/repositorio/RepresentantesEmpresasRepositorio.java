package com.banquito.core.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.banquito.core.clientes.modelo.RepresentantesEmpresas;

@Repository
public interface RepresentantesEmpresasRepositorio extends JpaRepository<RepresentantesEmpresas, Integer> {

}
