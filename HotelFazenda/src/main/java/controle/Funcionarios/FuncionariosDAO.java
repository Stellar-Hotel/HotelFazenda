package controle.Funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

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
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, Func.getNome());
			ps.setString(2, Func.getSobrenome());
			ps.setString(3, Func.getFuncao());
			ps.setFloat(4, Func.getSalario());
			ps.setInt(5, Func.getUsuario().getIdUsuario());
		
			int result = ps.executeUpdate();
			if(result==0) {
				throw new SQLException("Não foi possível inserir o funcionário na respectiva tabela!");
			}
			else {
				ResultSet Rs=ps.getGeneratedKeys();
				if(Rs.next())
				{
					ChavePrimariaGerada=Rs.getInt(1);
				}
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		
		
		return ChavePrimariaGerada;
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
			
			while(rs.next()) {
				
				Funcionarios Funcionario = new Funcionarios();
				
				String Nome = rs.getString("nome");
				String funcao = rs.getString("funcao");
				String sobrenome = rs.getString("sobrenome");
				Float salario = rs.getFloat("salario");
				
				Usuarios User= new Usuarios();
				
				User.setSenha(rs.getString("Senha"));
				User.setNivelDeAcesso(rs.getInt("NivelDeAcesso"));
				User.setLogin(rs.getString("Login"));
			

				Funcionario.setNome(Nome);
				Funcionario.setSobrenome(sobrenome);
				Funcionario.setFuncao(funcao);
				Funcionario.setSalario(salario);
				Funcionario.setUsuario(User);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Funcionarios;
	}

	@Override
	public boolean AtualizarFuncionarios(Funcionarios Func) {
		// TODO Auto-generated method stub
		
		String SQL = "UPDATE Funcionarios SET nome = ?, Sobrenome = ?, Funcao = ?, Salario = ?, WHERE IdFuncionario = ?";
		
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
		
		String SQL = "DELETE FROM Funcioanrios WHERE IdFuncionario = ?";
		
		Conexao con = Conexao.getConexao(); // instanciando
		Connection conBD = con.Conectar(); // cria "ponte"

		int retorno = 0;
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, Func.getIdFuncionario());

			retorno = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return (retorno == 0 ? false : true);
	}

	@Override
	public Funcionarios BuscarFuncionarioPorId(int Id) {
		// TODO Auto-generated method stub
		Funcionarios Func=null;
		String SQL="Select * from Funcionarios where IdFuncionario=?";
		Conexao con=Conexao.getConexao();
		Connection conBD=con.Conectar();
		
		try {
			PreparedStatement ps=conBD.prepareStatement(SQL);
			
			ps.setInt(1, Id);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Func=new Funcionarios();
				
				Func.setFuncao(rs.getString("Funcao"));
				Func.setIdFuncionario(Id);
				Func.setNome(rs.getString("Nome"));
				Func.setSalario(rs.getFloat("Salario"));
				Func.setSobrenome(rs.getString("Sobrenome"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		
		return Func;
	}
	

	
}

