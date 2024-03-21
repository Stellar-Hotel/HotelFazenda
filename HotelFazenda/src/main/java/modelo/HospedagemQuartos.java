package modelo;

public class HospedagemQuartos {
	private int IdHospedagemQuarto;
	private Quartos Quarto;
	private Hospedagens Hospedagen;
	private Hospedes Hospede;

	public Quartos getQuarto() {
		return Quarto;
	}

	public void setQuarto(Quartos Quarto) {
		this.Quarto = Quarto;
	}

	public Hospedagens getHospedagem() {
		return Hospedagen;
	}

	public void setHospedagem(Hospedagens Hospedagen) {
		this.Hospedagen = Hospedagen;
	}

	public Hospedes getHospede() {
		return Hospede;
	}

	public void setHospede(Hospedes Hospede) {
		this.Hospede = Hospede;
	}

	public int getIdHospedagemQuarto() {
		return IdHospedagemQuarto;
	}

	public void setIdHospedagemQuarto(int idHospedagensQuartos) {
		IdHospedagemQuarto = idHospedagensQuartos;
	}

}
