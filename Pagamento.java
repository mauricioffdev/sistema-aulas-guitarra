import java.time.LocalDate;

public class Pagamento {
    private LocalDate data;
    private double valor;
    private String metodo; // Ex: Pix, Dinheiro, Cartão
    private String status; // Ex: Pago, Pendente

    public Pagamento(LocalDate data, double valor, String metodo, String status) {
        this.data = data;
        this.valor = valor;
        this.metodo = metodo;
        this.status = status;
    }

    // Getters e Setters

    public String toString() {
        return "Data: " + data + ", Valor: R$" + valor + ", Método: " + metodo + ", Status: " + status;
    }
}
