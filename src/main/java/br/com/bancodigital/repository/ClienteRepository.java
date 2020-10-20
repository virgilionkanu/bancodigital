package br.com.bancodigital.repository;

import br.com.bancodigital.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true)
    List<Cliente> findByEmail(String email);

    @Transactional(readOnly = true)
    List<Cliente> findByCpf(String cpf);
}
