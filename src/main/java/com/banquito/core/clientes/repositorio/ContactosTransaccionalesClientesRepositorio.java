package com.banquito.core.clientes.repositorio;

import com.banquito.core.clientes.modelo.Clientes;
import com.banquito.core.clientes.modelo.ContactosTransaccionalesClientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactosTransaccionalesClientesRepositorio extends JpaRepository<ContactosTransaccionalesClientes, Integer> {

    List<ContactosTransaccionalesClientes> findById(Clientes id);
}
