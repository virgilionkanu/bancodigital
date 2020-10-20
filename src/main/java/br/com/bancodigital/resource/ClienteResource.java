package br.com.bancodigital.resource;

import br.com.bancodigital.dto.ClienteInput;
import br.com.bancodigital.exception.ResourceNotFoundException;
import br.com.bancodigital.mapper.ClienteMapper;
import br.com.bancodigital.model.Cliente;
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
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody ClienteInput clienteInput) throws URISyntaxException {
        Cliente cliente = clienteMapper.toEntity(clienteInput);
        cliente = clienteService.criar(cliente);
        return ResponseEntity.created(new URI("clientes/" + cliente.getId() + "/enderecos")).build();
    }
}
