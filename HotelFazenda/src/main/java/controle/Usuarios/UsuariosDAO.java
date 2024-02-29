package controle.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
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
		String SQL = "INSERT INTO USUARIO (Senha, nivel_de_acesso) VALUES(?, ?)";
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ArrayList<Usuario> ListarUsuarios() {
		
		ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
		String SQL = "SELECT * FROM Usuario";
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.conectar(); 
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Usuario end = new Usuario();
				
				Integer nivel_de_acesso = rs.getInt("nivel_de_acesso");
				String senha = rs.getString("senha");
				
				end.setNivelDeAcesso(nivel_de_acesso);;
				end.setSenha(senha);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.fecharConexao();
		}
		
		return Usuarios;
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
