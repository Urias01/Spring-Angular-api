package com.springAngluar;

import com.springAngluar.models.Chamado;
import com.springAngluar.models.Cliente;
import com.springAngluar.models.Tecnico;
import com.springAngluar.models.enums.Perfil;
import com.springAngluar.models.enums.Prioridade;
import com.springAngluar.models.enums.Status;
import com.springAngluar.repositories.ChamadoRepository;
import com.springAngluar.repositories.ClienteRepository;
import com.springAngluar.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CourseAngularSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseAngularSpringApplication.class, args);
	}

}
