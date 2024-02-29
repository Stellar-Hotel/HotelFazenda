package controle.Hospedagens;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import modelo.Hospedagens;
import modelo.Hospede;

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

	public Boolean Inserir(Hospedagens a) {
		String SQL = "INSERT INTO Hospedagens (checkin, checkout) VALUES (?, ?)";
		return true;
	}

	public Boolean Alterar(Hospedagens a) {
		return true;
	}

	public Boolean Excluir(Hospedagens a) {
		return true;
	}

	@Override
	public int InserirHospedagem(Hospedagens Hosp) {
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
		Connection conBD = con.conectar();

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
			con.fecharConexao();
		}

		return hospedagens;
	}

	@Override
	public boolean AtualizarHospedagem(Hospedagens Hosp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean RemoverHospedagem(Hospedagens Hosp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Hospedagens BuscarHospedagemId(int Id) {
		// TODO Auto-generated method stub
		return null;
	}
}
