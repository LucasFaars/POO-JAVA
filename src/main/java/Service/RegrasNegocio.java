package Service;

import java.util.Scanner;
import Model.Evento;
import DAO.EventoDAO;

public class RegrasNegocio {

	Scanner scanner = new Scanner(System.in);
	EventoDAO evento_dao = new EventoDAO();

	public void ExibirEvento() {
		System.out.println("\nTodos os Eventos:");

		for (Evento eventos : evento_dao.ConsultarTodosEventos()) {
			System.out.println("\n================================================================================");
			System.out.println("ID --> " + eventos.getId() + "\nNome --> " + eventos.getNome() + "\nDescrição --> " + eventos.getDescricao() + "\nLocal --> " + eventos.getLocal() + "\nData --> " + eventos.getData() + "\nHorário --> " + eventos.getHorario());
			System.out.println("================================================================================");
		}
	}

	public Evento ExibirInputsCadastrarEvento() {
		System.out.print("\nNome do Evento: ");
		String nome = scanner.nextLine();

		System.out.print("Descrição do Evento: ");
		String descricao = scanner.nextLine();

		System.out.print("Local do Evento: ");
		String local = scanner.nextLine();

		System.out.print("Data do Evento (Ex: 18/06/2024): ");
		String data = scanner.nextLine();

		System.out.print("Horário do Evento (Ex: 19:30): ");
		String horario = scanner.nextLine();
		
		

		return new Evento(nome, descricao, local, data, horario);
		
	}

	public Evento ExibirInputsAtualizarEvento() {
		System.out.print("\nInforme o ID do evento a ser atualizado: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("\nNome do Evento: ");
		String nome = scanner.nextLine();

		System.out.print("Descrição do Evento: ");
		String descricao = scanner.nextLine();

		System.out.print("Local do Evento: ");
		String local = scanner.nextLine();

		System.out.print("Data do Evento (EX: 18/06/2024): ");
		String data = scanner.nextLine();

		System.out.print("Horário do Evento (Ex: 19:30): ");
		String horario = scanner.nextLine();
		
		System.out.println("\nEvento de Id: " + id + ", Atualizado com Sucesso!");

		return new Evento(id, nome, descricao, local, data, horario);
	}

	public int ReceberIdDeletarEvento() {
		System.out.print("\nInforme o ID do evento a ser excluído: ");
		int id = scanner.nextInt();
		
		System.out.println("\nEvento de Id: " + id + ", foi Excluído com Sucesso!");

		return id;
	}

	public void EncerrarApp() {
		System.out.println("\nEncerrando aplicação...");
		System.exit(0);
	}

}
