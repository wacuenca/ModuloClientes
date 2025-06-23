package com.banquito.core.clientes.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.clientes.modelo.AccionistasEmpresas;
import com.banquito.core.clientes.modelo.Empresas;

@Repository
public interface AccionistasEmpresasRepositorio extends JpaRepository<AccionistasEmpresas, Integer> {
    boolean existsByEmpresaAndParticipe(Empresas empresa, Integer participe);

    List<AccionistasEmpresas> findByEmpresaAndEstado(Empresas empresa, String estado);

}
