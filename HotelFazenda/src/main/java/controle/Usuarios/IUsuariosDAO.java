package controle.Usuarios;

import java.util.ArrayList;

import modelo.Usuarios;

public interface IUsuariosDAO {

	public void inserirUsuario(Usuarios end);
	public ArrayList<Usuarios> ListarUsuarios();
	public boolean atualizarUsuarios(Usuarios end);
	public boolean removerUsuario(Usuarios end);
	public Usuarios BuscarUsuario(String Login, String Senha);
	
}