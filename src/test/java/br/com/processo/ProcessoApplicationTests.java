package br.com.processo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.processo.dto.ProcessoDto;
import br.com.processo.repository.ProcessoRepository;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProcessoApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ProcessoRepository processoRepository;

	@Autowired
	private RestTemplateBuilder builder;

	private ProcessoDto processo;

	@BeforeAll
	public void start() {

		processo = new ProcessoDto("Codigo processo testadefas");
	}

	@Test
	@Order(1)
    @DisplayName("Cadastrar processo!")
	public void deveRealizarPostProcesso() {
		HttpEntity<ProcessoDto> request = new HttpEntity<ProcessoDto>(processo);

		ResponseEntity<String> resposta = testRestTemplate
			.exchange("/processos", HttpMethod.POST, request, String.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());

	}


	@Test
	@Order(2)
    @DisplayName("Processo já cadastrado!")
	public void deveDarErroAoRealizarPostProcessoJaCadastrado() {
		HttpEntity<ProcessoDto> request = new HttpEntity<ProcessoDto>(processo);

		ResponseEntity<String> resposta = testRestTemplate
			.exchange("/processos", HttpMethod.POST, request, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());

	}
}
