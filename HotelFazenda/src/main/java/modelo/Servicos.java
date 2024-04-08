package modelo;

public class Servicos {
	private int IdServico, Quantidade;
	private Float PrecoServico,PrecoTotal;
	private String NomeServico;

	public int getIdServico() {
		return IdServico;
	}

	public void setIdServico(int idServicos) {
		IdServico = idServicos;
	}

	public Float getPrecoServico() {
		return PrecoServico;
	}

	public void setPrecoServico(Float precoServico) {
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

	public Float getPrecoTotal() {
		return PrecoTotal;
	}

	public void setPrecoTotal(Float precoTotal) {
		PrecoTotal = precoTotal;
	}

}
