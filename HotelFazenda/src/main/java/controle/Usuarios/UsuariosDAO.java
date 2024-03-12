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
	public UsuariosDAO() {
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
		Connection conBD = con.Conectar(); 
		String SQL = "INSERT INTO USUARIO (Senha, NivelDeAcesso,login) VALUES(?, ?, ?)";

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
			con.FecharConexao();
		}
		
		
		return chavePrimariaGerada;
	}
	@Override
	public ArrayList<Usuarios> ListarUsuarios() {
		
		ArrayList<Usuarios> Usuarios = new ArrayList<Usuarios>();
		String SQL = "SELECT * FROM Usuario WHERE Login = ?, Senha = ?";
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.Conectar(); 
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			
			ps.setString(1, Usuarios.getLogin);
			ps.setString(2, Usuarios.getSenha());
			
			
			
			ResultSet rs = ps.executeQuery(SQL);
			
			while (rs.next()) {
				Usuarios Usu = new Usuarios();
				
				Integer nivel_de_acesso = rs.getInt("nivel_de_acesso");
				String senha = rs.getString("senha");
				String login = rs.getString("login");
				
				Usu.setNivelDeAcesso(nivel_de_acesso);
				Usu.setSenha(senha);
				Usu.setLogin(login);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.FecharConexao();
		}
		
		return Usuarios;
	}
	@Override
	public boolean atualizarUsuarios(Usuarios end) {
		// TODO Auto-generated method stub
		
		String SQL = "UPDATE Usuarios SET NivelDeAcesso = ?, Senha = ?,"
				+ " Login = ? Where IdUsuarios = ?";
		
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.Conectar();
		
		int retorno = 0;
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			
			ps.setInt(1, end.getNivelDeAcesso());
			ps.setString(2, end.getSenha());
			ps.setString(3, end.getLogin());
			ps.setInt(4, end.getIdUsuarios());
			
			retorno = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		
			
		return (retorno == 0 ? false : true);
	}
	@Override
	public boolean removerUsuario(Usuarios end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Usuarios uscarUsuario(String Login, String Senha) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
