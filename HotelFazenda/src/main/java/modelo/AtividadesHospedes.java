package modelo;
 
public class AtividadesHospedes {
    private int IdAtividadesHospedes,IdHospede,IdAtividade;
    private Hospede Hospede_id;
    private Atividades id_atividade;
    
    
	public int getIdAtividadesHospedes() {
		return IdAtividadesHospedes;
	}

	public int getIdHospede() {
		return IdHospede;
	}

	public void setIdHospede(int idHospede) {
		IdHospede = idHospede;
	}

	public int getIdAtividade() {
		return IdAtividade;
	}

	public void setIdAtividade(int idAtividade) {
		IdAtividade = idAtividade;
	}

	public Hospede getHospede_id() {
		return Hospede_id;
	}

	public void setHospede_id(Hospede hospede_id) {
		Hospede_id = hospede_id;
	}

	public Atividades getId_atividade() {
		return id_atividade;
	}

	public void setId_atividade(Atividades id_atividade) {
		this.id_atividade = id_atividade;
	}

	public void setIdAtividadesHospedes(int idAtividadesHospedes) {
		IdAtividadesHospedes = idAtividadesHospedes;
	}


   
}


