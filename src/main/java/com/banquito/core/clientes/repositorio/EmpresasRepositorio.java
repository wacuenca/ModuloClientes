package com.banquito.core.clientes.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.clientes.modelo.Empresas;

@Repository
public interface EmpresasRepositorio extends JpaRepository<Empresas, Integer> {
    Optional<Empresas> findByTipoAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);

    boolean existsByTipoAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);

    List<Empresas> findByRazonSocialLikeOrderByRazonSocialAsc(String razonSocial);

    List<Empresas> findByNombreComercialLikeOrderByNombreComercialAsc(String nombreComercial);
}
