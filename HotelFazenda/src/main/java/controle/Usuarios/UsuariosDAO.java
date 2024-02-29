package controle.Usuarios;
import java.util.ArrayList;

import modelo.Usuario;

public class UsuariosDAO implements IUsuariosDAO {
	
	
	private static UsuariosDAO instancia; 
/*
 * Construtor privado (padrao Singleton)
 */
	private UsuariosDAO() {
	}
	/*
	 * Metodo para instanciar(Padrao SIngleton)
	 */
	
	public static UsuariosDAO getInstancia() {
		if (instancia == null) {
			instancia = new UsuariosDAO();
		}
		return instancia;
	}
	
	@Override
	public int inserirUsuario(Usuario end) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ArrayList<Usuario> ListarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean atualizarUsuarios(Usuario end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removerUsuario(Usuario end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Usuario buscarServicoPorCep(int cep) {
		// TODO Auto-generated method stub
		return null;
	}
}
