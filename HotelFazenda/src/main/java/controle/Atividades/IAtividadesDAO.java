package controle.Atividades;

import java.util.ArrayList;

import modelo.Atividades;

public interface IAtividadesDAO {
	public int InserirAtividades(Atividades Ativ);

	public ArrayList<Atividades> ListarAtividades();

	public boolean AtualizarAtividades(Atividades Ativ);

	public boolean RemoverAtividades(Atividades Nome);

	public ArrayList<Atividades> BuscarAtividadePeloNome(String Local);

	public void AtualizarAtividades();
}
