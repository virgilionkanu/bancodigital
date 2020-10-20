package br.com.bancodigital.service;

import br.com.bancodigital.exception.CustomException;
import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente criar(Cliente cliente) {
        validarIdade((cliente.getDataNascimento()));
        verificarDuplicidadeEmail(cliente.getEmail());
        verificarDuplicidadeCpf(cliente.getCpf());
        return clienteRepository.save(cliente);
    }

    public void validarIdade(LocalDate idadeCliente) {
        LocalDate dataAtual = LocalDate.now();
        int resultadoIdade = dataAtual.getYear() - idadeCliente.getYear();

        if( resultadoIdade < 18) {
            throw new CustomException("Idade não pode ser inferior a 18");
        }
    }

    public void verificarDuplicidadeEmail(String email) {
        List<Cliente> cliente = clienteRepository.findByEmail(email);
        if(!cliente.isEmpty()) {
            throw new CustomException("E-mail já cadastrado!");
        }
    }

    public void verificarDuplicidadeCpf(String cpf) {
        List<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if(!cliente.isEmpty()) {
            throw new CustomException("CPF já cadastrado!");
        }
    }
}
