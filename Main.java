import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    private static List<Aluno> alunos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        do {
            try {
                System.out.println("\n=== Sistema de Aulas de Guitarra ===");
                System.out.println("1. Cadastrar novo aluno");
                System.out.println("2. Listar alunos");
                System.out.println("3. Registrar aula para aluno");
                System.out.println("4. Registrar pagamento");
                System.out.println("5. Ver histórico de pagamentos de um aluno");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> cadastrarAluno();
                    case 2 -> listarAlunos();
                    case 3 -> registrarAula();
                    case 4 -> registrarPagamento();
                    case 5 -> verHistoricoPagamentos();
                    case 0 -> System.out.println("Encerrando o sistema.");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                opcao = -1;
            }
        } while (opcao != 0);
    }

    private static void cadastrarAluno() {
        String nome = lerTexto("Nome");
        String email = lerTexto("Email");
        String telefone = lerTexto("Telefone");
        String plano = lerTexto("Plano (Mensal, Avulso, etc)");

        Aluno aluno = new Aluno(nome, email, telefone, plano);
        alunos.add(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Alunos ---");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println((i + 1) + ". " + alunos.get(i));
        }
    }

    private static void registrarAula() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        listarAlunos();
        int index = lerIndiceAluno();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;
        while (data == null) {
            try {
                System.out.print("Data da aula (dd/MM/yyyy): ");
                String input = scanner.nextLine();
                data = LocalDate.parse(input, formato);
            } catch (Exception e) {
                System.out.println("Formato inválido. Use: 12/07/2025");
            }
        }

        int duracao = -1;
        while (duracao < 0) {
            try {
                System.out.print("Duração em minutos: ");
                duracao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Digite apenas números. Ex: 60");
            }
        }

        System.out.print("Observações: ");
        String obs = scanner.nextLine();

        Aula aula = new Aula(data, duracao, obs);
        alunos.get(index).adicionarAula(aula);
        System.out.println("Aula registrada com sucesso!");
    }

    private static void registrarPagamento() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        listarAlunos();
        int index = lerIndiceAluno();
        Aluno aluno = alunos.get(index);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;
        while (data == null) {
            try {
                System.out.print("Data do pagamento (dd/MM/yyyy): ");
                data = LocalDate.parse(scanner.nextLine(), formato);
            } catch (Exception e) {
                System.out.println("Formato inválido. Use: 12/07/2025");
            }
        }

        double valor = -1;
        while (valor < 0) {
            try {
                System.out.print("Valor pago: ");
                valor = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Digite um valor válido. Ex: 100.00");
            }
        }

        String metodo = lerTexto("Método de pagamento (Pix, Dinheiro, etc)");
        String status = lerTexto("Status (Pago ou Pendente)");

        Pagamento pagamento = new Pagamento(data, valor, metodo, status);
        aluno.adicionarPagamento(pagamento);
        System.out.println("Pagamento registrado com sucesso!");
    }

    private static void verHistoricoPagamentos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        listarAlunos();
        int index = lerIndiceAluno();
        Aluno aluno = alunos.get(index);

        List<Pagamento> pagamentos = aluno.getPagamentos();
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado para este aluno.");
        } else {
            System.out.println("\n--- Histórico de Pagamentos ---");
            for (Pagamento p : pagamentos) {
                System.out.println(p);
            }
        }
    }

    private static int lerIndiceAluno() {
        int index = -1;
        while (index < 0 || index >= alunos.size()) {
            try {
                System.out.print("Escolha o número do aluno: ");
                index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index < 0 || index >= alunos.size()) {
                    System.out.println("Número inválido. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Digite um número válido.");
            }
        }
        return index;
    }

    private static String lerTexto(String campo) {
        String valor = "";
        while (valor.isBlank()) {
            System.out.print(campo + ": ");
            valor = scanner.nextLine().trim();
            if (valor.isBlank()) {
                System.out.println("Campo não pode estar vazio.");
            }
        }
        return valor;
    }
}
