package br.com.bancodigital.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;


public class ClienteInput {

    @NotNull(message = "{name.not.null}")
    @NotBlank(message = "{name.not.blank}")
    private String nome;

    @NotNull(message = "{surname.not.null}")
    @NotBlank(message = "{surname.not.blank}")
    private String sobrenome;

    @NotNull(message = "{email.not.null}")
    @NotBlank(message = "{email.not.blank}")
    @Email (message = "{email.not.valid}")
    private String email;

    @NotNull(message = "{date.not.null}")
    @Past(message = "{date.not.past}")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascimento;

    @NotNull(message = "{cpf.not.null}")
    @CPF(message = "{cpf.not.valid}")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }
}
