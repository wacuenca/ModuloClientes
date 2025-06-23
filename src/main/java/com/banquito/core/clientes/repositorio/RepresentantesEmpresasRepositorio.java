package com.banquito.core.clientes.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.clientes.modelo.Empresas;
import com.banquito.core.clientes.modelo.RepresentantesEmpresas;

@Repository
public interface RepresentantesEmpresasRepositorio extends JpaRepository<RepresentantesEmpresas, Integer> {
    boolean existsByEmpresaAndCliente(Empresas empresa, Integer cliente);

    List<RepresentantesEmpresas> findByEmpresaAndEstado(Empresas empresa, String estado);

}
