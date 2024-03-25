package controle.Atividades;

import java.util.ArrayList;

import modelo.Atividades;

public interface IAtividadesDAO {
	public int InserirAtividades(Atividades Ativ);

	public ArrayList<Atividades> ListarAtividades();

	public boolean AtualizarAtividades(Atividades Ativ);

	public boolean RemoverAtividades(String Nome);

	public Atividades BuscarAtividadePeloNome(String Local);
}
