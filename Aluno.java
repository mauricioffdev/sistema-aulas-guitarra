import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String email;
    private String telefone;
    private String plano;
    private List<Aula> historicoAulas;

    public Aluno(String nome, String email, String telefone, String plano) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.plano = plano;
        this.historicoAulas = new ArrayList<>();
    }

    private List<Pagamento> pagamentos = new ArrayList<>();

public void adicionarPagamento(Pagamento pagamento) {
    pagamentos.add(pagamento);
}

public List<Pagamento> getPagamentos() {
    return pagamentos;
}

    public void adicionarAula(Aula aula) {
        historicoAulas.add(aula);
    }

    public List<Aula> getHistoricoAulas() {
        return historicoAulas;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getPlano() {
        return plano;
    }

    @Override
    public String toString() {
        return nome + " | " + plano + " | " + email + " | " + telefone;
    }
}
