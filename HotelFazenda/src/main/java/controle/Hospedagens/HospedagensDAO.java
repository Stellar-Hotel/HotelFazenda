package controle.Hospedagens;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import controle.ValidarDia;
import controle.Quartos.QuartosDAO;
import modelo.Hospedagens;
import modelo.Hospedes;
import modelo.Quartos;
import modelo.Usuarios;

public class HospedagensDAO implements IHospedagenDAO {

	private static HospedagensDAO instancia;

	private HospedagensDAO() {
	}

	public static HospedagensDAO getInstancia() {
		if (instancia == null) {
			instancia = new HospedagensDAO();
		}
		return instancia;
	}

	@Override
	public int InserirHospedagem(Hospedagens Hg) {
		String SQL = "INSERT INTO Hospedagens (Checkin, Checkout,IdQuartoHospedagens,IdHospedeHospedagens) VALUES (?, ?,?,?)";

		// cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int chavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Hg.getCheckin());
			ps.setDate(2, Hg.getCheckout());
			ps.setInt(3, Hg.getQuarto().getIdQuarto());
			ps.setInt(4, Hg.getHospede().getIdHospede());

			int result = ps.executeUpdate();
			if (result == 0) {
				throw new SQLException("Não foi possível inserir a hospedagem!");
			} else {
				ResultSet Rs = ps.getGeneratedKeys();
				if (Rs.next()) {
					chavePrimariaGerada = Rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return chavePrimariaGerada;
	}

	@Override
	public ArrayList<Hospedagens> ListarHospedagens() {
		// TODO Auto-generated method stub

		// Arraylist para armazenar resultado do select
		ArrayList<Hospedagens> hospedagens = new ArrayList<Hospedagens>();

		// comando sql executado
		String SQL = "SELECT * FROM Hospedagens"; // tem que colocar o inner join aqui

		// cria a ponte de conecao com o mysql
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery(SQL);

			while (rs.next()) {

				// Cria objeto
				Hospedagens Hg = new Hospedagens();

				Hospedes Hospede = new Hospedes();

				Quartos Quarto = new Quartos();

				Usuarios Usuario = new Usuarios();

				Hospede.setNome(rs.getString("Nome"));
				Hospede.setDocumento(rs.getString("CPF"));
				Hospede.setSobrenome(rs.getString("Sobrenome"));
				Hospede.setDataNasc(rs.getDate("DataNasc"));
				Hospede.setNacionalidade(rs.getString("Nacionalidade"));
				Hospede.setPronome(rs.getString("Pronome"));
				Hospede.setEmail(rs.getString("Email"));
				Hospede.setDataNasc(rs.getDate("DataNasc"));

				Hospede.setUsuario(Usuario);

				// Tem que setar os atributos dos bagulho estrangeiro ainda

				// Pega os valores de cada coluna d registro

				Hg.setCheckin(rs.getDate("Checkin"));
				Hg.setCheckout(rs.getDate("Chekout"));
				Hg.setIdHospedagem(rs.getInt("IdHospedagem"));
				Hg.setHospde(Hospede);
				Hg.setQuarto(Quarto);

				// Adiciona objeto na lista
				hospedagens.add(Hg);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return hospedagens;
	}

	@Override
	public boolean AtualizarHospedagem(Hospedagens Hg) {
		// TODO Auto-generated method stub

		// Comando SQL a ser executado
		String SQL = "UPDATE Hospedagens Set Checkin = ?, Checkout = ?, IdQuartoHospedagens = ?, IdHospedeHospedagens=? where HospedensId = ?";

		// Abre a conexao e cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getConexao();// Instanciando
		Connection conBD = con.Conectar();// cria a conexao

		int retorno = 0;

		try {
			// transfere o texto para um objeto
			PreparedStatement ps = conBD.prepareStatement(SQL);

			// Substitui as interrogações no comando SQL
			ps.setDate(1, Hg.getCheckin());
			ps.setDate(2, Hg.getCheckout());
			ps.setInt(3, Hg.getQuarto().getIdQuarto());
			ps.setInt(4, Hg.getHospede().getIdHospede());

			// indica qual qual hospedagem atualizar no comeando where através do id
			ps.setInt(5, Hg.getIdHospedagem());

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
	public boolean RemoverHospedagem(Hospedagens Hg) {
		// TODO Auto-generated method stub

		// Comando SQL a ser executado
		String SQL = "DELETE FROM Hospedagens Where IdHospedagen = ?";

		// Abre a conexao e cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getConexao();// Instanciando
		Connection conBD = con.Conectar();// cria a conexao

		int retorno = 0;

		try {
			// transfere o texto para um objeto
			PreparedStatement ps = conBD.prepareStatement(SQL);

			// Substitui a primeira interrogação no comando SQL

			ps.setInt(1, Hg.getIdHospedagem());

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
	public Hospedagens BuscarHospedagemId(int Id) {
		// TODO Auto-generated method stub
		Hospedagens Hosp = null;
		String SQL = "Select * from Hospedagens where IdHospedagem = ?"; // tem que arrumar o inner join aqui também
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ps.setInt(1, Id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Hosp = new Hospedagens();

				Hosp.setCheckin(rs.getDate("Checkin"));
				Hosp.setCheckout(rs.getDate("Checkout"));
				Hosp.setIdHospedagem(Id);

				Hosp.setHospde(null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return Hosp;
	}

	@Override
	public void AtualizarSituacao() {
		// TODO Auto-generated method stub
		LocalDate hoje = LocalDate.now();
		ArrayList<Hospedagens> ListaHospedagens = new ArrayList<Hospedagens>();
		HospedagensDAO Hdao = HospedagensDAO.getInstancia();
		QuartosDAO Qdao = QuartosDAO.getConexao();
		ListaHospedagens = Hdao.ListarHospedagens();

		if (ValidarDia.lerDia(hoje.toString())) {
			
			for (Hospedagens hospedagem : ListaHospedagens) {
				String checkin = hospedagem.getCheckin().toString();
				String checkout = hospedagem.getCheckout().toString();
				if (checkin.equals(hoje)) {
					hospedagem.getQuarto().setSituacao(1);
					Qdao.atualizarQuarto(hospedagem.getQuarto());
				} else if (checkout.equals(hoje)) {
					hospedagem.getQuarto().setSituacao(0);
					Qdao.atualizarQuarto(hospedagem.getQuarto());
				}

			}
		}

	}
}
