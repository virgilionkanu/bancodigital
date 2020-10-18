package br.com.bancodigital.mapper;

import br.com.bancodigital.dto.ClienteInput;
import br.com.bancodigital.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(ClienteInput clienteInput);
}
