package controle.Funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import controle.Funcionarios.FuncionariosDAO;
import modelo.Funcionarios;
import modelo.Usuarios;

public class FuncionariosDAO implements IFuncionariosDAO

{

	private static FuncionariosDAO Funcionarios;

	private FuncionariosDAO() {
	} // construtor privado

	/**
	 * Metodo singleton
	 * 
	 * @return conexao
	 */

	public static FuncionariosDAO getConexao() {
		if (Funcionarios == null) {
			Funcionarios = new FuncionariosDAO();
		}

		return Funcionarios;
	}

	@Override
	public int InserirFuncionario(Funcionarios Func) {
		// TODO Auto-generated method stub
		String SQL = "INSERT INTO Funcionarios (Nome, Sobrenome, Funcao, Salario) VALUES (?, ?, ?, ?)";

		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int ChavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setString(1, Func.getNome());
			ps.setString(2, Func.getSobrenome());
			ps.setString(3, Func.getFuncao());
			ps.setFloat(4, Func.getSalario());
			ps.setInt(5, Func.getUsuario().getIdUsuario());

			ResultSet Rs = ps.executeQuery();
			if (Rs != null) {
				ChavePrimariaGerada = Rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return 0;
	}

	@Override
	public ArrayList<Funcionarios> ListarFuncionarios() {
		// TODO Auto-generated method stub

		ArrayList<Funcionarios> Funcionarios = new ArrayList<Funcionarios>();

		String SQL = "SELECT * FROM Funcionarios";

		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Funcionarios Funcionario = new Funcionarios();

				String Nome = rs.getString("Nome");
				String Funcao = rs.getString("Funcao");
				String Sobrenome = rs.getString("Sobrenome");
				Float Salario = rs.getFloat("Salario");

				Usuarios User = new Usuarios();

				User.setSenha(rs.getString("Senha"));
				User.setNivelDeAcesso(rs.getInt("NivelDeAcesso"));
				User.setLogin(rs.getString("Login"));

				Funcionario.setNome(Nome);
				Funcionario.setSobrenome(Sobrenome);
				Funcionario.setFuncao(Funcao);
				Funcionario.setSalario(Salario);
				Funcionario.setUsuario(User);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean AtualizarFuncionarios(Funcionarios Func) {
		// TODO Auto-generated method stub

		String SQL = "UPDATE Funcionarios SET Nome = ?, Sobrenome = ?, Funcao = ?, Salario = ?, WHERE IdFuncionario = ?";

		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int retorno = 0;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ps.setString(1, Func.getNome());
			ps.setString(2, Func.getSobrenome());
			ps.setString(3, Func.getFuncao());
			ps.setFloat(4, Func.getSalario());
			ps.setInt(5, Func.getIdFuncionario());

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
	public boolean RemoverFuncionario(Funcionarios Func) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Funcionarios BuscarFuncionarioPorNome(String Nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
