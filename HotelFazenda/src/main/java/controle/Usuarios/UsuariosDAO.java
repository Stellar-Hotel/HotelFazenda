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

		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		String SQL = "INSERT INTO Usuarios (Senha, Login) VALUES(?, ?)";

		int chavePrimariaGerada = Integer.MIN_VALUE;
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);// ,
			ps.setString(1, end.getSenha());
			ps.setString(2, end.getLogin());

			int result = ps.executeUpdate();
			if (result == 0) {
				throw new SQLException("Não foi possível executar o INSERT");
			} else {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					chavePrimariaGerada = rs.getInt(1);
				}

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
		String SQL = "SELECT * FROM Usuario";
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery(SQL);

			while (rs.next()) {
				Usuarios Usu = new Usuarios();

				int Id = rs.getInt("IdUsuario");
				Integer NivelDeAcesso = rs.getInt("NivelDeAcesso");
				String Senha = rs.getString("Senha");
				String Login = rs.getString("Login");

				Usu.setNivelDeAcesso(NivelDeAcesso);
				Usu.setSenha(Senha);
				Usu.setLogin(Login);
				Usu.setIdUsuario(Id);
				
				Usuarios.add(Usu);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return Usuarios;
	}

	@Override
	public boolean atualizarUsuarios(Usuarios end) {
		// TODO Auto-generated method stub

		String SQL = "UPDATE Usuarios SET NivelDeAcesso = ?, Senha = ?," + " Login = ? Where IdUsuarios = ?";

		Conexao con = Conexao.getConexao();// instanciando
		Connection conBD = con.Conectar();// cria ponte de conexao

		int retorno = 0;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ps.setInt(1, end.getNivelDeAcesso());
			ps.setString(2, end.getSenha());
			ps.setString(3, end.getLogin());
			ps.setInt(4, end.getIdUsuario());

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
		
		String SQL = "DELETE FROM enderecos WHERE cep = ?";

		Conexao con = Conexao.getConexao(); // instanciando
		Connection conBD = con.Conectar(); // cria "ponte"

		int retorno = 0;
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, end.getIdUsuario());

			retorno = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return (retorno == 0 ? false : true);
	}

	@Override
	public Usuarios BuscarUsuario(String Login, String Senha) {

		Usuarios Usu = null;
		String SQL = "SELECT * FROM Usuarios WHERE Login = ? AND Senha = ?";
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setString(1, Login);
			ps.setString(2, Senha);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Usu = new Usuarios();
				int Id = rs.getInt("IdUsuario");
				String senha = rs.getString("Senha");
				String login = rs.getString("Login");
				Usu.setSenha(senha);
				Usu.setLogin(login);
				Usu.setIdUsuario(Id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return Usu;
	}

}
