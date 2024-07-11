package controle.Funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import controle.Conexao;
import controle.Funcionarios.FuncionariosDAO;
import controle.Usuarios.UsuariosDAO;
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
		String SQL = "INSERT INTO Funcionarios (Nome, Sobrenome, Funcao, Salario,IdUsuarioFuncionario,CPF,NivelDeAcesso,SetorFunc,PronomesFunc,EmailFunc,Telefone) VALUES (?,?,?, ?, ?, ?,?,?,?,?,?)";
		
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
			ps.setString(6, Func.getCPF());
			ps.setInt(7, Func.getNivelDeAcesso());
			ps.setString(8,Func.getSetor());
			ps.setString(9, Func.getPronomeFunc());
			ps.setString(10, Func.getEmailFunc());
			ps.setString(11, Func.getTelefone());
		
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
		
		String SQL = "SELECT * FROM Funcionarios inner join Usuarios on Usuarios.IdUsuario=Funcionarios.IdUsuarioFuncionario";
		
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
				String CPF=rs.getString("CPF");
				int nivel=rs.getInt("NivelDeAcesso");
				int IdFunc=rs.getInt("IdFuncionario");
				String Setor=rs.getString("SetorFunc");
				String PronomeFunc=rs.getString("PronomesFunc");
				String EmailFunc=rs.getString("EmailFunc");
				String Telefone=rs.getString("Telefone");
				
				Usuarios User= new Usuarios();
				
				User.setSenha(rs.getString("Senha"));
				User.setLogin(rs.getString("Login"));
				User.setIdUsuario(rs.getInt("IdUsuarioFuncionario"));
			

				Funcionario.setNome(Nome);
				Funcionario.setSobrenome(sobrenome);
				Funcionario.setFuncao(funcao);
				Funcionario.setSalario(salario);
				Funcionario.setUsuario(User);
				Funcionario.setCPF(CPF);
				Funcionario.setNivelDeAcesso(nivel);
				Funcionario.setIdFuncionario(IdFunc);
				Funcionario.setSetor(Setor);
				Funcionario.setPronomeFunc(PronomeFunc);
				Funcionario.setEmailFunc(EmailFunc);
				Funcionario.setTelefone(Telefone);
				
				Funcionarios.add(Funcionario);
				
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
		UsuariosDAO dao=UsuariosDAO.getInstancia();
		
		String SQL = "UPDATE Funcionarios SET nome = ?, Sobrenome = ?, Funcao = ?, Salario = ?,CPF=?,"
				+ "NivelDeAcesso=?,PronomesFunc=?,EmailFunc=?,SetorFunc=?,Telefone=? WHERE IdFuncionario = ?";//acho que tem que fazer o inner join aqui tbm
		
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();
		
		int retorno = 0;
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			
			ps.setString(1, Func.getNome());
			ps.setString(2, Func.getSobrenome());
			ps.setString(3, Func.getFuncao());
			ps.setFloat(4, Func.getSalario());
			ps.setString(5, Func.getCPF());
			ps.setInt(6, Func.getNivelDeAcesso());
			ps.setString(7, Func.getPronomeFunc());
			ps.setString(8, Func.getEmailFunc());
			ps.setString(9, Func.getSetor());
			ps.setString(10, Func.getTelefone());
			
			
			ps.setInt(11, Func.getIdFuncionario());
			
			
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
		
		String SQL = "DELETE FROM Funcionarios WHERE IdFuncionario = ?";
		
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
	public Funcionarios BuscarFuncionarioPorIdUsuario(Usuarios user) {
		// TODO Auto-generated method stub
		Funcionarios Func=null;
		String SQL="Select * from Funcionarios inner join Usuarios on Usuarios.IdUsuario=Funcionarios.IdUsuarioFuncionario  where IdUsuarioFuncionario=? ";//inner join aqui também
		Conexao con=Conexao.getConexao();
		Connection conBD=con.Conectar();
		
		try {
			PreparedStatement ps=conBD.prepareStatement(SQL);
			
			ps.setInt(1, user.getIdUsuario());
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Func=new Funcionarios();
				Usuarios Usu=new Usuarios();
				
				Usu.setIdUsuario(rs.getInt("IdUsuario"));	
				Usu.setLogin(rs.getString("Login"));
				Usu.setSenha(rs.getString("Senha"));
				Usu.setTipo(rs.getBoolean("Tipo"));
				
				
				Func.setFuncao(rs.getString("Funcao"));
				Func.setIdFuncionario(rs.getInt("IdFuncionario"));
				Func.setNome(rs.getString("Nome"));
				Func.setSalario(rs.getFloat("Salario"));
				Func.setSobrenome(rs.getString("Sobrenome"));
				Func.setCPF(rs.getString("CPF"));
				Func.setNivelDeAcesso(rs.getInt("NivelDeAcesso"));
				Func.setPronomeFunc(rs.getString("PronomesFunc"));
				Func.setEmailFunc(rs.getString("EmailFunc"));
				Func.setTelefone(rs.getString("Telefone"));
				Func.setSetor(rs.getString("SetorFunc"));
				Func.setUsuario(Usu);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		
		return Func;
	}
	
	public ArrayList<Funcionarios> BuscarFuncionarioPorNome(String Nome) {
		// TODO Auto-generated method stub
		Funcionarios Func=null;
		ArrayList<Funcionarios> Lista=new ArrayList<Funcionarios>();
		String SQL="Select * from Funcionarios where Nome=? inner join Usuarios.IdUsuario=Funcionarios.IdUsuario";//inner join aqui tbm
		Conexao con=Conexao.getConexao();
		Connection conBD=con.Conectar();
		
		try {
			PreparedStatement ps=conBD.prepareStatement(SQL);
			
			ps.setString(1, Nome);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Usuarios Usu=new Usuarios();
				Func=new Funcionarios();
				
				Usu.setIdUsuario(rs.getInt("idUsuario"));
				Usu.setLogin(rs.getString("Login"));
				Usu.setSenha(rs.getString("Senha"));
//				Usu.setNivelDeAcesso(rs.getInt("NivelDeAcesso"));
				
				Func.setFuncao(rs.getString("Funcao"));
				Func.setIdFuncionario(rs.getInt("IdFuncionario"));
				Func.setNome(Nome);
				Func.setSalario(rs.getFloat("Salario"));
				Func.setSobrenome(rs.getString("Sobrenome"));
				Func.setPronomeFunc(rs.getString("PronomesFunc"));
				Func.setEmailFunc(rs.getString("EmailFunc"));
				Func.setSetor(rs.getString("Setor"));
				Func.setTelefone(rs.getString("Telefone"));
				Func.setUsuario(Usu);
				
				Lista.add(Func);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		
		return Lista;
	}
	

	
}

