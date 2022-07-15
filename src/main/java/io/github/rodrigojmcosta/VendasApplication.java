package io.github.rodrigojmcosta;

import io.github.rodrigojmcosta.domain.entity.Cliente;
import io.github.rodrigojmcosta.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Fulano"));
            clientes.save(new Cliente("Rodrigo"));

            boolean existe = clientes.existsByNome("Dougllas");
            System.out.println("EXISTE UM CLIENTE COM O NOME DOUGLAS? " + existe);


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
