package modelo;

public class ServicosConsumidos {
	
	private int IdServicoConsumido;
	private Hospedes Hospede;
	private Servicos Servico;
	private Hospedagens Hospedagem;
	
	
	public int getIdServicosConsumidos() {
		return IdServicoConsumido;
	}

	public void setIdServicosConsumidos(int idServicosConsumidos) {
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

	public Hospedagens getHospedagem() {
		return Hospedagem;
	}

	public void setHospedagens(Hospedagens Hospedagem) {
		this.Hospedagem = Hospedagem;
	}
	
	



}
