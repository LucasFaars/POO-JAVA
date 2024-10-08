package Application;

import java.util.Scanner;
import DAO.EventoDAO;
import Model.Evento;
import Service.RegrasNegocio;

public class Main {

    public static void main(String[] args) {

        int opcao;

        Scanner scanner = new Scanner(System.in);
        EventoDAO evento_dao = new EventoDAO();
        
        
        evento_dao.CriarTabela();

        System.out.println("======================================================");
        System.out.println("Seja bem-vindo(a) ao Calendário de Eventos!");
        System.out.println("======================================================");

        do {
            System.out.println("\n======================================================");
            System.out.println("Escolha uma das opções a seguir:\n\n[1] - " +
                "Consultar Todos os Eventos\n[2] - Cadastrar um Evento\n[3] - Atualizar um evento\n[4] - " +
                "Excluir um Evento\n[5] - Sair da Aplicação\n");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

            case 1:
                RegrasNegocio exibir_eventos = new RegrasNegocio();
                exibir_eventos.ExibirEvento();
                break;

            case 2:
                RegrasNegocio exibir_inputs_cadastrar_evento = new RegrasNegocio();
                Evento novo_evento = exibir_inputs_cadastrar_evento.ExibirInputsCadastrarEvento();
                evento_dao.PublicarEvento(novo_evento);
                break;

            case 3:
                RegrasNegocio exibir_inputs_atualizar_evento = new RegrasNegocio();
                Evento nova_atualizacao_evento = exibir_inputs_atualizar_evento.ExibirInputsAtualizarEvento();
                evento_dao.AtualizarEvento(nova_atualizacao_evento);
                break;

            case 4:
                RegrasNegocio receber_id_deletar_evento = new RegrasNegocio();
                int id = receber_id_deletar_evento.ReceberIdDeletarEvento();
                evento_dao.ExcluirEvento(id);
                break;

            case 5:
                RegrasNegocio encerrar_app = new RegrasNegocio();
                encerrar_app.EncerrarApp();
                break;

            default:
                System.out.println("\n=================================================");
                System.out.println("Você Digitou uma opção inválida, Tente Novamente!");
                System.out.println("=================================================");
            }

        } while (opcao != 5);

        scanner.close();
    }
}
