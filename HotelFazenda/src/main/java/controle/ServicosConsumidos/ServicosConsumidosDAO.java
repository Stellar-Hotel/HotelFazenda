package controle.ServicosConsumidos;
import java.util.ArrayList;

import controle.Usuarios.UsuariosDAO;
import modelo.ServicosConsumidos;

public class ServicosConsumidosDAO implements IServicosConsumidosDAO {
	
	private static ServicosConsumidosDAO instancia;
	
	private ServicosConsumidosDAO() {
		
	}
	public static ServicosConsumidosDAO getinstancia() {
		if (instancia == null) {
			instancia = new ServicosConsumidosDAO();
		}
		return instancia;
	}
	
	@Override
	public int inserirServicoConsumido(ServicosConsumidos end) {
	
		String SQL = "INSERT INTO ServicosConsumidos (Senha, nivel_de_acesso) VALUES(?, ?)";
		return 0;
	}
	@Override
	public ArrayList<ServicosConsumidos> ListarServicos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean atualizarServicoConsumido(ServicosConsumidos end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removerServicoConsumido(ServicosConsumidos end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public ServicosConsumidos buscarServicoConsumidoPorNome(int nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
