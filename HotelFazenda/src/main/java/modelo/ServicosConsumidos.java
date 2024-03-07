package modelo;

public class ServicosConsumidos {
	
	private int IdServicosConsumidos;
	private Hospedes hospede;
	private Servicos servico;
	private Hospedagens hospedagem;
	
	
	public int getIdServicosConsumidos() {
		return IdServicosConsumidos;
	}

	public void setIdServicosConsumidos(int idServicosConsumidos) {
		IdServicosConsumidos = idServicosConsumidos;
	}


	public Hospedes getHospede() {
		return hospede;
	}

	public void setHospede(Hospedes IdHospede) {
		this.hospede = IdHospede;
	}

	public Servicos getServico() {
		return servico;
	}

	public void setServico(Servicos IdServico) {
		this.servico = IdServico;
	}

	public Hospedagens getHospedagem() {
		return hospedagem;
	}

	public void setHospedagens(Hospedagens IdHospedagens) {
		this.hospedagem = IdHospedagens;
	}
	
	



}
