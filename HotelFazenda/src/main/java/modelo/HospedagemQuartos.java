 package modelo;

public class HospedagemQuartos {
	private int IdHospedagensQuartos;
	private Quartos Quarto;
	private Hospedagens Hospedagen;
	private Hospedes Hospede;
	
	
	public Quartos getQuarto() {
		return Quarto;
	}

	public void setQuarto(Quartos id_Quartos) {
		this.Quarto = id_Quartos;
	}

	public Hospedagens getHospedagem() {
		return Hospedagen;
	}

	public void setHospedagem(Hospedagens hospedagensId) {
		this.Hospedagen = hospedagensId;
	}

	public Hospedes getHospede() {
		return Hospede;
	}

	public void setHospede(Hospedes hospedeId) {
		this.Hospede = hospedeId;
	}

	public int getIdHospedagensQuartos() {
		return IdHospedagensQuartos;
	}

	public void setIdHospedagensQuartos(int idHospedagensQuartos) {
		IdHospedagensQuartos = idHospedagensQuartos;
	}


	
}
