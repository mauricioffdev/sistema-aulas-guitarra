import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aula {
    private LocalDate data;
    private int duracaoMinutos;
    private String observacoes;

    public Aula(LocalDate data, int duracaoMinutos, String observacoes) {
        this.data = data;
        this.duracaoMinutos = duracaoMinutos;
        this.observacoes = observacoes;
    }

    public LocalDate getData() {
        return data;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    @Override
public String toString() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return "Data: " + data.format(formato) +
           " | Duração: " + duracaoMinutos + " min | Obs: " + observacoes;
}

}
