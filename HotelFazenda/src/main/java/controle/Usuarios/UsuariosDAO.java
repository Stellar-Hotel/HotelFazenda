package controle.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controle.Conexao;
import modelo.Usuarios;

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
	public int inserirUsuario(Usuarios end) {
		
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.conectar(); 
		String SQL = "INSERT INTO USUARIO (Senha, nivel_de_acesso,login) VALUES(?, ?, ?)";

		int chavePrimariaGerada = Integer.MIN_VALUE;	
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			
			
			ResultSet rs = ps.executeQuery();
			
			ps.setString(1, end.getSenha());
			ps.setInt(2,end.getNivelDeAcesso());
			ps.setString(3, end.getLogin());
			
			if (rs!= null) {
				chavePrimariaGerada = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.fecharConexao();
		}
		
		
		return chavePrimariaGerada;
	}
	@Override
	public ArrayList<Usuarios> ListarUsuarios() {
		
		ArrayList<Usuarios> Usuarios = new ArrayList<Usuarios>();
		String SQL = "SELECT * FROM Usuario";
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.conectar(); 
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery(SQL);
			
			while (rs.next()) {
				Usuarios end = new Usuarios();
				
				Integer nivel_de_acesso = rs.getInt("nivel_de_acesso");
				String senha = rs.getString("senha");
				String login = rs.getString("login");
				
				end.setNivelDeAcesso(nivel_de_acesso);
				end.setSenha(senha);
				end.setLogin(login);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.fecharConexao();
		}
		
		return Usuarios;
	}
	@Override
	public boolean atualizarUsuarios(Usuarios end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removerUsuario(Usuarios end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Usuarios buscarServicoPorCep(int cep) {
		// TODO Auto-generated method stub
		return null;
	}
}
