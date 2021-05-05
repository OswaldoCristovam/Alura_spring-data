package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		system = true;
		while (system) {
			System.out.println("Qual acao de cargo deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcinario nome");
			System.out.println("2 - Buscar funcinario por nome, data contratacao e salario");
			System.out.println("3 - Buscar funcinario por data contratacao");
			System.out.println("4 - Pesquisar funcionario salario");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				buscarFuncionarioNome(scanner);
				break;
			case 2:
				buscarFuncionarioNomeSalarioData(scanner);
				break;
			case 3:
				buscarFuncionarioData(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void buscarFuncionarioNome(Scanner scanner) {
		
		System.out.println("Qual nome do funcionario");
		String nome = scanner.next();
		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		lista.forEach(System.out::println);
	}
	
	private void buscarFuncionarioNomeSalarioData(Scanner scanner) {
		System.out.println("Qual nome do funcionario");
		String nome = scanner.next();
		System.out.println("Qual data contratacao do funcionario");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, format);
		System.out.println("Qual salario do funcionario");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> lista = funcionarioRepository.findByNomeSalarioDataContrtado(nome, salario, localDate);
		lista.forEach(System.out::println);
	}
	
	private void buscarFuncionarioData(Scanner scanner) {
		System.out.println("Qual data contratacao do funcionario");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, format);
		
		List<Funcionario> lista = funcionarioRepository.findByDataContratado(localDate);
		lista.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() + 
														  "nome: " + f.getNome() + 
														  "salario: " + f.getSalario()));
	}
}
