package modelo;

public class Servicos {
    private int IdServico, Quantidade;
    private double PrecoServico;
    private String NomeServico;

    // Construtor padrão
    public Servicos() {
    }

    public Servicos(String NomeServico, double PrecoServico) {
        this.NomeServico = NomeServico;
        this.PrecoServico = PrecoServico;
    }

    public int getIdServico() {
        return IdServico;
    }

    public void setIdServico(int idServico) {
        IdServico = idServico;
    }

    public double getPrecoServico() {
        return PrecoServico;
    }

    public void setPrecoServico(double precoServico) {
        PrecoServico = precoServico;
    }

    public String getNomeServico() {
        return NomeServico;
    }

    public void setNomeServico(String nomeServico) {
        NomeServico = nomeServico;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }
}
