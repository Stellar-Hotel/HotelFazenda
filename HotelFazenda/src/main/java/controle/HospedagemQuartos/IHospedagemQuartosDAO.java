package controle.HospedagemQuartos;

import java.util.ArrayList;

import modelo.HospedagemQuartos;

public interface IHospedagemQuartosDAO {
	public int InserirHospedagemQuartos(HospedagemQuartos Hosp);
	public ArrayList<HospedagemQuartos> ListarHospedagemQuartos();
	public boolean AtualizarHospedagemQuartos(HospedagemQuartos Hosp);
	public boolean RemoverHospedagemQuartos(HospedagemQuartos Hosp);
	public HospedagemQuartos BuscarHospedagemQuartosPorNumero(int Num);
}
