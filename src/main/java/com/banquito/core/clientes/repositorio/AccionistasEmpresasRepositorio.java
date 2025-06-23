package com.banquito.core.clientes.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.clientes.modelo.AccionistasEmpresas;

@Repository
public interface AccionistasEmpresasRepositorio extends JpaRepository<AccionistasEmpresas, Integer> {

    boolean existsByEmpresaIdAndEmpresaParticipeId(Integer empresaId, Integer empresaParticipeId);
    
    boolean existsByEmpresaIdAndPersonaParticipeId(Integer empresaId, Integer personaParticipeId);
    
    List<AccionistasEmpresas> findByEmpresaIdAndEstado(Integer empresaId, String estado);
    
    List<AccionistasEmpresas> findByEmpresaParticipeIdAndEstado(Integer empresaParticipeId, String estado);
    
    List<AccionistasEmpresas> findByPersonaParticipeIdAndEstado(Integer personaParticipeId, String estado);
}


