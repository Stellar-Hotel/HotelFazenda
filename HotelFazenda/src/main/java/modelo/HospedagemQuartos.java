package modelo;

public class HospedagemQuartos {
	private int IdHospedagensQuartos,IdQuartos,HospedagensId,HospedeId;
	private Quartos id_Quartos;
	private Hospedagens Hospedagens_id;
	private Hospede Hospede_id;
	
	
	public Quartos getId_Quartos() {
		return id_Quartos;
	}

	public void setId_Quartos(Quartos id_Quartos) {
		this.id_Quartos = id_Quartos;
	}

	public Hospedagens getHospedagens_id() {
		return Hospedagens_id;
	}

	public void setHospedagens_id(Hospedagens hospedagens_id) {
		Hospedagens_id = hospedagens_id;
	}

	public Hospede getHospede_id() {
		return Hospede_id;
	}

	public void setHospede_id(Hospede hospede_id) {
		Hospede_id = hospede_id;
	}

	public int getIdHospedagensQuartos() {
		return IdHospedagensQuartos;
	}

	public void setIdHospedagensQuartos(int idHospedagensQuartos) {
		IdHospedagensQuartos = idHospedagensQuartos;
	}

	public int getIdQuartos() {
		return IdQuartos;
	}

	public void setIdQuartos(int idQuartos) {
		IdQuartos = idQuartos;
	}

	public int getHospedagensId() {
		return HospedagensId;
	}

	public void setHospedagensId(int hospedagensId) {
		HospedagensId = hospedagensId;
	}

	public int getHospedeId() {
		return HospedeId;
	}

	public void setHospedeId(int hospedeId) {
		HospedeId = hospedeId;
	}

	
	
}
