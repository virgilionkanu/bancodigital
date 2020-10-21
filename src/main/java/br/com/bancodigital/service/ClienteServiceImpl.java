package br.com.bancodigital.service;

import br.com.bancodigital.exception.CustomException;
import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.model.Endereco;
import br.com.bancodigital.repository.ClienteRepository;
import br.com.bancodigital.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

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

    private void verificarDuplicidadeEmail(String email) {
        List<Cliente> cliente = clienteRepository.findByEmail(email);
        if(!cliente.isEmpty()) {
            throw new CustomException("E-mail já cadastrado!");
        }
    }

    private void verificarDuplicidadeCpf(String cpf) {
        List<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if(!cliente.isEmpty()) {
            throw new CustomException("CPF já cadastrado!");
        }
    }

    @Override
    public void atualizarEndereco(Integer id, Endereco endereco) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(!clienteOptional.isPresent()) {
            throw new CustomException("Cliente não encontrado!");
        } else {
            Cliente cliente = clienteOptional.get();
            endereco.setCliente(cliente);
            endereco = enderecoRepository.save(endereco);
            cliente.setEndereco(endereco);
            clienteRepository.save(cliente);
        }
    }
}
