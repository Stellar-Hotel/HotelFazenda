package controle.Hospedagens;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import modelo.Hospedagens;

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

	public Boolean Inserir(Hospedagens Hg) {

		String SQL = "INSERT INTO Hospedagens (checkin, checkout) VALUES (?, ?)";

		// cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int chavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1, Hg.getCheckin());
			ps.setDate(2, Hg.getCheckout());

			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				chavePrimariaGerada = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return true;
	}

	public Boolean Alterar(Hospedagens Hg) {
		return true;
	}

	public Boolean Excluir(Hospedagens Hg) {
		return true;
	}

	@Override
	public int InserirHospedagem(Hospedagens Hg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Hospedagens> ListarHospedagens() {
		// TODO Auto-generated method stub

		// Arraylist para armazenar resultado do select
		ArrayList<Hospedagens> hospedagens = new ArrayList<Hospedagens>();

		// comando sql executado
		String SQL = "SELECT * FROM Hospedagens";

		// cria a ponte de conecao com o mysql
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery(SQL);

			while (rs.next()) {

				// Cria objeto
				Hospedagens Hg = new Hospedagens();
				

				// Pega os valores de cada coluna d registro
				Date Checkin = rs.getDate("Checkin");
				Date Checkout = rs.getDate("Chekout");

				Hg.setCheckin(Checkin);
				Hg.setCheckout(Checkout);

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
		String SQL = "UPDATE Hospedagens Set Checkin = ?, Checkout = ?,"
				+ " PrecoTotal = ? where HospedensId = ?";

		// Abre a conexao e cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getInstancia();// Instanciando
		Connection conBD = con.Conectar();// cria a conexao

		int retorno = 0;

		try {
			// transfere o texto para um objeto
			PreparedStatement ps = conBD.prepareStatement(SQL);

			// Substitui as interrogações no comando SQL
			ps.setDate(1, Hg.getCheckin());
			ps.setDate(2, Hg.getCheckout());
			ps.setFloat(3, Hg.getPrecoTotal());

			// indica qual qual hospedagem atualizar no comeando where através do id
			ps.setInt(4, Hg.getHospedagensId());

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
		return false;
	}

	@Override
	public Hospedagens BuscarHospedagemId(int Id) {
		// TODO Auto-generated method stub
		return null;
	}
}
