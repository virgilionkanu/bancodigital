package br.com.bancodigital;

import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Cliente c1 = new Cliente("Virgilio", "Cano", "virgiliocano53@gmail.com", LocalDateTime.now(), "70247394106");
        clienteRepository.save(c1);
    }
}
