package br.com.bancodigital.service;

import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.model.Endereco;

public interface ClienteService {
    Cliente criar(Cliente cliente);
    void atualizarEndereco(Integer id, Endereco endereco);
}
