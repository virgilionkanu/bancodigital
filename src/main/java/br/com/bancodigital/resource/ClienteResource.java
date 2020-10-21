package br.com.bancodigital.resource;

import br.com.bancodigital.dto.ClienteInput;
import br.com.bancodigital.dto.EnderecoInput;
import br.com.bancodigital.mapper.ClienteMapper;
import br.com.bancodigital.mapper.EnderecoMapper;
import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.model.Endereco;
import br.com.bancodigital.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody ClienteInput clienteInput) throws URISyntaxException {
        Cliente cliente = clienteMapper.toEntity(clienteInput);
        cliente = clienteService.criar(cliente);
        return ResponseEntity.created(new URI("clientes/" + cliente.getId() + "/enderecos")).build();
    }

    @PostMapping("{id}/enderecos")
    public ResponseEntity<Void> atualizarEndereco(@Valid @RequestBody EnderecoInput enderecoInput,
                                                  @PathVariable Integer id) throws URISyntaxException {
        Endereco endereco = enderecoMapper.toEntity(enderecoInput);
        clienteService.atualizarEndereco(id, endereco);
        return ResponseEntity.created(new URI("clientes/" + id + "/documentos")).build();
    }
}
