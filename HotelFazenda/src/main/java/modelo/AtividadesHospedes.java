package modelo;

public class AtividadesHospedes {
	private int IdHospedeAtividade;
	private Hospedes Hospede;
	private Atividades Atividade;

	public Hospedes getHospede() {
		return Hospede;
	}

	public void setHospede(Hospedes idHospede) {
		Hospede = idHospede;
	}

	public Atividades getAtividade() {
		return Atividade;
	}

	public void setAtividade(Atividades idAtividade) {
		Atividade = idAtividade;
	}

	public int getIdHospedeAtividade() {
		return IdHospedeAtividade;
	}

	public void setIdHospedeAtividade(int idHospedeAtividade) {
		IdHospedeAtividade = idHospedeAtividade;
	}

}
