package br.com.bancodigital.dto;

import br.com.bancodigital.constraint.Cep;

import javax.validation.constraints.NotNull;

public class EnderecoInput {

    @NotNull
    @Cep
    private String cep;

    @NotNull
    private String rua;

    @NotNull
    private String bairro;

    @NotNull
    private String complemento;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
