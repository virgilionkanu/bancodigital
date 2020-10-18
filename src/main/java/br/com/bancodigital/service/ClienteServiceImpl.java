package br.com.bancodigital.service;

import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public Cliente criar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
