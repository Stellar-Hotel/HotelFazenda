package modelo;

public class ServicosConsumidos {

	private int IdServicoConsumido;
	private Hospedes Hospede;
	private Servicos Servico;

	public int getIdServicoConsumido() {
		return IdServicoConsumido;
	}

	public void setIdServicoConsumido(int idServicosConsumidos) {
		IdServicoConsumido = idServicosConsumidos;
	}

	public Hospedes getHospede() {
		return Hospede;
	}

	public void setHospede(Hospedes Hospede) {
		this.Hospede = Hospede;
	}

	public Servicos getServico() {
		return Servico;
	}

	public void setServico(Servicos Servico) {
		this.Servico = Servico;
	}

}
