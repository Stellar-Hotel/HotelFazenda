package controle.Atividades;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controle.Conexao;
import modelo.Atividades;
import modelo.Funcionarios;
import modelo.Usuarios;

public class AtividadesDAO implements IAtividadesDAO

{
	private static AtividadesDAO instancia;

	public static AtividadesDAO getInstancia() {
		if (instancia == null) {
			instancia = new AtividadesDAO();
		}

		return instancia;
	}

	private AtividadesDAO() {
	}

	@Override
	public ArrayList<Atividades> ListarAtividades() {

		// ArrayList Que vai ser retornado
		ArrayList<Atividades> atividades = new ArrayList<Atividades>();

		// Comando pro MySQL
		String SQL = "SELECT * FROM Atividades inner join Funcionarios.IdFuncionario=Atividades.IdFuncionario";

		// Método pra fazer a conexão
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL);

			ResultSet Rs = Ps.executeQuery();
			while (Rs.next()) {
				Atividades At = new Atividades();

				int IdAtividade = Rs.getInt("IdAtividade");
				int RestricaoIdade = Rs.getInt("IdadeMinima");
				String Horario = Rs.getString("Horario");
				String HorarioFim = Rs.getString("HorarioFim");
				String NomeAtividade = Rs.getString("NomeAtividade");
				Date Data = Rs.getDate("Data");

				Funcionarios Funcionario = new Funcionarios();

				Funcionario.setNome(Rs.getString("Nome"));
				Funcionario.setSobrenome(Rs.getString("Sobrenome"));
				Funcionario.setFuncao(Rs.getString("Funcao"));
				Funcionario.setIdFuncionario(Rs.getInt("IdFuncionario"));
				Funcionario.setSalario(Rs.getFloat("Slario"));
				
				
				Funcionario.setUsuario(null);

				// Tem que preencher os atributos do objeto funcionário
				At.setIdAtividade(IdAtividade);
				At.setIdadeMinima(RestricaoIdade);
				At.setHorario(Horario);
				At.setHorarioFim(HorarioFim);
				At.setFuncionario(Funcionario);
				At.setNomeAtividade(NomeAtividade);
				At.setData(Data);
				
				atividades.add(At);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		// Return da arraylist
		return atividades;
	}

	@Override
	public boolean AtualizarAtividades(Atividades Ativ) {

		// Conexâo SQl a ser executada
		String SQL = "UPDATE Atividades SET NomeAtividade = ?,  Horario = ?,  HorarioFim = ?,  Data = ?,  IdFuncionario = ?,  IdadeMinima = ?  WHERE IdAtividade = ?";

		// abre a conexão e cria a "parte de conexão" com MYSQL
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int retorno = 0;
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setString(1, Ativ.getNomeAtividade());
			ps.setString(2, Ativ.getHorario());
			ps.setDate(3, Ativ.getData());
			ps.setInt(4, Ativ.getFuncionario().getIdFuncionario());
			ps.setInt(5, Ativ.getIdadeMinima());
			ps.setInt(6, Ativ.getIdAtividade());

			retorno = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return (retorno == 0 ? false : true);
	}

	@Override
	public boolean RemoverAtividades(String Nome) {
		String SQL = "DELETE FROM enderecos WHERE NomeAtividade = ?";

		Conexao con = Conexao.getConexao(); // instanciando
		Connection conBD = con.Conectar(); // cria "ponte"

		int retorno = 0;
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setString(1, Nome);

			retorno = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		return (retorno == 0 ? false : true);
	}

	@Override
	public ArrayList<Atividades> BuscarAtividadePeloNome(String Local) {
		// TODO Auto-generated method stub
		ArrayList<Atividades> lista=new ArrayList<Atividades>();
		Atividades Ativ=null;
		String SQL="SELECT * FROM Atividades Where Nome=? inner join Funcionarios.idFuncionario=atividades.IdFuncionario"
				+" inner join Usuarios.IdUsuario=Funcionarios.IdUsario";
		Conexao con=Conexao.getConexao();
		Connection conBD=con.Conectar();
		
		try {
			PreparedStatement ps=conBD.prepareStatement(SQL);
			ps.setString(1, Local);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				Ativ=new Atividades();
				Funcionarios f = new Funcionarios();
				Usuarios usu=new Usuarios();
				//preenchendo o usuario pra botar no funcionario
				usu.setIdUsuario(rs.getInt("IdUsuario"));
				usu.setLogin(rs.getString("Login"));
				usu.setSenha(rs.getString("Senha"));
				//preenchendo o objeto funcionário para colocar na atividade
				f.setFuncao(rs.getString("Funcao"));
				f.setIdFuncionario(rs.getInt("IdFuncionario"));
				f.setNome(rs.getString("Nome"));
				f.setSobrenome(rs.getString("Sobrenome"));
				f.setSalario(rs.getFloat("Salario"));
				f.setUsuario(usu);
				//preenchendo a atividade
				Ativ.setData(rs.getDate("Data"));
				Ativ.setHorario(rs.getString("Horario"));
				Ativ.setHorarioFim(rs.getString("IdadeMinima"));
				Ativ.setIdadeMinima(rs.getInt("IdadeMinima"));
				Ativ.setIdAtividade(rs.getInt("IdAtividade"));
				Ativ.setNomeAtividade(rs.getString("NomeAtividade"));
				//Perguntar como faz isso aqui
				Ativ.setFuncionario(f);
				
				lista.add(Ativ);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		
		
		return lista;
	}

	@Override
	public int InserirAtividades(Atividades Ativ) {
		// TODO Auto-generated method stub
		String SQL = "INSERT INTO Atividades(Horario,HorarioFim,IdFuncionario,IdadeMinima,NomeAtividade,Data) VALUES (?,?,?,?,?,?)";
		// Método pra fazer a conexão com o banco
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int ChavePrimariaGerada = Integer.MIN_VALUE;// variável que vai guardar a chave que vai ser gerada
													// automaticamente, se não for autogerado não precisa disso

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);// se for um insert já
																								// conhcendo a chave
																								// primária não adcionar
																								// o
																								// Statement.RETURN_GENERATED_KEYS
			Ps.setString(1, Ativ.getHorario());
			Ps.setString(2, Ativ.getHorarioFim());
			Ps.setInt(3, Ativ.getFuncionario().getIdFuncionario());
			Ps.setInt(4, Ativ.getIdadeMinima());
			Ps.setString(5, Ativ.getNomeAtividade());
			Ps.setDate(6, Ativ.getData());

			/*
			 * se for um insert sem gerar chave primária automaticamente não usar a parte de
			 * baixo
			 */
			int result = Ps.executeUpdate();
			if (result == 0) {
				throw new SQLException("Não foi possível inserir a atividade!");
			}
			else {
				ResultSet rs= Ps.getGeneratedKeys();
				if(rs.next())
				{
					ChavePrimariaGerada=rs.getInt(1);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return ChavePrimariaGerada;
	}

}
