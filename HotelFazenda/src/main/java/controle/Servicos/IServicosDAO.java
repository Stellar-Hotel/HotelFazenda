package controle.Servicos;

import java.util.ArrayList;

import modelo.Servicos;

public interface IServicosDAO {

	public int inserirServico(Servicos end);

	public ArrayList<Servicos> ListarServicos();

	public boolean atualizarServico(Servicos end);

	public boolean removerServico(Servicos end);

	public Servicos buscarServicoPorNome(String nome);

}