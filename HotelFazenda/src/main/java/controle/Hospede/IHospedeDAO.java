package controle.Hospede;

import java.util.ArrayList;

import modelo.Hospede;

public interface IHospedeDAO {

	public int inserirHospede(Hospede end);
	public ArrayList<Hospede> ListarHospedes();
	public boolean atualizarHospede(Hospede end);
	public boolean removerHospede(Hospede end);
	public Hospede buscarHospedePorCep(int cep);
	
}
