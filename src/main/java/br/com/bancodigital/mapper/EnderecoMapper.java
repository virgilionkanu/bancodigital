package br.com.bancodigital.mapper;

import br.com.bancodigital.dto.EnderecoInput;
import br.com.bancodigital.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    Endereco toEntity(EnderecoInput enderecoInput);
}
