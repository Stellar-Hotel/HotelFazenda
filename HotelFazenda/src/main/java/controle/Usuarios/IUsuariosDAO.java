package controle.Usuarios;

import java.util.ArrayList;

import modelo.Usuario;

public interface IUsuariosDAO {

	public int inserirUsuario(Usuario end);
	public ArrayList<Usuario> ListarUsuarios();
	public boolean atualizarUsuarios(Usuario end);
	public boolean removerUsuario(Usuario end);
	public Usuario buscarServicoPorCep(int cep);
	
}