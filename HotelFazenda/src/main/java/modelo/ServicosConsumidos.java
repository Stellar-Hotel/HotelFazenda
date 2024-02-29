package modelo;

public class ServicosConsumidos {
	
	private int IdServicosConsumidos,IdHospede,IdServico,IdHospedagens;
	private Hospede id_hospede;
	private Servicos id_servico;
	private Hospedagens id_hospedagens;
	
	
	public int getIdServicosConsumidos() {
		return IdServicosConsumidos;
	}

	public void setIdServicosConsumidos(int idServicosConsumidos) {
		IdServicosConsumidos = idServicosConsumidos;
	}

	public int getIdHospede() {
		return IdHospede;
	}

	public void setIdHospede(int idHospede) {
		IdHospede = idHospede;
	}

	public int getIdServico() {
		return IdServico;
	}

	public void setIdServico(int idServico) {
		IdServico = idServico;
	}

	public int getIdHospedagens() {
		return IdHospedagens;
	}

	public void setIdHospedagens(int idHospedagens) {
		IdHospedagens = idHospedagens;
	}

	public Hospede getId_hospede() {
		return id_hospede;
	}

	public void setId_hospede(Hospede id_hospede) {
		this.id_hospede = id_hospede;
	}

	public Servicos getId_servico() {
		return id_servico;
	}

	public void setId_servico(Servicos id_servico) {
		this.id_servico = id_servico;
	}

	public Hospedagens getId_hospedagens() {
		return id_hospedagens;
	}

	public void setId_hospedagens(Hospedagens id_hospedagens) {
		this.id_hospedagens = id_hospedagens;
	}
	
	



}
