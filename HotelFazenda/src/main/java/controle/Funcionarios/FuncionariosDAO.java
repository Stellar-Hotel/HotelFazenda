package controle.Funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import controle.Funcionarios.FuncionariosDAO;
import modelo.Funcionarios;

public class FuncionariosDAO implements IFuncionariosDAO

{
	
	private static FuncionariosDAO instancia;
	
	private FuncionariosDAO() {
	} // construtor privado

	/**
	 * Metodo singleton
	 * 
	 * @return conexao
	 */

	public static FuncionariosDAO getConexao() {
		if (instancia == null) {
			instancia = new FuncionariosDAO();
		}

		return instancia;
	}

	
	

	@Override
	public int InserirFuncionario(Funcionarios Func) {
		// TODO Auto-generated method stub
		String SQL = "INSERT INTO Funcionarios (nome, sobrenome, funcao, salario) VALUES (?, ?, ?, ?)";
		return 0;
	}

	@Override
	public ArrayList<Funcionarios> ListarFuncionarios() {
		// TODO Auto-generated method stub
		
		ArrayList<Funcionarios> Funcionarios = new ArrayList<Funcionarios>();
		
		String SQL = "SELECT * FROM Funcionarios";
		
		Conexao con = Conexao.getConexao();
		Connection conBD = con.conectar();
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Funcionarios funcionario = new Funcionarios();
				
				String nome = rs.getString("nome");
				String funcao = rs.getString("funcao");
				String sobrenome = rs.getString("sobrenome");
				Float salario = rs.getFloat("salario");
				
				funcionario.setNome(nome);
				funcionario.setSobrenome(sobrenome);
				funcionario.setFuncao(funcao);
				funcionario.setSalario(salario);
				
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
		return false;
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

