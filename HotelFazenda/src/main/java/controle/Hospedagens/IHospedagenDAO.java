package controle.Hospedagens;

import java.util.ArrayList;

import modelo.Hospedagens;

public interface IHospedagenDAO {

	public int InserirHospedagem(Hospedagens Hosp);

	public ArrayList<Hospedagens> ListarHospedagens();

	public boolean AtualizarHospedagem(Hospedagens Hosp);

	public boolean RemoverHospedagem(Hospedagens Hosp);

	public Hospedagens BuscarHospedagemId(int Id);
	
	public void AtualizarSituacao();

}
