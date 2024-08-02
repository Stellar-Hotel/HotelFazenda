package controle.AtividadesHospedes;

import java.util.ArrayList;

import modelo.AtividadesHospedes;

public interface IAtividadesHospedesDAO {
	public int InserirAtividadesHospedes(AtividadesHospedes A);

	public ArrayList<AtividadesHospedes> ListarAtividadesHospedes();

	public boolean AtualizarAtividadesHospedes(AtividadesHospedes A);

	public ArrayList<AtividadesHospedes> BuscarAtividadesHospedesPorIdHospede(int Id);

	public boolean RemoverAtividadeHospede(int IdAtividade);

	public boolean isHospedeRegisteredForActivity(String documento, int idAtividade);

	public int contarHospedesNaAtividade(int idAtividade);

}
