package controle.Hospede;

import java.util.ArrayList;

import modelo.Hospedes;

public interface IHospedeDAO {

	public int inserirHospede(Hospedes end);

	public ArrayList<Hospedes> ListarHospedes();

	public boolean atualizarHospede(Hospedes end);

	public boolean removerHospede(Hospedes end);

	public Hospedes buscarHospedePorCep(int cep);

}
