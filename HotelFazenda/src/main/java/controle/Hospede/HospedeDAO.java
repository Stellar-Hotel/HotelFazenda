package controle.Hospede;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import modelo.Hospedes;
import modelo.Usuarios;

public class HospedeDAO implements IHospedeDAO {

	/*
	 * variavel padrao singleton
	 */
	private static HospedeDAO instancia;

	/*
	 * Construtor privado(padrao singleton)
	 */

	private HospedeDAO() {
	}

	public static HospedeDAO getInstancia() {
		if (instancia == null) {
			instancia = new HospedeDAO();
		}
		return instancia;
	}

	public int inserirHospede(Hospedes Hd) {
		// TODO Auto-generated method stub

		// Comando SQL a ser executado
		String SQL = "INSERT INTO Hospedagens (Nome, Sobrenome, DataNasc, Documento, Nacionalidade,Pronome, Email, IdUsuarioHospede) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int chavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, Hd.getNome());
			ps.setString(2, Hd.getSobrenome());
			ps.setDate(3, Hd.getDataNasc());
			ps.setString(4, Hd.getNacionalidade());
			ps.setString(5, Hd.getEmail());
			ps.setString(6, Hd.getPronome());
			ps.setString(7, Hd.getDocumento());
			ps.setInt(8, Hd.getUsuario().getIdUsuario());

			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				chavePrimariaGerada = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Fecha conexao
			con.FecharConexao();
		}

		return chavePrimariaGerada;
	}

	@Override
	public ArrayList<Hospedes> ListarHospedes() {
		// TODO Auto-generated method stub

		// Arraylist para armazenar resultado do select
		ArrayList<Hospedes> hospede = new ArrayList<Hospedes>();

		// comando sql executado
		String SQL = "SELECT * FROM Hospedes";// tem que fazer o inner join e os objetos

		// cria a "ponte de conexao" com o mysql
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery(SQL);

			while (rs.next()) {

				// Cria objeto
				Hospedes Hd = new Hospedes();

				// Pega os valores de cada coluna d registro
				String Nome = rs.getString("Nome");
				String Sobrenome = rs.getString("Sobrenome");
				Date DataNasc = rs.getDate("DataNasc");
				String Documento = rs.getString("Documento");
				String Nacionalidade = rs.getString("Nacionalidade");
				String Pronome = rs.getString("Pronome");
				String Email = rs.getString("Email");
				int Id=rs.getInt("IdHospede");

				Usuarios User = new Usuarios();

				// preenche os atributos desse objeto

				User.setSenha(rs.getString("Senha"));
				User.setLogin(rs.getString("Login"));

				Hd.setNome(Sobrenome);
				Hd.setNome(Nome);
				Hd.setDataNasc(DataNasc);
				Hd.setDocumento(Documento);
				Hd.setEmail(Email);
				Hd.setNacionalidade(Nacionalidade);
				Hd.setPronome(Pronome);
				Hd.setIdHospede(Id);
				//Atribui o objeto estranjeiro no que vai ser colocado na lista
				Hd.setUsuario(User);
				
				// Adiciona objeto na lista
				hospede.add(Hd);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return hospede;
	}

	/*
	 * Tem q possuir a chave primária (ID, CPF, CEP, etc.) Atualiza um registro
	 * existente no banco de dados O objeto passado como parâmetro já deve possuir
	 * os NOVOS valeres porém deve possuir a chave primária do registro que se
	 * deseja atualizar.
	 */

	@Override
	public boolean atualizarHospede(Hospedes Hd) {
		// TODO Auto-generated method stub

		// Comando SQL a ser executado
		String SQL = "UPDATE Hospedes Set Nome = ?, Documento = ?, Sobrenome = ?, Email = ?, Nacionalidade = ?, Pronome = ?, DataNacs=  ? , IdUsuarioHospede = ?  where IdHospede = ?";

		// Abre a conexao e cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getConexao();// Instanciando
		Connection conBD = con.Conectar();// cria a conexao

		int retorno = 0;

		try {
			// transfere o texto para um objeto
			PreparedStatement ps = conBD.prepareStatement(SQL);

			// Substitui a primeira interrogação no comando SQL
			ps.setString(1, Hd.getNome());
			ps.setString(2, Hd.getDocumento());
			ps.setString(3, Hd.getSobrenome());
			ps.setString(4, Hd.getEmail());
			ps.setString(5, Hd.getNacionalidade());
			ps.setString(6, Hd.getPronome());
			ps.setDate(7, Hd.getDataNasc());
			ps.setInt(8, Hd.getUsuario().getIdUsuario());
			// Substitui a segunda interrogação no comando SQL
			ps.setInt(9, Hd.getIdHospede());
			// Retorna 1 para certo e 0 para erro.
			retorno = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// Captura e mostra eventuais bugs na execução do codigo
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		// if ternário
		return (retorno == 0 ? false : true);
	}

	@Override
	public boolean removerHospede(Hospedes Hd) {
		// TODO Auto-generated method stub

		// Comando SQL a ser executado
		String SQL = "DELETE FROM Hospedes Where IdHospede = ?";

		// Abre a conexao e cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getConexao();// Instanciando
		Connection conBD = con.Conectar();// cria a conexao

		int retorno = 0;

		try {
			// transfere o texto para um objeto
			PreparedStatement ps = conBD.prepareStatement(SQL);

			// Substitui a primeira interrogação no comando SQL

			ps.setInt(1, Hd.getIdHospede());

			// Retorna 1 para certo e 0 para erro.
			retorno = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// Captura e mostra eventuais bugs na execução do codigo
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		// if ternário
		return (retorno == 0 ? false : true);
	}

	@Override
	public Hospedes buscarHospedePorCPF(String CPF) {
		// TODO Auto-generated method stub
		Hospedes Hosp=null;
		String SQL="Select * from Hospedes where Documento = ? inner join Usuarios on Usuarios.IdUsuario=Hospedes.IdUsuarioHospede";
		Conexao con=Conexao.getConexao();
		Connection conBD=con.Conectar();
		
		try {
			PreparedStatement ps=conBD.prepareStatement(SQL);
			
			ps.setString(1, CPF);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Hosp=new Hospedes();
				Usuarios User=new Usuarios();
				
				User.setIdUsuario(rs.getInt("IdUsuario"));
				User.setLogin(rs.getString("Login"));
				User.setSenha(rs.getString("Senha"));
				User.setTipo(rs.getBoolean("Tipo"));
				
				
				Hosp.setDocumento(CPF);
				Hosp.setDataNasc(rs.getDate("DataNasc"));
				Hosp.setEmail(rs.getString("Email"));
				Hosp.setIdHospede(rs.getInt("IdHospede"));
				Hosp.setNacionalidade(rs.getString("Nacionalidade"));
				Hosp.setNome(rs.getString("Nome"));
				Hosp.setPronome(rs.getString("Pronome"));
				Hosp.setSobrenome(rs.getString("Sobrenome"));
				
				Hosp.setUsuario(User);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		
		return Hosp;
	}
}
