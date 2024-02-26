package controle.ServicosConsumidos;

import java.util.ArrayList;

import modelo.ServicosConsumidos;

public interface IServicosConsumidosDAO {

	public int inserirServicoConsumido(ServicosConsumidos end);
	public ArrayList<ServicosConsumidos> ListarServicos();
	public boolean atualizarServicoConsumido(ServicosConsumidos end);
	public boolean removerServicoConsumido(ServicosConsumidos end);
	public ServicosConsumidos buscarServicoConsumidoPorNome(int nome);
	
}